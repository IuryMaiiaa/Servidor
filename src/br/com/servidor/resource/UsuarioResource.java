package br.com.servidor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidor.controller.UsuarioController;
import br.com.servidor.model.Usuario;

@Path("/Usuario")
public class UsuarioResource {
	private UsuarioController usuarioController;

	public UsuarioResource() {
		usuarioController = new UsuarioController();
	}
	
	@POST
	@Path("/addUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarNovoUsuario(Usuario usuario) {
		System.out.println("chegoaqui");
		usuarioController.addUsuario(usuario);
	}
	
	@GET
	@Path("/getUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(String email,String senha) {
		return usuarioController.getUsuario(email, senha);
	}
}
