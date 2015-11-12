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
import com.sim.drugsreminder.entities.User;



public class UserCustomAdapter extends ArrayAdapter<User> {
	
	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContex;
	  
	  
	  

	  public UserCustomAdapter(Context context, int resourceId, List<User> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContex = context;
	    
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView nom;
		    public TextView phone;
		    public TextView email;
		    public ImageView image;
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
		  
		  vh.image = (ImageView) rowView.findViewById(R.id.imguser);
		  vh.nom = (TextView) rowView.findViewById(R.id.nomuser);
		  vh.phone = (TextView) rowView.findViewById(R.id.phone_user);
		  vh.email = (TextView) rowView.findViewById(R.id.email_user);
		  
		  
		  //changer la police du titre
		 
		  
		  rowView.setTag(vh);	  
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  User user= getItem(position);
		  
		  holder.nom.setText(user.getName());
		  if (user.getIcon().equals("1"))
		  holder.image.setImageResource(R.drawable.homme);
		  holder.phone.setText(user.getPhone()+"");
		  holder.email.setText(user.getEmail());
		  if (user.getIcon().equals("2"))
		  holder.image.setImageResource(R.drawable.femme);
		  holder.phone.setText(user.getPhone()+"");
		  holder.email.setText(user.getEmail());
		  if (user.getIcon().equals("3"))
			  holder.image.setImageResource(R.drawable.dog_icon);
		  if (user.getIcon().equals("4"))
			  holder.image.setImageResource(R.drawable.cat_icon);
		  if (user.getIcon().equals("5"))
			  holder.image.setImageResource(R.drawable.bird_icon);
		  if (user.getIcon().equals("6"))
			  holder.image.setImageResource(R.drawable.horse_icon);
		  if (user.getIcon().equals("7"))
			  holder.image.setImageResource(R.drawable.rabbit_icon);
		
		 
		  

		  return rowView;
	  }

}
