package com.sim.drugsreminder.sqllite;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	//nom des tables
	public static final String TABLE_DRUGS = "drugs";
	public static final String TABLE_USER = "user";
	
	//colonne table User
	public static final String ID_USER = "iduser";
	public static final String NAME_USER = "name";
	public static final String ICON_USER = "icon";
	public static final String PHONE_USER = "phone";
	public static final String EMAIL_ICON = "email";
	
	
	//colonne table drugs
	
	public static final String ID_DRUG = "iddrug";
	public static final String NAME_DRUG = "name";
	public static final String SDATE_DRUG = "sdate";
	public static final String EDATE_DRUG = "edate";
	public static final String TYPE_DRUG = "type_medoc";
	public static final String INSTRUCTION_DRUG = "instruction";
	public static final String DURER_DRUG = "durer";
	public static final String NOMBRE_DURER_DRUG = "nombre_durer";

	
	//Creation table User
	
	private static final String CREATE_USER = "CREATE TABLE " + TABLE_USER + " ("+ 
			ID_USER + " INTEGER, "+
			NAME_USER + " TEXT, "+
			ICON_USER + " TEXT, "+
			PHONE_USER + " TEXT, "+
			EMAIL_ICON + " TEXT);";
	private static final String INSERT_USER = "INSERT INTO " + TABLE_USER + " VALUES ("+"1, "+"'ME', "+"'1' ,"+"'44444444' , "+"'email@email.com'"+");";
	private static final String INSERT_USER2 = "INSERT INTO " + TABLE_USER + " VALUES ("+"2, "+"'User2', "+"'1',"+"'96147282' , "+"'Karouinour@gmail.com'"+");";
	private static final String INSERT_USER3 = "INSERT INTO " + TABLE_USER + " VALUES ("+"3, "+"'User3', "+"'1',"+"'96147282' , "+"'Karouinour@gmail.com'"+");";
	private static final String INSERT_USER4 = "INSERT INTO " + TABLE_USER + " VALUES ("+"4, "+"'User4', "+"'1',"+"'96147282' , "+"'Karouinour@gmail.com'"+");";
	
	
	//Creation table drugs
	private static final String CREATE_DRUG = "CREATE TABLE " + TABLE_DRUGS + " ("+ 
			ID_DRUG+ " INTEGER, "+
			ID_USER + " INTEGER, "+
			NAME_DRUG + " TEXT, "+
			SDATE_DRUG + " TEXT, "+
			EDATE_DRUG + " TEXT, "+
			TYPE_DRUG + " TEXT, "+
			INSTRUCTION_DRUG + " TEXT, "+
			DURER_DRUG + " TEXT, "+
			NOMBRE_DURER_DRUG + " TEXT);";
	
	private static final String INSERT_ME_Drugs1 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"1, "+"1, "+"'Default medicament',"+"'27 nov. 2014',"+"'27 nov. 2014',"+"'Pill',"+"'After meals',"+"'days',"+"'one day');";
	private static final String INSERT_ME_Drugs2 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"2, "+"1, "+"'Efferalgan ME',"+"'29 nov. 2014',"+"'30 nov. 2014',"+"'Pill',"+"'Before meals',"+"'one mouth',"+"'1');";
	
	private static final String INSERT_User2_Drugs1 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"3, "+"2, "+"'Alllergica Nour ',"+"'01 dec. 2014',"+"'03 nov. 2014',"+"'Pill',"+"'After meals',"+"'one mouth',"+"'1');";
	private static final String INSERT_User2_Drugs2 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"4, "+"2, "+"'Efferalgan Nour',"+"'10 dec. 2014',"+"'10 dec. 2014',"+"'Pill',"+"'After meals',"+"'one mouth',"+"'1');";

	private static final String INSERT_User3_Drugs1 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"5, "+"3, "+"'Alllergica ME',"+"'27 nov. 2014',"+"'27 nov. 2014',"+"'Pill',"+"'After meals',"+"'one mouth',"+"'1');";
	private static final String INSERT_User3_Drugs2 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"6, "+"3, "+"'Efferalgan ME',"+"'29 nov. 2014',"+"'30 nov. 2014',"+"'Pill',"+"'Before meals',"+"'one mouth',"+"'1');";
	
	private static final String INSERT_User4_Drugs1 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"7, "+"4, "+"'Alllergica Nour ',"+"'01 dec. 2014',"+"'03 nov. 2014',"+"'Pill',"+"'After meals',"+"'one mouth',"+"'1');";
	private static final String INSERT_User4_Drugs2 = "INSERT INTO " + TABLE_DRUGS + " VALUES ("+"8, "+"4, "+"'Efferalgan Nour',"+"'10 dec. 2014',"+"'10 dec. 2014',"+"'Pill',"+"'After meals',"+"'one mouth',"+"'1');";

	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL(CREATE_USER);
		db.execSQL(INSERT_USER);
		db.execSQL(CREATE_DRUG);
		//db.execSQL(INSERT_ME_Drugs1);
		/*db.execSQL(INSERT_USER2);
		db.execSQL(INSERT_USER3);
		db.execSQL(INSERT_USER4);
		
		
		db.execSQL(INSERT_ME_Drugs2);
		db.execSQL(INSERT_User2_Drugs1);
		db.execSQL(INSERT_User2_Drugs2);
		db.execSQL(INSERT_User3_Drugs1);
		db.execSQL(INSERT_User3_Drugs2);
		db.execSQL(INSERT_User4_Drugs1);
		db.execSQL(INSERT_User4_Drugs2);*/
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
