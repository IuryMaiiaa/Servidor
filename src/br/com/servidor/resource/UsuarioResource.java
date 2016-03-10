package br.com.servidor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidor.controller.UsuarioController;
import br.com.servidor.model.Usuario;
import br.com.servidor.utilites.UsuarioReferenciaCircular;

@Path("/Usuario")
public class UsuarioResource {
	private UsuarioController usuarioController;
	private UsuarioReferenciaCircular usuarioReferenciaCircular;

	public UsuarioResource() {
		usuarioController = new UsuarioController();
		usuarioReferenciaCircular = new UsuarioReferenciaCircular();
	}
	
	@POST
	@Path("/addUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarNovoUsuario(Usuario usuario) {
		System.out.println("chego aqui");
		usuario = usuarioReferenciaCircular.adicionandoReferenciasCirculares(usuario);
		usuarioController.addUsuario(usuario);
	}
	
	@GET
	@Path("/getUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(String email,String senha) {
		return usuarioController.getUsuario(email, senha);
	}
}
