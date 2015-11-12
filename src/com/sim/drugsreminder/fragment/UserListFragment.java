package com.sim.drugsreminder.fragment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.sim.drugsreminder.R;
import com.sim.drugsreminder.adapters.UserCustomAdapter;
import com.sim.drugsreminder.entities.User;
import com.sim.drugsreminder.sqllite.UserBDD;

public class UserListFragment extends Fragment {

	List<User> listu;
	ImageView image;
	public static UserCustomAdapter adapter;
	public User userdelete;
	private ListView mDrawerListView;

	UserBDD u;

	public UserListFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Liste et recuperation user

		UserBDD u = new UserBDD(getActivity().getApplicationContext());

		listu = new ArrayList<User>();
		// -------------connextion sqlite
		try {
			listu = u.selectAll();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
     	
		
		mDrawerListView = (ListView) inflater.inflate(
				R.layout.fragment_list_user, container, false);
		mDrawerListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// ***********************************************************************
						// selectItem(position);
						/*DrugBDD drugs = new DrugBDD(getActivity());
						List<Drugs> listdrug = new ArrayList<Drugs>();
						drugs.open();
						try {
							listdrug = drugs.selectAll();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (listdrug!=null){*/
						DrugsListFragment frg = new DrugsListFragment();
						Bundle args = new Bundle();
						args.putInt("id", position);
						frg.setArguments(args);
						getFragmentManager().beginTransaction()
								.replace(R.id.container, frg)
								.addToBackStack(null).commit();
						
						//}
						
					}
				});
		mDrawerListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						userdelete  = new User();
						userdelete=listu.get(position);
						new SweetAlertDialog(getActivity(),
								SweetAlertDialog.WARNING_TYPE)
								.setTitleText(getResources().getString(R.string.Are_you_sure_to_delete_user))
								.setContentText(
										"Won't be able to recover this file!")
								.setCancelText("No,cancel plx!")
								.setConfirmText("Yes,delete it!")
								.showCancelButton(true)
								.setCancelClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(
													SweetAlertDialog sDialog) {
												// reuse previous dialog
												// instance, keep widget user
												// state, reset them if you need
												sDialog.setTitleText(
														"Cancelled!")
														.setContentText(
																"Your imaginary file is safe :)")
														.setConfirmText("OK")
														.showCancelButton(false)
														.setCancelClickListener(
																null)
														.setConfirmClickListener(
																null)
														.changeAlertType(
																SweetAlertDialog.ERROR_TYPE);

												// or you can new a
												// SweetAlertDialog to show
												/*
												 * sDialog.dismiss(); new
												 * SweetAlertDialog
												 * (SampleActivity.this,
												 * SweetAlertDialog.ERROR_TYPE)
												 * .setTitleText("Cancelled!")
												 * .setContentText
												 * ("Your imaginary file is safe :)"
												 * ) .setConfirmText("OK")
												 * .show();
												 */
											}
										})
								.setConfirmClickListener(
										new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(												
													SweetAlertDialog sDialog) {
												UserBDD userbdd = new UserBDD(getActivity());
												userbdd.open();
												userbdd.removeUser(userdelete.getId());
												userbdd.close();
												sDialog.setTitleText("Deleted!")
														.setContentText(
																"Your imaginary file has been deleted!")
														.setConfirmText("OK")
														.showCancelButton(false)
														.setCancelClickListener(
																null)
														.setConfirmClickListener(
																null)
														.changeAlertType(
																SweetAlertDialog.SUCCESS_TYPE);
											}
										}).show();
//						Intent j = new Intent(getActivity(),
//								MainActivity.class);
//
//						startActivity(j);
						return true;
					}
				});

		// ____________________________________________________________________________
		getActivity().getActionBar().setTitle("User liste");
		adapter = new UserCustomAdapter(getActivity().getBaseContext(),
				R.layout.one_user, listu);
		mDrawerListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		return mDrawerListView;
	}

	/*public void refrech(Context cxt) {
		AlarmDBHelper_temp i = new AlarmDBHelper_temp(getActivity());
		List<AlarmModel> list2 = new ArrayList<AlarmModel>();
		list2 = i.getAlarmsTemps();
		adapter.clear();
		adapter.addAll(list2);
		adapter.notifyDataSetChanged();
	}*/

}
