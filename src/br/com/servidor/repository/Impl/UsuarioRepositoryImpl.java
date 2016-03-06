package br.com.servidor.repository.Impl;

import java.util.HashMap;
import java.util.Map;

import enumeration.QueryType;
import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.model.Usuario;
import br.com.servidor.repository.UsuarioRepository;

public class UsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Usuario> implements UsuarioRepository{

	@Override
	public Usuario find(String email, String senha) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("senha", senha);
		
		Usuario usuario = (Usuario) find(QueryType.JPQL,
				"Select * from usuario where email=:email and senha=:senha", params);
		

		return usuario;
	}

}
