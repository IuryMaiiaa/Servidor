package br.com.servidor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Path("/getUsuario/{email}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam(value = "email") String email,@PathParam(value = "senha") String senha) {
		System.out.println(email + "  " + senha);
		Usuario usuario = usuarioController.getUsuario(email, senha);
		usuario = usuarioReferenciaCircular.removendoReferenciasCirculares(usuario);
		return usuario;
	}
	
	@POST
	@Path("/removeUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletarUsuario(Usuario usuario) {
		usuario = usuarioReferenciaCircular.adicionandoReferenciasCirculares(usuario);
		usuarioController.deleteUsuario(usuario);
	}
	
	
	@POST
	@Path("/updateUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarUsuario(Usuario usuario) {
		usuario = usuarioReferenciaCircular.adicionandoReferenciasCirculares(usuario);
		usuarioController.updateUsuario(usuario);
	}
	
	
}
