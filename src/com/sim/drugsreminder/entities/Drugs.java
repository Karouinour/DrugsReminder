package com.sim.drugsreminder.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Drugs {
	
	
	private int iddrugs;
	private int iduser;
	private String name;
	private String Startdate;
	private String enddate;
	private String Type_medoc;
	private String instruction;
	private String durer;
	private String nombre_durer;
	
	private User user;
	
	
	
public Drugs(){
		
	}
	




	public Drugs(int iddrugs, int iduser, String name, String startdate,
		String enddate, String type_medoc, String instruction, String durer,
		String nombre_durer, User user) {
	super();
	this.iddrugs = iddrugs;
	this.iduser = iduser;
	this.name = name;
	Startdate = startdate;
	this.enddate = enddate;
	Type_medoc = type_medoc;
	this.instruction = instruction;
	this.durer = durer;
	this.nombre_durer = nombre_durer;
	this.user = user;
}





	public String getStartdate() {
		return Startdate;
	}
	public void setStartdate(String startdate) {
		Startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	

	public int getIddrugs() {
		return iddrugs;
	}

	public void setIddrugs(int iddrugs) {
		this.iddrugs = iddrugs;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getType_medoc() {
		return Type_medoc;
	}





	public void setType_medoc(String type_medoc) {
		Type_medoc = type_medoc;
	}





	public String getDurer() {
		return durer;
	}





	public void setDurer(String durer) {
		this.durer = durer;
	}





	public String getNombre_durer() {
		return nombre_durer;
	}





	public void setNombre_durer(String nombre_durer) {
		this.nombre_durer = nombre_durer;
	}





	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getTime(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        return dateFormat.format(d);
	}
	public String getDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        
        return dateFormat.format(d);
	}
	public Date SetTime(String s) throws ParseException{
		 SimpleDateFormat dateFormat = new SimpleDateFormat(
	                "HH:mm");
	        return dateFormat.parse(s);
	}
	
	public Date SetDate(String s) throws ParseException{
		 SimpleDateFormat dateFormat = new SimpleDateFormat(
	                "yyyy-MM-dd");
	        return dateFormat.parse(s);
	}
	
	

}
