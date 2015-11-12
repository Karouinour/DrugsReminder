package com.sim.drugsreminder.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sim.drugsreminder.R;
import com.sim.drugsreminder.entities.AlarmModel;


public class ListAlarmAdapter extends ArrayAdapter<AlarmModel>{

	 private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContex;
	  
	  
	  

	  public ListAlarmAdapter(Context context, int resourceId, List<AlarmModel> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContex = context;
	    
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		
		    public TextView time;
		    public TextView days;
		    
		  }
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  View rowView = convertView;
		  	  
		  //Réutiliser les Views
		  if (rowView==null){
			  rowView = inflater.inflate(resourceId, parent, false);
			  
		  }
		  
		  //configuré le ViewHolder
		  ViewHolder vh = new ViewHolder();
		  
		  vh.time = (TextView) rowView.findViewById(R.id.One_time);
		  vh.days = (TextView) rowView.findViewById(R.id.days_drug);
		  
		  
		  //changer la police du titre
		 
		  
		  rowView.setTag(vh);	  
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  AlarmModel alarm= getItem(position);
		  //TextView txtTime = (TextView) view.findViewById(R.id.alarm_item_time);
			
		  holder.time.setText(String.format("%02d : %02d", alarm.timeHour, alarm.timeMinute));
		  String days="";
		
			  if (alarm.getRepeatingDay(0))
				  days +="SUNDAY, ";
			  if (alarm.getRepeatingDay(1))
				  days +="MONDAY, ";
			  if (alarm.getRepeatingDay(2))
				  days +="TUESDAY, ";
			  if (alarm.getRepeatingDay(3))
				  days +="WEDNESDAY, ";
			  if (alarm.getRepeatingDay(4))
				  days +="THURSDAY, ";
			  if (alarm.getRepeatingDay(5))
				  days +="FRDIAY, ";
			  if (alarm.getRepeatingDay(6))
				  days +="SATURDAY, ";
		
		  holder.days.setText(days);
		 
		  

		  return rowView;
	  }

}
