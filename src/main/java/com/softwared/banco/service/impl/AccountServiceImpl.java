package com.softwared.banco.service.impl;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softwared.banco.dto.LoginRequest;
import com.softwared.banco.dto.RegisterRequestDto;
import com.softwared.banco.jwt.AuthResponse;
import com.softwared.banco.jwt.JwtService;
import com.softwared.banco.modelo.Account;
import com.softwared.banco.modelo.Authority;
import com.softwared.banco.modelo.Person;
import com.softwared.banco.repository.AuthorityRepository;
import com.softwared.banco.repository.AccountRepository;
import com.softwared.banco.repository.PersonRepository;
import com.softwared.banco.service.IAccountService;
import com.softwared.banco.util.enums.AuthorityNameEnum;
import com.softwared.banco.util.excepciones.SistemaBancarioExcepcionDetails;
import com.softwared.banco.util.message.LocaleResolverConfig;

@Service
public class AccountServiceImpl implements IAccountService {

	private final AccountRepository cuentaRepository;

	private final AuthorityRepository authorityRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final PersonRepository personRepository;

	private final JwtService jwtService;

	LocaleResolverConfig localeResolverConfig = new LocaleResolverConfig();

	public AccountServiceImpl(AccountRepository cuentaRepository, AuthorityRepository authorityRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService,
			PersonRepository personRepository) {
		super();
		this.cuentaRepository = cuentaRepository;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.personRepository = personRepository;
		this.jwtService = jwtService;
	}

	@Override
	public AuthResponse createAccount(RegisterRequestDto request) {

		if (validateNumberLength(request.getNumberAccount())) {
			globalError(
					localeResolverConfig.messageSource().getMessage("short.number.length", null, Locale.getDefault()),
					HttpStatus.BAD_REQUEST);
		}
		
		if (validateNumber(request.getNumberAccount()).isPresent()) {
			globalError(localeResolverConfig.messageSource().getMessage("invalid.number", null, Locale.getDefault()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		var person = createAccountRequest(request);
		var jwtToken = jwtService.generateToken(person);
		createAccount(person, request);
		return AuthResponse.builder().token(jwtToken).build();

	}

	private Account createAccount(Person person, RegisterRequestDto request) {

		return cuentaRepository.save(new Account(request.getInitialBalance(), request.getNumberAccount(), person));

	}

	private Optional<Account> validateNumber(Long numberAccount) {

		return cuentaRepository.findByNumberAccount(numberAccount);
	}

	private boolean validateNumberLength(Long numberAccount) {
		return numberAccount.toString().length() <= 4;
	}

	private Person createAccountRequest(RegisterRequestDto request) {

		return personRepository.save(new Person(request.getHolderName(), request.getHolderLastName(),
				request.getHolderEmail(), passwordEncoder.encode(request.getHolderpassword()), addAuthority()));
	}

	private Set<Authority> addAuthority() {
		Authority roleuser = authorityRepository.findByName(AuthorityNameEnum.USER.toString()).get();
		Set<Authority> rol = new HashSet<>();
		rol.add(roleuser);
		return rol;
	}

	@Override
	public AuthResponse loginUser(LoginRequest request) {

		var person = personRepository.findByHolderEmail(request.getEmail());

		if (person.isEmpty()) {
			globalError(localeResolverConfig.messageSource().getMessage("login.Error", null, Locale.getDefault()),
					HttpStatus.UNAUTHORIZED);
		}

		authenticationUser(request);

		var jwtToken = jwtService.generateToken(person.get());

		return AuthResponse.builder().token(jwtToken).build();

	}

	private void authenticationUser(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

	}

	private void globalError(String errorMessage, HttpStatus status) {
		throw new SistemaBancarioExcepcionDetails(errorMessage, "error", status);
	}

}
