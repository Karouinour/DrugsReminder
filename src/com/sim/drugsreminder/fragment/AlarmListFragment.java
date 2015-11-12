package com.sim.drugsreminder.fragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sim.drugsreminder.R;
import com.sim.drugsreminder.adapters.ListAlarmAdapter;
import com.sim.drugsreminder.entities.AlarmModel;
import com.sim.drugsreminder.entities.Drugs;
import com.sim.drugsreminder.sqllite.AlarmDBHelper;
import com.sim.drugsreminder.sqllite.AlarmDBHelper_temp;
import com.sim.drugsreminder.sqllite.DrugBDD;

public class AlarmListFragment extends Fragment {

	ListView mDrawerListalarm;
	public static ListAlarmAdapter adapter;

	public AlarmListFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mDrawerListalarm = (ListView) inflater.inflate(
				R.layout.fragment_list_alarm, container, false);
		inflater.inflate(R.layout.fragment_list_alarm, container, false);
		// declaration -----------------------------------------------
		List<Drugs> drugs = new ArrayList<Drugs>();
		List<AlarmModel> alarms = new ArrayList<AlarmModel>();
		AlarmDBHelper alarmhelper = new AlarmDBHelper(getActivity()
				.getApplicationContext());
		DrugBDD drugbdd = new DrugBDD(getActivity().getApplicationContext());
		Drugs drug = new Drugs();
		// -------------------------------------------
		drugbdd.open();
		try {
			drugs = drugbdd.selectAllByiduser(getiduserlogin());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drugbdd.close();

		Bundle args = getArguments();
		if (args != null) {
			if (args.containsKey("id")) {
				int index = args.getInt("id");
				drug = drugs.get(index);
			}
			alarms = alarmhelper.getAlarmsByDrugID(drug.getIddrugs());

			if (alarms != null) {
				adapter = new ListAlarmAdapter(getActivity().getBaseContext(),
						R.layout.one_alarm_item, alarms);
				mDrawerListalarm.setAdapter(adapter);
			}
			adapter.notifyDataSetChanged();

			mDrawerListalarm
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// ***********************************************************************
							//Toast.makeText(
									//getActivity().getApplicationContext(),
									//"test", Toast.LENGTH_SHORT).show();
						}
					});
			mDrawerListalarm.setLongClickable(true);
			mDrawerListalarm
					.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							// Toast.makeText(getActivity().getApplicationContext(),
							// "testlong", Toast.LENGTH_SHORT).show();
							AlarmDBHelper_temp i = new AlarmDBHelper_temp(
									getActivity());
							List<AlarmModel> list = new ArrayList<AlarmModel>();
							list = i.getAlarmsTemps();
							//Toast.makeText(
									//getActivity().getApplicationContext(),
									//id + "", Toast.LENGTH_SHORT).show();
							i.deleteAlarm(list.get((int) id).id);
							return false;
						}

					});

		}
		return mDrawerListalarm;

	}

	public void refrech(Context cxt) {
		AlarmDBHelper_temp i = new AlarmDBHelper_temp(getActivity());
		List<AlarmModel> list2 = new ArrayList<AlarmModel>();
		list2 = i.getAlarmsTemps();
		adapter.clear();
		adapter.addAll(list2);
		adapter.notifyDataSetChanged();
	}

	public int getiduserlogin() {
		SharedPreferences pref = getActivity().getSharedPreferences("Userlog",
				0); // Mypref
		String id = pref.getString("userid", "vide");
		return Integer.parseInt(id);

	}

}
