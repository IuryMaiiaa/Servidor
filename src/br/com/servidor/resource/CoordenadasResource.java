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

import br.com.servidor.controller.CoordenadaController;
import br.com.servidor.model.CoordenadaGeografica;


@Path("/Cordenada")
public class CoordenadasResource {
	private CoordenadaController controllerCordenadas;
	
	public CoordenadasResource() {
		controllerCordenadas = new CoordenadaController();
	}
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CoordenadaGeografica> listarTodasCordenadas() {
		return controllerCordenadas.listarTodos();
	}
	
	@POST
	@Path("/addCoordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void adicionarCordenada(CoordenadaGeografica coordenadaGeografica) {
		controllerCordenadas.add(coordenadaGeografica);
	}
	
	@POST
	@Path("/updateCoordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarCordenada(CoordenadaGeografica cordenada) {
		controllerCordenadas.update(cordenada);
	}
	
	@POST
	@Path("/removeCordenada")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removerCordenada(CoordenadaGeografica cordenada) {
		controllerCordenadas.remove(cordenada);
	}
	
	@GET
	@Path("/listarProximas")
	@Produces("aplication/json")
	public ArrayList<CoordenadaGeografica> listarCordenadasProximas(CoordenadaGeografica cordenada,int raio) {
		return controllerCordenadas.listarProximas(cordenada,raio);
	}

}
