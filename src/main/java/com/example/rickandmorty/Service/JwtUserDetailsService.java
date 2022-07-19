package com.example.rickandmorty.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.example.rickandmorty.dto.UsuarioTO;
import com.example.rickandmorty.entity.Usuario;
import com.example.rickandmorty.repository.UsuarioRepository;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("no existe usuario'"+username+"' en el sistema!");
		}
		/*return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());*/
				List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Rol: "+ authority.getAuthority()))
				.collect(Collectors.toList());


		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);		
	}

	public Usuario save(UsuarioTO user) {
		Usuario newUser = new Usuario();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEnabled(user.getEnabled());
		newUser.setNombre(user.getNombre());
		newUser.setApellido(user.getApellido());
		newUser.setEmail(user.getEmail());

		newUser.setRoles(user.getRoles());

		return usuarioRepository.save(newUser);
	}
}