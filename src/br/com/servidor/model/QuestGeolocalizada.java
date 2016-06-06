package br.com.servidor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="questgeolocalizada")
@XmlRootElement
public class QuestGeolocalizada {
	
	@Id
	@Column(name = "id_qu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="descricao")
	private String descricao;
	
	@OneToMany(mappedBy = "questGeolocalizada", targetEntity = Etapa.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Etapa> etapas;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_cordenada")
	private CordenadaGeografica cordenada;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}
	
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

	public List<Etapa> getEtapas() {
		return etapas;
	}

	public CordenadaGeografica getCordenada() {
		return cordenada;
	}

	public void setCordenada(CordenadaGeografica cordenada) {
		this.cordenada = cordenada;
	}

}
