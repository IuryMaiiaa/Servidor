package br.com.servidor.model;

import java.util.ArrayList;

public class QuestGeolocalizada {
	
	private int id;
	
	private String nome;
	
	private ArrayList<Etapa> etapas;
	
	private CordenadaGeografica cordenada;
	
	public void addNovaEtapa(Etapa etapa) {
		etapas.add(etapa);
	}
	
	public void addEtapaPosicao(Etapa etapa, int posicao) {
		etapas.add(posicao, etapa);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(ArrayList<Etapa> etapas) {
		this.etapas = etapas;
	}

	public CordenadaGeografica getCordenada() {
		return cordenada;
	}

	public void setCordenada(CordenadaGeografica cordenada) {
		this.cordenada = cordenada;
	}

}
