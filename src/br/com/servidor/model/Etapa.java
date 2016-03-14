package br.com.servidor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="etapa")
@XmlRootElement
public class Etapa {
	
	@Id
	@Column(name = "id_e")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="posicao")
	private int posicao;

	@Column(name="descricao")
	private String descricao;
	
	@Column(name="dica")
	private String dica;
	
	@Column(name="palavraChave")
	private String palavraChave;
	
	@ManyToOne
	@JoinColumn(name = "id_quest")
	private QuestGeolocalizada questGeolocalizada;
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	
	public QuestGeolocalizada getQuestGeolocalizada() {
		return questGeolocalizada;
	}

	public void setQuestGeolocalizada(QuestGeolocalizada questGeolocalizada) {
		this.questGeolocalizada = questGeolocalizada;
	}

	public boolean comparaPalavraChave(String chave) {
		if(chave.equals(palavraChave)) {
			return true;
		}
		return false;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

}
