package com.sim.drugsreminder.entities;

public class MenuDrow {
	
	private String title;
	private int icon;
	
	
	private boolean isCounterVisible = false;
	
	public MenuDrow(){}

	public MenuDrow(String title, int icon){
		this.title = title;
		this.icon = icon;
	}
	
	
	
	public String getTitle(){
		return this.title;
	}
	
	public int getIcon(){
		return this.icon;
	}
	

	
	public boolean getCounterVisibility(){
		return this.isCounterVisible;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	
	
	public void setCounterVisibility(boolean isCounterVisible){
		this.isCounterVisible = isCounterVisible;
	}
}
