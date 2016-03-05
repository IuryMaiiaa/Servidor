package br.com.servidor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;




/**
 * 
 * @author Iury
 * 
 * 
 * classe responsavel por conter os atributos das cordenadas geograficas. 
 *
 */
@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"lat", "lon"}),name="CordenadaGeografica")
@XmlRootElement
public class CordenadaGeografica {
	
	@Id
	@Column(name = "id_c")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name="lat")
	private double lat;
	
	@Column(name="lon")
	private double lon;
	
	
	public CordenadaGeografica() {
		
	}
	public CordenadaGeografica(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	
	public int getID() {
		return ID;
	}
	
	
	public void setID(int iD) {
		ID = iD;
	}
	
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public String getIdString() {
		return ID + "";
	}

}
