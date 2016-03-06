package br.com.servidor.repository;

import br.com.servidor.model.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario>{

	public Usuario find(String email,String senha);
}
