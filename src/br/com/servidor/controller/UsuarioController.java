package br.com.servidor.controller;

import br.com.servidor.model.Usuario;
import br.com.servidor.repository.UsuarioRepository;
import br.com.servidor.repository.Impl.UsuarioRepositoryImpl;

public class UsuarioController {
	private UsuarioRepository usuarioRepository;
	

	public UsuarioController () {
		usuarioRepository = new UsuarioRepositoryImpl();
	}
	
	public void addUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void updateUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public Usuario getUsuario(String email, String senha) {
		return usuarioRepository.find(email,senha);
	}
}
