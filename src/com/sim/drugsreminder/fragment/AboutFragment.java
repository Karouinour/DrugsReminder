package com.sim.drugsreminder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sim.drugsreminder.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class AboutFragment  extends Fragment{

	private SlidingUpPanelLayout mLayout;
    private static final String TAG = "DemoActivity";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View r = (View) inflater.inflate(R.layout.fragment_about, container, false);
		final ImageView upanddown =(ImageView)r.findViewById(R.id.upanddown);
		mLayout = (SlidingUpPanelLayout)r.findViewById(R.id.sliding_layout);
		mLayout.setPanelSlideListener(new PanelSlideListener() {
			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				Log.i(TAG, "onPanelSlide, offset " + slideOffset);
			}

			@Override
			public void onPanelExpanded(View panel) {
				Log.i(TAG, "onPanelExpanded");
				upanddown.setImageResource(R.drawable.low27);
			}

			@Override
			public void onPanelCollapsed(View panel) {
				Log.i(TAG, "onPanelCollapsed");
				upanddown.setImageResource(R.drawable.down);
			}

			@Override
			public void onPanelAnchored(View panel) {
				Log.i(TAG, "onPanelAnchored");
			}

			@Override
			public void onPanelHidden(View panel) {
				Log.i(TAG, "onPanelHidden");
			}
		});
		return r;

//		TextView t = (TextView) view.findViewById(R.id.main);
//		t.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mLayout.collapsePanel();
//			}
//		});

//		t = (TextView) view.findViewById(R.id.name);
//		t.setText("Hello");
		

	}
	
	
}
