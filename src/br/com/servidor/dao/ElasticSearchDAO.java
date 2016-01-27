package br.com.servidor.dao;

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

	public void chamarNode() {
		Node node = NodeBuilder.nodeBuilder().clusterName("elasticsearch")
				.node();
		System.out.println("passo");
		Client client = node.client();

		//client.prepareIndex("cordenadas", "cordenada");
				//.setSource(putJsonDocument(1, 10.000, 20.000)).execute()
				//.actionGet();
		System.out.println("passo");

		getDocument(client, "cordenadas", "cordenada", "1");
		
		updateDocument(client, "cordenadas", "cordenada", "1", "id", "20");
		
		getDocument(client, "cordenadas", "cordenada", "1");

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

	public static void getDocument(Client client, String index, String type,
			String id) {

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

	public static void updateDocument(Client client, String index, String type,
			String id, String field, String newValue) {
		
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(index);
		updateRequest.type(type);
		updateRequest.id(id);
		updateRequest.doc(putJsonDocument(Integer.parseInt(newValue), 20, 12));
		try {
			client.update(updateRequest).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionarCordenada(CordenadaGeografica aux) {
		Client client = ESClientProvider.instance().getClient();
		
		client.prepareIndex("cordenadas", "cordenada")
			.setSource(putJsonDocument(aux.getID(), aux.getLat(), aux.getLon())).execute()
			.actionGet();
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

}
