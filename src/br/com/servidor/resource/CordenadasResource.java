package br.com.servidor.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.impl.provider.header.NewCookieProvider;

import br.com.servidor.controller.CordenadaController;
import br.com.servidor.model.CordenadaGeografica;


@Path("/Cordenada")
public class CordenadasResource {
	private CordenadaController controllerCordenadas;
	
	public CordenadasResource() {
		controllerCordenadas = new CordenadaController();
	}
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CordenadaGeografica> listarTodasCordenadas() {
		return controllerCordenadas.listarTodos();
	}
	
	@POST
	@Path("/addCordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void adicionarCordenada(CordenadaGeografica cordenadaGeografica) {
		controllerCordenadas.add(cordenadaGeografica);
	}
	
	@POST
	@Path("/updateCordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarCordenada(CordenadaGeografica cordenada) {
		controllerCordenadas.update(cordenada);
	}
	
	@POST
	@Path("/removeCordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removerCordenada(CordenadaGeografica cordenada) {
		controllerCordenadas.remove(cordenada);
	}
	
	@GET
	@Path("/listarProximas")
	@Produces("aplication/json")
	public ArrayList<CordenadaGeografica> listarCordenadasProximas(CordenadaGeografica cordenada,int raio) {
		return controllerCordenadas.listarProximas(cordenada,raio);
	}

}
