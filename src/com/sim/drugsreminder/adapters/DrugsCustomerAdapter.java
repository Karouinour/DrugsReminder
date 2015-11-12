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
import com.sim.drugsreminder.StringsText;
import com.sim.drugsreminder.entities.Drugs;




public class DrugsCustomerAdapter extends ArrayAdapter<Drugs> {
	
	  private int resourceId = 0;
	  private LayoutInflater inflater;
	  public Context mContex;
	  
	  
	  

	  public DrugsCustomerAdapter(Context context, int resourceId, List<Drugs> mediaItems) {
	    super(context, 0, mediaItems);
	    this.resourceId = resourceId;
	    this.mContex = context;
	    
	    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }
	  
	  //ViewHolder Design Pattern
	  static class ViewHolder {
		    public TextView nom;
		    public ImageView drugs_icon;
		    public TextView instruction;
		    public TextView durer;
		    public TextView type;
		    
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
		  
		 
		  vh.drugs_icon = (ImageView) rowView.findViewById(R.id.imgdrug);
		  vh.nom = (TextView) rowView.findViewById(R.id.drugnom);
		  vh.instruction = (TextView) rowView.findViewById(R.id.instruction_drug);
		  vh.durer = (TextView) rowView.findViewById(R.id.durer_drug);
		  
		  
		  //changer la police du titre
		 
		  
		  rowView.setTag(vh);	  
		  
		  //Affecter les données aux Views
		  ViewHolder holder = (ViewHolder) rowView.getTag();
		  Drugs drug= getItem(position);
		  
		  holder.nom.setText(drug.getName());
		  holder.durer.setText(drug.getNombre_durer());
		  holder.instruction.setText(drug.getInstruction());
		  if (drug.getType_medoc().equals(StringsText.a3_Vial))
			  holder.drugs_icon.setImageResource(R.drawable.vial);
		  if (drug.getType_medoc().equals(StringsText.a4_Pomade))
			  holder.drugs_icon.setImageResource(R.drawable.pomade);
		  if (drug.getType_medoc().equals(StringsText.a5_Syrup))
			  holder.drugs_icon.setImageResource(R.drawable.syrup);
		  if (drug.getType_medoc().equals(StringsText.a6_Injection))
			  holder.drugs_icon.setImageResource(R.drawable.injection);
		  if (drug.getType_medoc().equals(StringsText.a7_Pill))
			  holder.drugs_icon.setImageResource(R.drawable.pill);
		  
		 
		  

		  return rowView;
	  }

}
