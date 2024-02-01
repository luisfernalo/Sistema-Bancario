package com.softwared.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.softwared.banco.repository.CuentaRepository;

@Service("usuarioAuthenticationService")
public class UsuarioAuthenticationService implements UserDetailsService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("entro al load");
		
		return cuentaRepository.findByNombreTitular(username).orElseThrow(()-> new UsernameNotFoundException("usuario no vlido"));
		
	}

}
