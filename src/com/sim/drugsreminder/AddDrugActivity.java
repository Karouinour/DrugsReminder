/*
 * Copyright 2012 Roman Nurik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sim.drugsreminder;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sim.drugsreminder.entities.AlarmModel;
import com.sim.drugsreminder.entities.Drugs;
import com.sim.drugsreminder.sqllite.AlarmDBHelper;
import com.sim.drugsreminder.sqllite.DrugBDD;
import com.sim.drugsreminder.wizardpager.DrugWizardModel;
import com.sim.drugsreminder.wizardpager.pages.DrugInfoPage;
import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;
import com.tech.freak.wizardpager.ui.ReviewFragment;
import com.tech.freak.wizardpager.ui.StepPagerStrip;


public class AddDrugActivity extends FragmentActivity implements
		PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks {
	private ViewPager mPager;
	private MyPagerAdapter mPagerAdapter;

	private boolean mEditingAfterReview;
	//private AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());


	private boolean mConsumePageSelectedEvent;

	private Button mNextButton;
	private Button mPrevButton;
	private AbstractWizardModel mWizardModel= new 
			DrugWizardModel(this);
	private List<Page> mCurrentPageSequence;
	private StepPagerStrip mStepPagerStrip;
	private int iduser;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		//SharedPreferences pref = getSharedPreferences("Userlog", 0);  
			
		//iduser =pref.getInt("id", 0);
		
		
		
		

		if (savedInstanceState != null) {
			mWizardModel.load(savedInstanceState.getBundle("model"));
		}

		mWizardModel.registerListener(this);

		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mPagerAdapter);
		mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
		mStepPagerStrip
				.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
					@Override
					public void onPageStripSelected(int position) {
						position = Math.min(mPagerAdapter.getCount() - 1,
								position);
						if (mPager.getCurrentItem() != position) {
							mPager.setCurrentItem(position);
						}
					}
				});

		ActionBar ab = getActionBar(); 
        ab.setDisplayHomeAsUpEnabled(true);
		
		mNextButton = (Button) findViewById(R.id.next_button);
		mPrevButton = (Button) findViewById(R.id.prev_button);

		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				mStepPagerStrip.setCurrentPage(position);

				if (mConsumePageSelectedEvent) {
					mConsumePageSelectedEvent = false;
					return;
				}

				mEditingAfterReview = false;
				updateBottomBar();
			}
		});

		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mPager.getCurrentItem() == mCurrentPageSequence.size()) {
					DialogFragment dg = new DialogFragment() {
						@Override
						public Dialog onCreateDialog(Bundle savedInstanceState) {
							return new AlertDialog.Builder(getActivity())
									.setMessage(R.string.submit_confirm_message)
									.setPositiveButton(
											R.string.submit_confirm_button,new OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													
													 int h=0,m=0;
													//durée traitement
													 
													 //"nom : "+namemedoc+" type : "+medicaltype+" durée traitement"+termetraitment
													 String namemedoc = mWizardModel.findByKey(StringsText.a1_Your_medicament_info).getData().getString(DrugInfoPage.DRUG_NAME_DATA_KEY);
													 String medicaltype = mWizardModel.findByKey(StringsText.a2_Medicament_type).getData().getString(SingleFixedChoicePage.SIMPLE_DATA_KEY);
													 String avanapres = mWizardModel.findByKey(StringsText.a8_Instruction).getData().getString(SingleFixedChoicePage.SIMPLE_DATA_KEY);
													 
													 String termetraitment = mWizardModel.findByKey(StringsText.a30_term_treatment).getData().getString(BranchPage.SIMPLE_DATA_KEY);
													 if (termetraitment.equals(StringsText.a13_days)){
														 String nombredejour = mWizardModel.findByKey(StringsText.a13_days+":"+StringsText.a13_days).getData().getString(SingleFixedChoicePage.SIMPLE_DATA_KEY);
														 ArrayList<String> Time = mWizardModel.findByKey(StringsText.a31_time).getData().getStringArrayList(MultipleFixedChoicePage.SIMPLE_DATA_KEY);
														
														 
														// Toast toast2 = Toast.makeText(getApplicationContext(),"id user : "+getiduserlogin() +" id drug : "+getlastiddrugs(), 2000);
									                    	//toast2.show();	
														
									                    	
														
													 		if(StringsText.a17_one_day.equals(nombredejour))
													 		{
													 			DrugBDD drugbdd = new DrugBDD(getApplicationContext());
													 			Drugs d = new Drugs();
													 			 d.setName(namemedoc);
													 			 //Duré dutilisation dat 
													 			 Date date = new Date();
																 Calendar c = Calendar.getInstance();
																 c.roll(c.DAY_OF_MONTH, 1);
																 date =c.getTime();
																 d.setEnddate(date.toString());
																 //Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
																 //toast2.show();	
																 //-----------------------------------
																 d.setType_medoc(medicaltype);
																 d.setInstruction(avanapres);
																 d.setDurer(StringsText.a13_days);
																 d.setNombre_durer(StringsText.a17_one_day);
																 d.setIduser(getiduserlogin());
																 //insertion Drugs --------------
																 drugbdd.open();
																 drugbdd.insertTop(d);
																 drugbdd.close();
																 
																 for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																	 //Toast toast = Toast.makeText(getApplicationContext(),"H : "+h+" M : "+m, 2000);
																	 //toast.show();	
																	 //Sonerie de l'alarm 
																	 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																	 //repté chaque semaine ou pas 
																	 a.repeatWeekly=true;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, true);
																	 a.setRepeatingDay(1, true);
																	 a.setRepeatingDay(2, true);
																	 a.setRepeatingDay(3, true);
																	 a.setRepeatingDay(4, true);
																	 a.setRepeatingDay(5, true);
																	 a.setRepeatingDay(6, true);
																	 a.isEnabled=true;
																	 AlarmManagerHelper.cancelAlarms(getApplicationContext());
																	 AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																	 dbHelper.createAlarm(a);
																	 AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																 Intent j = new Intent(getActivity(),
																			MainActivity.class);

																	startActivity(j);
													 		}
													 		if(StringsText.a18_tow_days.equals(nombredejour))
													 		{
													 			DrugBDD drugbdd = new DrugBDD(getApplicationContext());
													 			Drugs d = new Drugs();
													 			 d.setName(namemedoc);
													 			 //Duré dutilisation dat 
													 			 Date date = new Date();
																 Calendar c = Calendar.getInstance();
																 c.roll(c.DAY_OF_MONTH, 2);
																 date =c.getTime();
																 d.setEnddate(date.toString());
																 //Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
											                    	//toast2.show();	
																 //-----------------------------------
																 d.setType_medoc(medicaltype);
																 d.setInstruction(avanapres);
																 d.setDurer(StringsText.a13_days);
																 d.setNombre_durer(StringsText.a17_one_day);
																 d.setIduser(getiduserlogin());
																 //insertion Drugs --------------
																 drugbdd.open();
																 drugbdd.insertTop(d);
																 drugbdd.close();
																 
																 for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																	 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																	 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, true);
																	 a.setRepeatingDay(1, true);
																	 a.setRepeatingDay(2, true);
																	 a.setRepeatingDay(3, true);
																	 a.setRepeatingDay(4, true);
																	 a.setRepeatingDay(5, true);
																	 a.setRepeatingDay(6, true);
																	 a.isEnabled=true;
																	 AlarmManagerHelper.cancelAlarms(getApplicationContext());
																	 AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																	 dbHelper.createAlarm(a);
																	 AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																
																 Intent j = new Intent(getActivity(),
																			MainActivity.class);

																	startActivity(j);
													 		}
													 		if(StringsText.a19_three_days.equals(nombredejour))
													 		{
													 			DrugBDD drugbdd = new DrugBDD(getApplicationContext());
													 			Drugs d = new Drugs();
													 			 d.setName(namemedoc);
													 			 //Duré dutilisation dat 
													 			 Date date = new Date();
																 Calendar c = Calendar.getInstance();
																 c.roll(c.DAY_OF_MONTH, 3);
																 date =c.getTime();
																 d.setEnddate(date.toString());
																 //Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
											                    	//toast2.show();	
																 //-----------------------------------
																 d.setType_medoc(medicaltype);
																 d.setInstruction(avanapres);
																 d.setDurer(StringsText.a13_days);
																 d.setNombre_durer(StringsText.a17_one_day);
																 d.setIduser(getiduserlogin());
																 //insertion Drugs --------------
																 drugbdd.open();
																 drugbdd.insertTop(d);
																 drugbdd.close();
																 
																 
																 for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																	 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																	 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, true);
																	 a.setRepeatingDay(1, true);
																	 a.setRepeatingDay(2, true);
																	 a.setRepeatingDay(3, true);
																	 a.setRepeatingDay(4, true);
																	 a.setRepeatingDay(5, true);
																	 a.setRepeatingDay(6, true);
																	 a.isEnabled=true;
																	 AlarmManagerHelper.cancelAlarms(getApplicationContext());
																	 AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																	 dbHelper.createAlarm(a);
																	 AlarmManagerHelper.setAlarms(getApplicationContext());
																 }
													 		}
													 		Intent j = new Intent(getActivity(),
																	MainActivity.class);

															startActivity(j);
													 }
													if (termetraitment.equals(StringsText.a12_weeks)){
														 String nombredesemaine = mWizardModel.findByKey(StringsText.a12_weeks+":"+StringsText.a12_weeks).getData().getString(SingleFixedChoicePage.SIMPLE_DATA_KEY);
														 ArrayList<String> Jours = mWizardModel.findByKey(StringsText.a12_weeks+":"+StringsText.a32_programme).getData().getStringArrayList(MultipleFixedChoicePage.SIMPLE_DATA_KEY);
														 ArrayList<String> Time = mWizardModel.findByKey(StringsText.a31_time).getData().getStringArrayList(MultipleFixedChoicePage.SIMPLE_DATA_KEY);
														
														 
														// Toast toast2 = Toast.makeText(getApplicationContext(),"jours : "+Jours, 2000);
														// toast2.show();	
														
									                    //--------------	
														
													 		if(StringsText.a20_one_week.equals(nombredesemaine))
													 		{
													 			DrugBDD drugbdd = new DrugBDD(getApplicationContext());
													 			Drugs d = new Drugs();
													 			 d.setName(namemedoc);
													 			 //Duré dutilisation dat 
													 			 Date date = new Date();
																 Calendar c = Calendar.getInstance();
																 c.roll(c.DAY_OF_MONTH, 7);
																 date =c.getTime();
																 d.setEnddate(date.toString());
																 //Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
											                     //toast2.show();	
																 //-----------------------------------
																 d.setType_medoc(medicaltype);
																 d.setInstruction(avanapres);
																 d.setDurer(StringsText.a12_weeks);
																 d.setNombre_durer(StringsText.a20_one_week);
																 d.setIduser(getiduserlogin());
																 //insertion Drugs --------------
																 drugbdd.open();
																 drugbdd.insertTop(d);
																 drugbdd.close();
																 
																 for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																	 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																	 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;
																	 AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																	 AlarmManagerHelper.cancelAlarms(getApplicationContext());
																	 dbHelper.createAlarm(a);
																	 AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																 Intent j = new Intent(getActivity(),
																			MainActivity.class);

																	startActivity(j);
													 		}
															if(StringsText.a21_tow_weeks.equals(nombredesemaine))
															 {
															 	DrugBDD drugbdd = new DrugBDD(getApplicationContext());
															 	Drugs d = new Drugs();
															 	d.setName(namemedoc);
															 	//Duré dutilisation dat 
															 	Date date = new Date();
																Calendar c = Calendar.getInstance();
																c.roll(c.DAY_OF_MONTH, 14);
																date =c.getTime();
																d.setEnddate(date.toString());
																//Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
													            //toast2.show();	
																//-----------------------------------
																d.setType_medoc(medicaltype);
																d.setInstruction(avanapres);
																d.setDurer(StringsText.a12_weeks);
																d.setNombre_durer(StringsText.a21_tow_weeks);
																d.setIduser(getiduserlogin());
																//insertion Drugs --------------
																drugbdd.open();
																drugbdd.insertTop(d);
																drugbdd.close();
																	 
																for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;			
																AlarmManagerHelper.cancelAlarms(getApplicationContext());
																AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																dbHelper.createAlarm(a);
																AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																Intent j = new Intent(getActivity(),
																		MainActivity.class);

																startActivity(j);
															 }
															if(StringsText.a22_three_weeks.equals(nombredesemaine))
															 {
															 	DrugBDD drugbdd = new DrugBDD(getApplicationContext());
															 	Drugs d = new Drugs();
															 	d.setName(namemedoc);
															 	//Duré dutilisation dat 
															 	Date date = new Date();
																Calendar c = Calendar.getInstance();
																c.roll(c.DAY_OF_MONTH, 21);
																date =c.getTime();
																d.setEnddate(date.toString());
																//Toast toast2 = Toast.makeText(getApplicationContext(),date.toString(), 2000);
													           // toast2.show();	
																//-----------------------------------
																d.setType_medoc(medicaltype);
																d.setInstruction(avanapres);
																d.setDurer(StringsText.a12_weeks);
																d.setNombre_durer(StringsText.a22_three_weeks);
																d.setIduser(getiduserlogin());
																//insertion Drugs --------------
																drugbdd.open();
																drugbdd.insertTop(d);
																drugbdd.close();
																	 
																for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;			
																AlarmManagerHelper.cancelAlarms(getApplicationContext());
																AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																dbHelper.createAlarm(a);
																AlarmManagerHelper.setAlarms(getApplicationContext());
																}
															 }
															Intent j = new Intent(getActivity(),
																	MainActivity.class);

															startActivity(j);
													}
													//mois mois-------------------------------
													
													if (termetraitment.equals(StringsText.a11_months)){
														 String nombredemois = mWizardModel.findByKey(StringsText.a11_months+":"+StringsText.a11_months).getData().getString(SingleFixedChoicePage.SIMPLE_DATA_KEY);
														 ArrayList<String> Jours = mWizardModel.findByKey(StringsText.a11_months+":"+StringsText.a32_programme).getData().getStringArrayList(MultipleFixedChoicePage.SIMPLE_DATA_KEY);
														 ArrayList<String> Time = mWizardModel.findByKey(StringsText.a31_time).getData().getStringArrayList(MultipleFixedChoicePage.SIMPLE_DATA_KEY);
														
														 
														 //Toast toast2 = Toast.makeText(getApplicationContext(),"id user : "+getiduserlogin() +" id drug : "+getlastiddrugs(), 2000);
														 //toast2.show();	
														
									                    //--------------	
														
													 		if(StringsText.a14_one_month.equals(nombredemois))
													 		{
													 			DrugBDD drugbdd = new DrugBDD(getApplicationContext());
													 			Drugs d = new Drugs();
													 			 d.setName(namemedoc);
													 			 //Duré dutilisation dat 
													 			 Date date = new Date();
																 Calendar c = Calendar.getInstance();
																 c.roll(c.MONTH, 1);
																 date =c.getTime();
																 d.setEnddate(date.toString());
																// Toast toast = Toast.makeText(getApplicationContext(),date.toString(), 2000);
											                    	//toast.show();	
																 //-----------------------------------
																 d.setType_medoc(medicaltype);
																 d.setInstruction(avanapres);
																 d.setDurer(StringsText.a11_months);
																 d.setNombre_durer(StringsText.a14_one_month);
																 d.setIduser(getiduserlogin());
																 //insertion Drugs --------------
																 drugbdd.open();
																 drugbdd.insertTop(d);
																 drugbdd.close();
																 
																 for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																	 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																	 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;
																	 AlarmManagerHelper.cancelAlarms(getApplicationContext());
																	 AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																	 dbHelper.createAlarm(a);
																	 AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																 Intent j = new Intent(getActivity(),
																			MainActivity.class);

																	startActivity(j);
													 		}
															if(StringsText.a15_tow_months.equals(nombredemois))
															 {
															 	DrugBDD drugbdd = new DrugBDD(getApplicationContext());
															 	Drugs d = new Drugs();
															 	d.setName(namemedoc);
															 	//Duré dutilisation dat 
															 	Date date = new Date();
																Calendar c = Calendar.getInstance();
																c.roll(c.MONTH, 2);
																date =c.getTime();
																d.setEnddate(date.toString());
																//Toast toast = Toast.makeText(getApplicationContext(),date.toString(), 2000);
													            //toast.show();	
																//-----------------------------------
																d.setType_medoc(medicaltype);
																d.setInstruction(avanapres);
																d.setDurer(StringsText.a11_months);
																d.setNombre_durer(StringsText.a15_tow_months);
																d.setIduser(getiduserlogin());
																//insertion Drugs --------------
																drugbdd.open();
																drugbdd.insertTop(d);
																drugbdd.close();
																	 
																for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																 //Sonerie de l'alarm 
																	 a.alarmTone=  Uri.parse(GetTonePref());
																 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;		
																AlarmManagerHelper.cancelAlarms(getApplicationContext());
																AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																dbHelper.createAlarm(a);
																AlarmManagerHelper.setAlarms(getApplicationContext());
																}
																Intent j = new Intent(getActivity(),
																		MainActivity.class);

																startActivity(j);
															 }
															if(StringsText.a16_three_months.equals(nombredemois))
															 {
															 	DrugBDD drugbdd = new DrugBDD(getApplicationContext());
															 	Drugs d = new Drugs();
															 	d.setName(namemedoc);
															 	//Duré dutilisation dat 
															 	Date date = new Date();
																Calendar c = Calendar.getInstance();
																c.roll(c.MONTH, 3);
																date =c.getTime();
																d.setEnddate(date.toString());
																//Toast toast = Toast.makeText(getApplicationContext(),date.toString(), 2000);
													           // toast.show();	
																//-----------------------------------
																d.setType_medoc(medicaltype);
																d.setInstruction(avanapres);
																d.setDurer(StringsText.a11_months);
																d.setNombre_durer(StringsText.a16_three_months);
																d.setIduser(getiduserlogin());
																//insertion Drugs --------------
																drugbdd.open();
																drugbdd.insertTop(d);
																drugbdd.close();
																	 
																for (String ti : Time) {
																	 h=Integer.valueOf(ti.substring(0,2));
																	 m = Integer.valueOf(ti.substring(3,5));
																	 AlarmModel a = new AlarmModel();
																	 a.id_drug=getlastiddrugs();
																	 a.name=getlastnamedrugs();
																	 a.timeHour=h;
																	 a.timeMinute=m;
																 //Sonerie de l'alarm 
																	 //a.alarmTone= Uri.parse(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI);
																	 a.alarmTone=  Uri.parse(GetTonePref());
																 //repté chaque semaine ou pas 
																	 a.repeatWeekly=false;
																	 //liste de jours actif
																	 a.setRepeatingDay(0, false);
																	 a.setRepeatingDay(1, false);
																	 a.setRepeatingDay(2, false);
																	 a.setRepeatingDay(3, false);
																	 a.setRepeatingDay(4, false);
																	 a.setRepeatingDay(5, false);
																	 a.setRepeatingDay(6, false);
																	 for (String j : Jours) {
																		 if(j.equals(StringsText.a23_monday))
																			 	a.setRepeatingDay(1, true);
																		 if(j.equals(StringsText.a24_tuesday))
																			 	a.setRepeatingDay(2, true);
																		 if(j.equals(StringsText.a25_wednesday))
																			 	a.setRepeatingDay(3, true);
																		 if(j.equals(StringsText.a26_thursday))
																			 	a.setRepeatingDay(4, true);
																		 if(j.equals(StringsText.a27_friday))
																			 	a.setRepeatingDay(5, true);
																		 if(j.equals(StringsText.a28_saturday))
																			 	a.setRepeatingDay(6, true);
																		 if(j.equals(StringsText.a29_sunday))
																			 	a.setRepeatingDay(0, true);
																	}
																	 a.isEnabled=true;			
																AlarmManagerHelper.cancelAlarms(getApplicationContext());
																AlarmDBHelper dbHelper = new AlarmDBHelper(getApplicationContext());
																dbHelper.createAlarm(a);
																AlarmManagerHelper.setAlarms(getApplicationContext());
																}
															 }
															Intent j = new Intent(getActivity(),
																	MainActivity.class);

															startActivity(j);
													}
													
												}
											}).setNegativeButton(android.R.string.cancel,
											null).create();
						}
					};
					dg.show(getSupportFragmentManager(), "place_order_dialog");
				} else {
					if (mEditingAfterReview) {
						mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
					} else {
						mPager.setCurrentItem(mPager.getCurrentItem() + 1);
					}
				}
			}
		});

		mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			}
		});

		onPageTreeChanged();
		updateBottomBar();
	}
	 public int getlastiddrugs(){
		 DrugBDD drugbdd = new DrugBDD(getApplicationContext());
		 Drugs lastdrugs = new Drugs();
		 List<Drugs> listdrug = new ArrayList<Drugs>();
		 try {
				listdrug= drugbdd.selectAll();
				lastdrugs =listdrug.get(listdrug.size()-1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return lastdrugs.getIddrugs();      
	 }
	 public String getlastnamedrugs(){
		 DrugBDD drugbdd = new DrugBDD(getApplicationContext());
		 Drugs lastdrugs = new Drugs();
		 List<Drugs> listdrug = new ArrayList<Drugs>();
		 try {
				listdrug= drugbdd.selectAll();
				lastdrugs =listdrug.get(listdrug.size()-1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return lastdrugs.getName();      
	 }
	 public int getiduserlogin(){
		 SharedPreferences pref = getApplicationContext().getSharedPreferences("Userlog", 0); //Mypref
			String id =pref.getString("userid", "vide");
		 return Integer.parseInt(id);
     	
		
	 }
	 public String GetTonePref (){
	 SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	 String strRingtonePreference = preference.getString("ring_tone_pref", "DEFAULT_SOUND"); 
	 //Toast toast2 = Toast.makeText(getApplicationContext(),"Tone : "+strRingtonePreference, 2000);
 		//toast2.show();	
	 return strRingtonePreference;
	 
	 }

	@Override
	public void onPageTreeChanged() {
		mCurrentPageSequence = mWizardModel.getCurrentPageSequence();
		recalculateCutOffPage();
		mStepPagerStrip.setPageCount(mCurrentPageSequence.size() + 1); // + 1 =
		// review
		// step
		mPagerAdapter.notifyDataSetChanged();
		updateBottomBar();
	}

	private void updateBottomBar() {
		int position = mPager.getCurrentItem();
		if (position == mCurrentPageSequence.size()) {
			mNextButton.setText(R.string.finish);
			mNextButton.setBackgroundResource(R.drawable.finish_background);
			// mNextButton.setTextAppearance(this,
			// R.style.TextAppearanceFinish);
		} else {
			mNextButton.setText(mEditingAfterReview ? R.string.review
					: R.string.next);
			mNextButton
					.setBackgroundResource(R.drawable.selectable_item_background);
			TypedValue v = new TypedValue();
			getTheme().resolveAttribute(android.R.attr.textAppearanceMedium, v,
					true);
			mNextButton.setTextAppearance(this, v.resourceId);
			mNextButton.setEnabled(position != mPagerAdapter.getCutOffPage());
		}

		mPrevButton
				.setVisibility(position <= 0 ? View.INVISIBLE : View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWizardModel.unregisterListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBundle("model", mWizardModel.save());
	}

	@Override
	public AbstractWizardModel onGetModel() {
		return mWizardModel;
	}

	@Override
	public void onEditScreenAfterReview(String key) {
		for (int i = mCurrentPageSequence.size() - 1; i >= 0; i--) {
			if (mCurrentPageSequence.get(i).getKey().equals(key)) {
				mConsumePageSelectedEvent = true;
				mEditingAfterReview = true;
				mPager.setCurrentItem(i);
				updateBottomBar();
				break;
			}
		}
	}

	@Override
	public void onPageDataChanged(Page page) {
		if (page.isRequired()) {
			if (recalculateCutOffPage()) {
				mPagerAdapter.notifyDataSetChanged();
				updateBottomBar();
			}
		}
	}

	@Override
	public Page onGetPage(String key) {
		return mWizardModel.findByKey(key);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) { 
        switch (item.getItemId()) {
            case android.R.id.home:
            	this.finish();
                return true;
                default:
                return super.onOptionsItemSelected(item); 
        }
	}
	
	private boolean recalculateCutOffPage() {
		// Cut off the pager adapter at first required page that isn't completed
		int cutOffPage = mCurrentPageSequence.size() + 1;
		for (int i = 0; i < mCurrentPageSequence.size(); i++) {
			Page page = mCurrentPageSequence.get(i);
			if (page.isRequired() && !page.isCompleted()) {
				cutOffPage = i;
				break;
			}
		}

		if (mPagerAdapter.getCutOffPage() != cutOffPage) {
			mPagerAdapter.setCutOffPage(cutOffPage);
			return true;
		}

		return false;
	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {
		private int mCutOffPage;
		private Fragment mPrimaryItem;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			if (i >= mCurrentPageSequence.size()) {
				return new ReviewFragment();
			}

			return mCurrentPageSequence.get(i).createFragment();
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO: be smarter about this
			if (object == mPrimaryItem) {
				// Re-use the current fragment (its position never changes)
				return POSITION_UNCHANGED;
			}

			return POSITION_NONE;
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			super.setPrimaryItem(container, position, object);
			mPrimaryItem = (Fragment) object;
		}

		@Override
		public int getCount() {
			return Math.min(mCutOffPage + 1, mCurrentPageSequence == null ? 1
					: mCurrentPageSequence.size() + 1);
		}

		public void setCutOffPage(int cutOffPage) {
			if (cutOffPage < 0) {
				cutOffPage = Integer.MAX_VALUE;
			}
			mCutOffPage = cutOffPage;
		}

		public int getCutOffPage() {
			return mCutOffPage;
		}
	}
}
