package com.softwared.banco.service;

import org.springframework.stereotype.Service;

@Service("usuarioAuthenticationService")
public class UsuarioAuthenticationService {

/*

implements UserDetailsService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		
		return cuentaRepository.findByNombreTitular(username).orElseThrow(()-> new UsernameNotFoundException("usuario no vlido"));
		
	}
*/
}
