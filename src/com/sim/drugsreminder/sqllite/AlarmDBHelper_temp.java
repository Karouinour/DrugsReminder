package com.sim.drugsreminder.sqllite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.sim.drugsreminder.entities.AlarmModel;



public class AlarmDBHelper_temp extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "alarmtemps.db";
    public static final String TABLE_NAME = "alarmtemps";
    public static final String _ID = "_id";
	public static final String COLUMN_NAME_ID_DRUG = "id_drug";
	public static final String COLUMN_NAME_ALARM_NAME = "name";
	public static final String COLUMN_NAME_ALARM_TIME_HOUR = "hour";
	public static final String COLUMN_NAME_ALARM_TIME_MINUTE = "minute";
	public static final String COLUMN_NAME_ALARM_REPEAT_DAYS = "days";
	public static final String COLUMN_NAME_ALARM_REPEAT_WEEKLY = "weekly";
	public static final String COLUMN_NAME_ALARM_TONE = "tone";
	public static final String COLUMN_NAME_ALARM_ENABLED = "enabled";

	
	private static final String SQL_CREATE_ALARM = "CREATE TABLE " + TABLE_NAME + " (" +
			_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			COLUMN_NAME_ID_DRUG + " INTEGER," +
			COLUMN_NAME_ALARM_NAME + " TEXT," +
			COLUMN_NAME_ALARM_TIME_HOUR + " INTEGER," +
			COLUMN_NAME_ALARM_TIME_MINUTE + " INTEGER," +
			COLUMN_NAME_ALARM_REPEAT_DAYS + " TEXT," +
			COLUMN_NAME_ALARM_REPEAT_WEEKLY + " BOOLEAN," +
			COLUMN_NAME_ALARM_TONE + " TEXT," +
			COLUMN_NAME_ALARM_ENABLED + " BOOLEAN" +
	    " )";
	
	private static final String SQL_DELETE_ALARM =
		    "DROP TABLE IF EXISTS " + TABLE_NAME;
    
	public AlarmDBHelper_temp(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ALARM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ALARM);
        onCreate(db);
	}
	
	private AlarmModel populateModel(Cursor c) {
		AlarmModel model = new AlarmModel();
		model.id = c.getLong(c.getColumnIndex(_ID));
		model.id_drug = c.getInt(c.getColumnIndex(COLUMN_NAME_ID_DRUG));
		model.name = c.getString(c.getColumnIndex(COLUMN_NAME_ALARM_NAME));
		model.timeHour = c.getInt(c.getColumnIndex(COLUMN_NAME_ALARM_TIME_HOUR));
		model.timeMinute = c.getInt(c.getColumnIndex(COLUMN_NAME_ALARM_TIME_MINUTE));
		model.repeatWeekly = c.getInt(c.getColumnIndex(COLUMN_NAME_ALARM_REPEAT_WEEKLY)) == 0 ? false : true;
		model.alarmTone = c.getString(c.getColumnIndex(COLUMN_NAME_ALARM_TONE)) != "" ? Uri.parse(c.getString(c.getColumnIndex(COLUMN_NAME_ALARM_TONE))) : null;
		model.isEnabled = c.getInt(c.getColumnIndex(COLUMN_NAME_ALARM_ENABLED)) == 0 ? false : true;
		
		String[] repeatingDays = c.getString(c.getColumnIndex(COLUMN_NAME_ALARM_REPEAT_DAYS)).split(",");
		for (int i = 0; i < repeatingDays.length; ++i) {
			model.setRepeatingDay(i, repeatingDays[i].equals("false") ? false : true);
		}
		
		return model;
	}
	
	private ContentValues populateContent(AlarmModel model) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_ID_DRUG, model.id_drug);
        values.put(COLUMN_NAME_ALARM_NAME, model.name);
        values.put(COLUMN_NAME_ALARM_TIME_HOUR, model.timeHour);
        values.put(COLUMN_NAME_ALARM_TIME_MINUTE, model.timeMinute);
        values.put(COLUMN_NAME_ALARM_REPEAT_WEEKLY, model.repeatWeekly);
        values.put(COLUMN_NAME_ALARM_TONE, model.alarmTone != null ? model.alarmTone.toString() : "");
        values.put(COLUMN_NAME_ALARM_ENABLED, model.isEnabled);
        
        String repeatingDays = "";
        for (int i = 0; i < 7; ++i) {
        	repeatingDays += model.getRepeatingDay(i) + ",";
        }
        values.put(COLUMN_NAME_ALARM_REPEAT_DAYS, repeatingDays);
        
        return values;
	}
	
	public long createAlarmTemps(AlarmModel model) {
		ContentValues values = populateContent(model);
        return getWritableDatabase().insert(TABLE_NAME, null, values);
	}
	
	public long updateAlarmTemps(AlarmModel model) {
		ContentValues values = populateContent(model);
        return getWritableDatabase().update(TABLE_NAME, values, _ID + " = ?", new String[] { String.valueOf(model.id) });
	}
	
	public AlarmModel getAlarmTemps(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + " = " + id;
		
		Cursor c = db.rawQuery(select, null);
		
		if (c.moveToNext()) {
			return populateModel(c);
		}
		
		return null;
	}
	
	public List<AlarmModel> getAlarmsTemps() {
		SQLiteDatabase db = this.getReadableDatabase();
		
        String select = "SELECT * FROM " + TABLE_NAME;
		
		Cursor c = db.rawQuery(select, null);
		
		List<AlarmModel> alarmList = new ArrayList<AlarmModel>();
		
		while (c.moveToNext()) {
			alarmList.add(populateModel(c));
		}
		
		if (!alarmList.isEmpty()) {
			return alarmList;
		}
		
		return null;
	}
	public List<AlarmModel> getAlarmsByDrugIDTemps(int iddrug) {
		SQLiteDatabase db = this.getReadableDatabase();
		
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_ID_DRUG + " = " + iddrug;
		
		Cursor c = db.rawQuery(select, null);
		
		List<AlarmModel> alarmList = new ArrayList<AlarmModel>();
		
		while (c.moveToNext()) {
			alarmList.add(populateModel(c));
		}
		
		if (!alarmList.isEmpty()) {
			return alarmList;
		}
		
		return null;
	}
	
	public int deleteAlarm(long id) {
		return getWritableDatabase().delete(TABLE_NAME, _ID + " = ?", new String[] { String.valueOf(id) });
	}
}
