package com.sim.drugsreminder.sqllite;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sim.drugsreminder.entities.Drugs;



public class DrugBDD {
	private static final int VERSION_BDD = 1;
	private static final String NAME_BDD = "drugs.db";
	public static final String TABLE_DRUGS = "drugs";

	public static final String ID_USER = "iduser";
	public static final String ID_DRUG = "iddrug";
	public static final String NAME_DRUG = "name";
	public static final String SDATE_DRUG = "sdate";
	public static final String EDATE_DRUG = "edate";
	public static final String TYPE_DRUG = "type_medoc";
	public static final String INSTRUCTION_DRUG = "instruction";
	public static final String DURER_DRUG = "durer";
	public static final String NOMBRE_DURER_DRUG = "nombre_durer";
	
private SQLiteDatabase bdd;
	
	private DBHelper dbHelper;
	
	public DrugBDD(Context context) {
		super();
		
		dbHelper = new DBHelper(context, NAME_BDD, null, VERSION_BDD);
	}
	public void open(){
		//TODO Open Data Base
		bdd = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		//TODO Close Data Base
		bdd.close();
	}
	
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public long insertTop(Drugs drugs){
		Cursor cursor = bdd.rawQuery("select max("+DBHelper.ID_DRUG+") from "+DBHelper.TABLE_DRUGS, null);
		if (cursor.moveToFirst()) drugs.setIddrugs(cursor.getInt(0)+1);
		else
			{drugs.setIddrugs(1);
	}
		//TODO Add Article to data base
		
		//Création d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				
				values.put(ID_DRUG, drugs.getIddrugs());
				values.put(ID_USER, drugs.getIduser());
				values.put(NAME_DRUG, drugs.getName());
				values.put(SDATE_DRUG, drugs.getStartdate());
				values.put(EDATE_DRUG, drugs.getEnddate());
				values.put(TYPE_DRUG, drugs.getType_medoc());
				values.put(INSTRUCTION_DRUG, drugs.getInstruction());
				values.put(DURER_DRUG, drugs.getDurer());
				values.put(NOMBRE_DURER_DRUG, drugs.getNombre_durer());
				
				
				//on insère l'objet dans la BDD via le ContentValues
				return bdd.insert(TABLE_DRUGS, null, values);
		
	}
	public int removeAllDrugs(){
		//TODO Remove all Table
		
		return	bdd.delete(TABLE_DRUGS, null, null);
		
		
	}
	
	
	
	public int removeDrugs(int index){
		//TODO Remove one Article
		return bdd.delete(TABLE_DRUGS, ID_DRUG + " = " +index, null);
		
	}
	public List<Drugs> selectAll() throws ParseException {
		open();
	      List<Drugs> list = new ArrayList<Drugs>();
	      
	      //TODO Get list of Article
	      String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;


	      Cursor  cursor = bdd.rawQuery(selectQuery,null);
	      
	      if (cursor.moveToFirst()) {
	    	  
	            while (cursor.isAfterLast() == false) {
	            	Drugs a = new Drugs();
	            	a.setIddrugs(cursor.getInt(0));
	                a.setIduser(cursor.getInt(1));
	                a.setName(cursor.getString(2));
	                a.setStartdate(cursor.getString(3));
	                a.setEnddate(cursor.getString(4));
	                a.setType_medoc(cursor.getString(5));
	                a.setInstruction(cursor.getString(6));
	                a.setDurer(cursor.getString(7));
	                a.setNombre_durer(cursor.getString(8));
	               
	               
	                
	                list.add(a);
	                cursor.moveToNext();
	            }
	        }
	      
	      
	      return list;
	   }
	public List<Drugs> selectAllByiduser( int id) throws ParseException {
		open();
	      List<Drugs> list = new ArrayList<Drugs>();
	    
	      
	      //TODO Get list of Article
	      String selectQuery = "SELECT  * FROM " + TABLE_DRUGS +" WHERE "+ID_USER+" = "+id;


	      Cursor  cursor = bdd.rawQuery(selectQuery,null);
	      
	      if (cursor.moveToFirst()) {
	    	  
	            while (cursor.isAfterLast() == false) {
	            	Drugs a = new Drugs();
	            	a.setIddrugs(cursor.getInt(0));
	                a.setIduser(cursor.getInt(1));
	                a.setName(cursor.getString(2));
	                a.setStartdate(cursor.getString(3));
	                a.setEnddate(cursor.getString(4));
	                a.setType_medoc(cursor.getString(5));
	                a.setInstruction(cursor.getString(6));
	                a.setDurer(cursor.getString(7));
	                a.setNombre_durer(cursor.getString(8));
	               
	             
	               
	                
	                list.add(a);
	                cursor.moveToNext();
	            }
	        }
	      
	      
	      return list;
	   }
	
	public Drugs selectDrugByid(int id) throws ParseException {
		open();
	     
	      
	      //TODO Get list of Article
	      String selectQuery = "SELECT  * FROM " + TABLE_DRUGS +" WHERE "+ID_DRUG+" = "+id;


	      Cursor  cursor = bdd.rawQuery(selectQuery,null);
	      
	      if (cursor.moveToFirst()) {
	    	  
	            while (cursor.isAfterLast() == false) {
	            	Drugs a = new Drugs();
	            	a.setIddrugs(cursor.getInt(0));
	                a.setIduser(cursor.getInt(1));
	                a.setName(cursor.getString(2));
	                a.setStartdate(cursor.getString(3));
	                a.setEnddate(cursor.getString(4));
	                a.setType_medoc(cursor.getString(5));
	                a.setInstruction(cursor.getString(6));
	                a.setDurer(cursor.getString(7));
	                a.setNombre_durer(cursor.getString(8));
	               
	                return a;
	            }
	        }
	      
	      
	      return null;
	   }
	
}
