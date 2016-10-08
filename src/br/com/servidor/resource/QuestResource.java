package br.com.servidor.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidor.controller.QuestController;
import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.utilites.QuestReferenciaCircular;


@Path("/Quest")
public class QuestResource {
	private QuestController Questcontroller;
	private QuestReferenciaCircular questReferenciaCircular;
	
	public QuestResource() {
		Questcontroller = new QuestController();
		questReferenciaCircular = new QuestReferenciaCircular();
	}
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<QuestGeolocalizada> listarTodasCordenadas() {
		return Questcontroller.listarTodos();
	}
	
	@POST
	@Path("/addQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarNovaQuest(QuestGeolocalizada quest) {
		quest = questReferenciaCircular.adicionarReferencaisCirculares(quest);
		Questcontroller.adicionarNovaQuest(quest);
	}
	
	@POST
	@Path("/removeQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removerQuest(QuestGeolocalizada quest) {
		quest = questReferenciaCircular.adicionarReferencaisCirculares(quest);
		Questcontroller.deleteQuest(quest);
	}
	
	@POST
	@Path("/updateQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarQuest(QuestGeolocalizada quest) {
		quest = questReferenciaCircular.adicionarReferencaisCirculares(quest);
		Questcontroller.updateQuest(quest);
	}
	
	@GET
	@Path("/listarProximas/{lat}/{lon}/{raio}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestGeolocalizada> listarCordenadasProximas(@PathParam(value = "lat") String lat,
			@PathParam(value = "lon") String lon, @PathParam(value = "raio") String raio) {
		CordenadaGeografica cordenada = new CordenadaGeografica();
		System.out.println(lat + " " + lon + " " + " " + raio);
		cordenada.setLat(Float.parseFloat(lat));
		cordenada.setLon(Float.parseFloat(lon));
		List<QuestGeolocalizada> quests = Questcontroller.listarProximas(cordenada,Integer.parseInt(raio));
		if(quests!=null && !quests.isEmpty()) {
			for(QuestGeolocalizada quest : quests) {
				if(quest!=null) {
					quest = questReferenciaCircular.removendoReferenciaCircularListUsuario(quest);
				}
			}
			return quests;
		}
		return null;
		
	}

}
