package br.com.servidor.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.lucene.queryparser.xml.QueryBuilder;
import org.elasticsearch.*;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.google.gson.Gson;

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

		GetResponse getResponse = client.prepareGet("cordenadas", "cordenada", id).execute()
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
		Gson gson = new Gson();
		Client client = ESClientProvider.instance().getClient();
		System.out.println(putJsonDocument(aux.getID(), aux.getLat(), aux.getLon()).toString());
		/*client.admin().indices().preparePutMapping("cordenadas")
								.setType("cordenada")
								.setSource("{\n" +                              
						                "  \"GeoPoint\": {\n" +
						                "      \"type\": \"geo_point\"\n" +
						                "  }\n" +
						                "}").get();
		
		*/
		
		client.prepareIndex("cordenadas", "cordenada",Integer.toString(aux.getID()))
			.setSource(putJsonDocument(aux.getID(), aux.getLat(), aux.getLon())).execute()
			.actionGet();
		System.out.println(aux.getID());
		getDocument(client, Integer.toString(aux.getID()));
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		Client client = ESClientProvider.instance().getClient();
		ArrayList<CordenadaGeografica> cordenadas = new ArrayList<CordenadaGeografica>();
		Gson gson = new Gson();
		 //getDocument(client, "1");
		/*client.admin().indices().prepareCreate("cordenadas").get();
		client.admin().indices().preparePutMapping("cordenadas")
		.setType("cordenada")
		.setSource("{\n" +                              
                "  \"properties\": {\n" +
				"       \"GeoPoint\": {\n"  +      
                "      		\"type\": \"geo_point\"\n" +
                "  		}\n" +
                "  }\n" +
                "}").get();
          */
		GeoDistanceQueryBuilder qb = new GeoDistanceQueryBuilder("GeoPoint")
													.point(cordenada.getLat(), cordenada.getLon())                                 
			    									.distance(raio, DistanceUnit.KILOMETERS)         
			    									.optimizeBbox("memory")                         
			    									.geoDistance(GeoDistance.ARC);
		
		SearchResponse response = client.prepareSearch("cordenadas")
		        .setTypes("cordenada")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(qb)                 // Query
		        .execute()
		        .actionGet();
		
		CordenadaGeografica cordenadaAux = new CordenadaGeografica();
		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
		System.out.println(hits.length);
		for(SearchHit hit : hits) {
			cordenadaAux = gson.fromJson(hit.getSource().toString(), CordenadaGeografica.class);
			cordenadas.add(cordenadaAux);
		}

		return cordenadas;
	}

	public void updateCordenada(CordenadaGeografica cordenada) {
		Client client = ESClientProvider.instance().getClient();
		System.out.println(putJsonDocument(cordenada.getID(), cordenada.getLat(), cordenada.getLon()).toString());
		try {
			client.prepareUpdate("cordenadas", "cordenada",Integer.toString(cordenada.getID()))
						.setSource(putJsonDocument(cordenada.getID(), cordenada.getLat(), cordenada.getLon()).toString().getBytes())
						.execute().actionGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cordenada.getID());
		getDocument(client, Integer.toString( cordenada.getID()));
		
	}

	public void deletarCordenada(CordenadaGeografica cordenada) {
		Client client = ESClientProvider.instance().getClient();
		System.out.println(putJsonDocument(cordenada.getID(), cordenada.getLat(), cordenada.getLon()).toString());
		try {
			client.prepareDelete("cordenadas", "cordenada",Integer.toString(cordenada.getID()))
						.execute().actionGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cordenada.getID());
		getDocument(client, Integer.toString( cordenada.getID()));
		
	}

}
