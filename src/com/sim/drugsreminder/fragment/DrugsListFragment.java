package com.sim.drugsreminder.fragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sim.drugsreminder.R;
import com.sim.drugsreminder.adapters.DrugsCustomerAdapter;
import com.sim.drugsreminder.entities.Drugs;
import com.sim.drugsreminder.entities.User;
import com.sim.drugsreminder.sqllite.DrugBDD;
import com.sim.drugsreminder.sqllite.UserBDD;


public class DrugsListFragment extends Fragment{

	TextView tx;

	ListView mdroablelisteDrugs;
	List<Drugs> Drugslist = new ArrayList<Drugs>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View r = (View) inflater.inflate(R.layout.fragment_type_list_drugs, container, false);
		mdroablelisteDrugs = (ListView) r.findViewById(R.id.listTypeDrugs);
		ImageView img=(ImageView)r.findViewById(R.id.first_drug);
		//-----------------------------on clic item
		mdroablelisteDrugs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ***********************************************************************
				// selectItem(position);
				AlarmListFragment frg = new AlarmListFragment();
				Bundle args = new Bundle();
				args.putInt("id", position);
				frg.setArguments(args);
				getFragmentManager().beginTransaction()
						.replace(R.id.container, frg)
						.addToBackStack(null).commit();
			}
		});
		
		
		
		
		//--------------------------
		List<User> Userlist = new ArrayList<User>();
		
		UserBDD u = new UserBDD(getActivity().getApplicationContext());
		DrugBDD g = new DrugBDD(getActivity().getApplicationContext());
		u.open();
		try {
			Userlist= u.selectAll();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		User user = new User();
		

		Bundle args = getArguments();
		if (args != null) {
			if (args.containsKey("id")) {
				int index = args.getInt("id");
				user =Userlist.get(index);
				
				
				try {
					Drugslist=g.selectAllByiduser(user.getId());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Drugslist.size() == 0){
					img.setVisibility(View.VISIBLE);
				}
				//-----------------sered preferences---------------------------------------------------------
				SharedPreferences pref = this.getActivity().getSharedPreferences("Userlog", 0); //Mypref
				Editor editor = pref.edit();
				editor.putString("userid", user.getId()+"");
				//Toast.makeText(getActivity().getApplicationContext(), user.getId()+"idtost", Toast.LENGTH_SHORT).show();
				editor.putString("name", user.getName());
				editor.commit();
				
				
				//--------------------------------------------------------------------------------------
			
				getActivity().getActionBar().setTitle(user.getName());
				
					
					mdroablelisteDrugs.setAdapter(new DrugsCustomerAdapter(getActivity().getBaseContext(), R.layout.one_drug, Drugslist));
					
				
				
			}
			
			}
		return r;
		
	}
	/*@Override
	public void onStart() {
		super.onStart();
		List<User> Userlist = new ArrayList<User>();
		
		UserBDD u = new UserBDD(getActivity().getApplicationContext());
		DrugBDD g = new DrugBDD(getActivity().getApplicationContext());
		u.open();
		try {
			Userlist= u.selectAll();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		User user = new User();
		

		Bundle args = getArguments();
		if (args != null) {
			if (args.containsKey("id")) {
				int index = args.getInt("id");
				user =Userlist.get(index);
				//getActivity().getActionBar().setIcon(Utils.actionBarIcon[index]);
				//getActivity().getActionBar().setTitle(Utils.actionBarTitle[index]);
				try {
					Drugslist=g.selectAll();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mdroablelisteDrugs.setAdapter(new DrugsCustomerAdapter(getActivity().getBaseContext(), R.layout.one_drug, Drugslist));
				return mdroablelisteDrugs
				
			
			}
		}
	}*/
		
	
}
