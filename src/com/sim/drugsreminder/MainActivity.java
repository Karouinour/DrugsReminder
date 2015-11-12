package com.sim.drugsreminder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonFloat;
import com.sim.drugsreminder.entities.User;
import com.sim.drugsreminder.fragment.NavigationDrawerFragment;
import com.sim.drugsreminder.sqllite.UserBDD;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,OnClickListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	
	private CharSequence miduser;
	List<User> Userlist = new ArrayList<User>();
	private ButtonFloat bfoat;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bfoat = (ButtonFloat)findViewById(R.id.buttonFloat);
		bfoat.setOnClickListener(this);
		
		
		
		UserBDD u = new UserBDD(this.getApplicationContext());
		
		u.open();
		
		try {
			Userlist= u.selectAll();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Calendar Calendar_Object = Calendar.getInstance();
		Calendar_Object.set(Calendar.MONTH, 11);
		Calendar_Object.set(Calendar.YEAR, 2014);
		Calendar_Object.set(Calendar.DAY_OF_MONTH, 11);
		Calendar_Object.set(Calendar.HOUR_OF_DAY, 9);
		Calendar_Object.set(Calendar.MINUTE, 30);
		Calendar_Object.set(Calendar.SECOND, 0);
		// MyView is my current Activity, and AlarmReceiver is the
		// BoradCastReceiver
		Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				MainActivity.this, 0, myIntent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, Calendar_Object.getTimeInMillis(),
				pendingIntent);*/	
		

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		miduser = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}


	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(miduser);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_notification) {
			Intent i = new Intent(MainActivity.this,NotificationActivity.class);
			this.startActivity(i);
			
		}
		
		if (id == R.id.action_settings) {
			return true;
			
		}
		
		if (id == R.id.action_sms) {
			Intent i = new Intent(MainActivity.this,SMSActivity.class);
			this.startActivity(i);
			
		}
		
		
		*/
		
		
		if(id == R.id.add_user){
			//Toast.makeText(this,"Example action.", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(MainActivity.this,AddUserActivity.class);
			this.startActivity(i);}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		//@Override
		/*public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}*/

	}


@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	
	NavigationDrawerFragment aaa = new NavigationDrawerFragment() ; 
	aaa.refreshlista(MainActivity.this);
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.buttonFloat:
		
		
		
	
		Intent i = new Intent(MainActivity.this,AddDrugActivity.class);
		
		startActivity(i);
		break;
	}
}
}
