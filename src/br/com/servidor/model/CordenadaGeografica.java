package br.com.servidor.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author Iury
 * 
 * 
 * classe responsavel por conter os atributos das cordenadas geograficas. 
 *
 */

@XmlRootElement
public class CordenadaGeografica {
	
	private int ID;
	private double lat;
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

}
