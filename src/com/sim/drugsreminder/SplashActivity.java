package com.sim.drugsreminder;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.sim.drugsreminder.entities.User;
import com.sim.drugsreminder.sqllite.UserBDD;

public class SplashActivity extends Activity {
	
	private ImageView img;
	private Animation annim;
	//private String a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		//img = (ImageView) findViewById(R.id.img);
		
		getActionBar().hide();
		//annim = AnimationUtils.loadAnimation(this, R.anim.rotation);
		
		
		//img.startAnimation(annim);
		
		 //Log.i("aaa",a);
		String a1 = getString(R.string.Your_medicament_info);
		 StringsText.a1_Your_medicament_info=a1;
		 String a2 = getString(R.string.Medicament_type);
		 StringsText.a2_Medicament_type=a2;
		 String a3 = getString(R.string.Vial);
		 StringsText.a3_Vial=a3;
		 String a4 = getString(R.string.Pomade);
		 StringsText.a4_Pomade=a4;
		 String a5 = getString(R.string.Syrup);
		 StringsText.a5_Syrup=a5;
		 String a6 = getString(R.string.Injection);
		 StringsText.a6_Injection=a6;
		 String a7 = getString(R.string.Pill);
		 StringsText.a7_Pill=a7;
		 String a8 = getString(R.string.Instruction);
		 StringsText.a8_Instruction=a8;
		 String a9 = getString(R.string.Before_meals);
		 StringsText.a9_Before_meals=a9;
		 String a10 = getString(R.string.After_meals);
		 StringsText.a10_After_meals=a10;
		 String a33 = getString(R.string.No_instructions);
		 StringsText.a33_no_instructions=a33;
		 //----------------------------------------
		
		 StringsText.a11_months=getString(R.string.months);
	
	
			 StringsText.a12_weeks=getString(R.string.weeks);
		
			 StringsText.a13_days=getString(R.string.days);

	
		    StringsText.a14_one_month= getString(R.string.one_month);
		    StringsText.a15_tow_months= getString(R.string.tow_months);
		 	StringsText.a16_three_months= getString(R.string.three_months);
		 	
		 	StringsText.a17_one_day= getString(R.string.one_day);	
			StringsText.a18_tow_days= getString(R.string.tow_days); 	
			StringsText.a19_three_days= getString(R.string.three_days);
			
			 StringsText.a20_one_week= getString(R.string.one_week);
			 StringsText.a21_tow_weeks= getString(R.string.tow_week);
			 StringsText.a22_three_weeks= getString(R.string.three_week);
			 
			 StringsText.a23_monday= getString(R.string.monday);
			 StringsText.a24_tuesday= getString(R.string.tuesday);
			 StringsText.a25_wednesday= getString(R.string.wednesday);
			 StringsText.a26_thursday= getString(R.string.thursday);
			 StringsText.a27_friday= getString(R.string.friday);
			 StringsText.a28_saturday= getString(R.string.saturday);
			 StringsText.a29_sunday= getString(R.string.sunday);
			 
			 StringsText.a30_term_treatment=getString(R.string.term_treatment);
			 StringsText.a31_time=getString(R.string.Time);
			 StringsText.a32_programme=getString(R.string.Programme);
			 
			 
		 
		 
		 
		 
		 
		 
		 
		 String b1 = getString(R.string.Sexe);
		 StringsText.b1_Sexe=b1;
		 String b2 = getString(R.string.Male);
		 StringsText.b2_Male=b2;
		 String b3 = getString(R.string.Your_info);
		 StringsText.b3_Your_info=b3;
		 String b4 = getString(R.string.Female);
		 StringsText.b4_Female=b4;
		 String b5 = getString(R.string.Other);
		 StringsText.b5_Other=b5;
		 String b6 = getString(R.string.Pet_Choice);
		 StringsText.b6_Pet_Choice=b6;
		 String b7 = getString(R.string.Dog);
		 StringsText.b7_Dog=b7;
		 String b8 = getString(R.string.Cat);
		 StringsText.b8_Cat=b8;
		 String b9 = getString(R.string.Bird);
		 StringsText.b9_Bird=b9;
		 String b11 = getString(R.string.Horse);
		 StringsText.b11_Horse=b11;
		 String b12 = getString(R.string.Rabbit);
		 StringsText.b12_Rabbit=b12;
		 String b10 = getString(R.string.Your_pet_name);
		 StringsText.b10_Your_pet_name=b10;
		 //-----------------------------------------------------
		 UserBDD userbdd= new UserBDD(getApplication());
		 List<User> users = new ArrayList<User>();
		 User u = new User();
		 try {
			users= userbdd.selectAll();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 u= users.get(0);
		 
		 SharedPreferences pref = getSharedPreferences("Userlog", 0); //Mypref
			Editor editor = pref.edit();
			editor.putString("userid", u.getId()+"");
			//Toast.makeText(getApplicationContext(), u.getId()+"idtost", Toast.LENGTH_SHORT).show();
			editor.putString("name", u.getName());
			editor.commit();
		
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(i);
				
				finish();
			}
		}, 2000);
		
	}

	
}
