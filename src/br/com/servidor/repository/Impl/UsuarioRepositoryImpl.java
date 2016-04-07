package br.com.servidor.repository.Impl;

import java.util.HashMap;
import java.util.List;
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
		
		List<Usuario> result  = find(QueryType.JPQL,
				"from Usuario where email=:email and senha=:senha", params);
		
		if(result != null && !result.isEmpty()) {
			return result.get(0);
		}
		

		return null;
	}

}
