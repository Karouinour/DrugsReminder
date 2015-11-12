package com.sim.drugsreminder.adapters;



import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sim.drugsreminder.R;
import com.sim.drugsreminder.entities.MenuDrow;






public class MenuCustomAdapter extends ArrayAdapter<MenuDrow> {
	
	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContex;
	  
	  
	  

	  public MenuCustomAdapter(Context context, int resourceId, List<MenuDrow> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContex = context;
	    
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView titre;
		    public ImageView logo;
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
		  
		  vh.logo = (ImageView) rowView.findViewById(R.id.icon_menu);
		  vh.titre = (TextView) rowView.findViewById(R.id.title_menu);
		  
		  
		  //changer la police du titre
		 
		  
		  rowView.setTag(vh);	  
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  MenuDrow menu= getItem(position);
		  
		  holder.titre.setText(menu.getTitle());
		  if (menu.getIcon()==1)
			  holder.logo.setImageResource(R.drawable.ic_action_home1);
		  if (menu.getIcon()==2)
			  holder.logo.setImageResource(R.drawable.ic_action_users2);
		  if (menu.getIcon()==3)
			  holder.logo.setImageResource(R.drawable.ic_action_add_user3);
		  if (menu.getIcon()==4)
			  holder.logo.setImageResource(R.drawable.ic_action_adddrugs4);
		  if (menu.getIcon()==5)
			  holder.logo.setImageResource(R.drawable.warning);
		  if (menu.getIcon()==6)
			  holder.logo.setImageResource(R.drawable.ic_action_setting5);
		  if (menu.getIcon()==7)
			  holder.logo.setImageResource(R.drawable.ic_action_about6);
		 
		 
		 
		 
		  

		  return rowView;
	  }




}
