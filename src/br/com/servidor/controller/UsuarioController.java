package br.com.servidor.controller;

import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.model.Usuario;
import br.com.servidor.repository.UsuarioRepository;
import br.com.servidor.repository.Impl.UsuarioRepositoryImpl;

public class UsuarioController {
	private UsuarioRepository usuarioRepository;
	private CoordenadaController coordenadaController;
	

	public UsuarioController () {
		usuarioRepository = new UsuarioRepositoryImpl();
		coordenadaController = new CoordenadaController();
	}
	
	public void addUsuario(Usuario usuario) {
		if(usuario.getMinhasQuests() != null) {
			for(QuestGeolocalizada quest : usuario.getMinhasQuests()){
				coordenadaController.add(quest.getCordenada());
				quest.setCordenada(coordenadaController.find(quest.getCordenada().getLat(),
															quest.getCordenada().getLon()));	
			}
		}
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
