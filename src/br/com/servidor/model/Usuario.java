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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"email"}),name="usuario")
@XmlRootElement
public class Usuario {
	
	@Id
	@Column(name = "id_u")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "nacionalidade")
	private String nacionalidade;
	
	@Column(name = "cep")
	private String cep;
	
	@OneToMany(mappedBy = "usuario", targetEntity = QuestGeolocalizada.class, fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	private List<QuestGeolocalizada> minhasQuests;
	
	public void addQuest(QuestGeolocalizada quest) {
		minhasQuests.add(quest);
	}
	
	public void removeQuest(QuestGeolocalizada quest) {
		minhasQuests.remove(quest);
	}

	public List<QuestGeolocalizada> getMinhasQuests() {
		return minhasQuests;
	}

	public void setMinhasQuests(ArrayList<QuestGeolocalizada> minhasQuests) {
		this.minhasQuests = minhasQuests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
