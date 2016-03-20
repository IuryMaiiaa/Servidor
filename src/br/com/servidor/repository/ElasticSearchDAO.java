package br.com.servidor.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.*;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.script.Script;

import br.com.servidor.factory.ESClientProvider;
import br.com.servidor.model.CordenadaGeografica;

public class ElasticSearchDAO {
	private static String index="cordenadas";
	private static String type="cordenada";
	
	private static ElasticSearchDAO instace;
	
	public static ElasticSearchDAO getInstance() {
		if(instace == null) {
			instace = new ElasticSearchDAO();;
		}
		return instace;
	}

	public void chamarNode() {
		Node node = NodeBuilder.nodeBuilder().clusterName("elasticsearch")
				.node();
		System.out.println("passo");
		Client client = node.client();

		//client.prepareIndex("cordenadas", "cordenada");
				//.setSource(putJsonDocument(1, 10.000, 20.000)).execute()
				//.actionGet();
		System.out.println("passo");

		getDocument(client,  "1");
		
		//updateDocument(client, "cordenadas", "cordenada", "1", "id", "20");
		
		getDocument(client,  "1");

		node.close();
	}

	public static Map<String, Object> putJsonDocument(int id, double latitude,
			double longitude) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();

		GeoPoint geoPoint = new GeoPoint();
		geoPoint.reset(latitude, longitude);
		jsonDocument.put("id", id);
		jsonDocument.put("GeoPoint", geoPoint);

		return jsonDocument;
	}

	public static void getDocument(Client client, String id) {

		GetResponse getResponse = client.prepareGet(index, type, id).execute()
				.actionGet();
		Map<String, Object> source = getResponse.getSource();

		System.out.println("------------------------------");
		System.out.println("Index: " + getResponse.getIndex());
		System.out.println("Type: " + getResponse.getType());
		System.out.println("Id: " + getResponse.getId());
		System.out.println("Version: " + getResponse.getVersion());
		System.out.println(source);
		System.out.println("------------------------------");

	}

	private static void updateDocument(Client client, String index, String type,CordenadaGeografica cordenada) {
		getDocument(ESClientProvider.instance().getClient(), cordenada.getIdString());
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(cordenada.getIdString());
		updateRequest.doc(putJsonDocument(cordenada.getID(),cordenada.getLat(),cordenada.getLon()));
		try {
			client.update(updateRequest).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDocument(ESClientProvider.instance().getClient(), cordenada.getIdString());
		
	}

	public void adicionarCordenada(CordenadaGeografica aux) {
		
		Client client = ESClientProvider.instance().getClient();
		System.out.println(putJsonDocument(aux.getID(), aux.getLat(), aux.getLon()).toString());
		client.prepareIndex("cordenadas", "cordenada",Integer.toString(aux.getID()))
			.setSource(putJsonDocument(aux.getID(), aux.getLat(), aux.getLon())).execute()
			.actionGet();
		System.out.println(aux.getID());
		getDocument(client, Integer.toString( aux.getID()));
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		Client client = ESClientProvider.instance().getClient();
		SearchResponse response = client.prepareSearch("cordenadas")
		        .setTypes("cordenada")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
		        .setPostFilter(QueryBuilders.geoDistanceQuery("GeoPoint").distance("20"))     // Filter
		        .setFrom(0).setSize(60).setExplain(true)
		        .execute()
		        .actionGet();
		
		
		return null;
	}

	public void updateCordenada(CordenadaGeografica cordenada) {
		// TODO Auto-generated method stub
		
	}

	public void deletarCordenada(CordenadaGeografica cordenada) {
		// TODO Auto-generated method stub
		
	}

}
