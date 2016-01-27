package br.com.servidor.factory;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class ESClientProvider {
	private static ESClientProvider instance = null;
	private static Object lock = new Object();

	private Client client;
	private Node node;

	public static ESClientProvider instance() {

		if (instance == null) {
			synchronized (lock) {
				if (null == instance) {
					instance = new ESClientProvider();
				}
			}
		}
		return instance;
	}

	public void prepareClient() {
		node = nodeBuilder().nodeBuilder().clusterName("elasticsearch")
				.node();
		client = node.client();
	}

	public void closeNode() {

		if (!node.isClosed())
			node.close();

	}

	public Client getClient() {
		return client;
	}

	public void printThis() {
		System.out.println(this);
	}

}
