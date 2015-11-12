package com.sim.drugsreminder.sqllite;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sim.drugsreminder.entities.User;


public class UserBDD {

	private static final int VERSION_BDD = 1;
	private static final String NAME_BDD = "user.db";
	public static final String TABLE_USER = "user";
	
	public static final String ID_USER = "iduser";
	public static final String NAME_USER = "name";
	public static final String ICON_USER = "icon";
	public static final String PHONE_USER = "phone";
	public static final String EMAIL_ICON = "email";
	
	
private SQLiteDatabase bdd;
	
	private DBHelper dbHelper;
	
	public UserBDD(Context context) {
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
	
	public long insertTop(User user){
		Cursor cursor = bdd.rawQuery("select max("+DBHelper.ID_USER+") from "+DBHelper.TABLE_USER, null);
		if (cursor.moveToFirst()) user.setId(cursor.getInt(0)+1);
		else
			{
			user.setId(1);
	}
		
		
		//TODO Add Article to data base
		
		
		
		//Création d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				
				values.put(ID_USER, user.getId());
				values.put(NAME_USER, user.getName());
				values.put(ICON_USER, user.getIcon());
				values.put(PHONE_USER, user.getPhone());
				values.put(EMAIL_ICON, user.getEmail());
				
				
				//on insère l'objet dans la BDD via le ContentValues
				return bdd.insert(TABLE_USER, null, values);
		
	}
	public int removeAllUser(){
		//TODO Remove all Table
		return	bdd.delete(TABLE_USER, null, null);
		
	}
	
	
	
	public int removeUser(int index){
		//TODO Remove one Article
		return bdd.delete(TABLE_USER, ID_USER + " = " +index, null);
		
	}
	public List<User> selectAll() throws ParseException {
		open();
	      List<User> list = new ArrayList<User>();
	      
	      //TODO Get list of Article
	      String selectQuery = "SELECT  * FROM " + TABLE_USER;


	      Cursor  cursor = bdd.rawQuery(selectQuery,null);
	      
	      if (cursor.moveToFirst()) {
	    	  
	            while (cursor.isAfterLast() == false) {
	            	User a = new User();
	            	a.setId(cursor.getInt(0)) ;
	                a.setName(cursor.getString(1));
	                a.setIcon(cursor.getString(2));
	                a.setPhone(cursor.getString(3));
	                a.setEmail(cursor.getString(4));
	               
	               
	                
	                list.add(a);
	                cursor.moveToNext();
	            }
	        }
	      
	      
	      return list;
	   }
	public User selectUserById(int id) throws ParseException {
		open();
	     
	      
	      //TODO Get list of Article
	      String selectQuery = "SELECT  * FROM " + TABLE_USER +"WHERE "+ID_USER+" = "+id;


	      Cursor  cursor = bdd.rawQuery(selectQuery,null);
	      
	      if (cursor.moveToFirst()) {
	    	  
	            while (cursor.isAfterLast() == false) {
	            	User a = new User();
	            	a.setId(cursor.getInt(0)) ;
	                a.setName(cursor.getString(1));
	                a.setIcon(cursor.getString(2));
	                a.setPhone(cursor.getString(3));
	                a.setEmail(cursor.getString(4));
	               
	                return a;
	            }
	        }
	      
	      
	      return null;
	   }
	
}
