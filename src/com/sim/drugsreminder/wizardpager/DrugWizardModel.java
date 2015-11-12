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
package com.sim.drugsreminder.wizardpager;

import android.content.Context;

import com.sim.drugsreminder.StringsText;
import com.sim.drugsreminder.wizardpager.pages.DrugInfoPage;
import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;

public class DrugWizardModel extends AbstractWizardModel {

	Context mcontext;

	public DrugWizardModel(Context context) {
		super(context);
		mcontext = context;

	}

	@Override
    protected PageList onNewRootPageList() {
    	
    	return new PageList(
        		new DrugInfoPage(this, StringsText.a1_Your_medicament_info)
                        .setRequired(true),
                new SingleFixedChoicePage(this,StringsText.a2_Medicament_type)
                        .setChoices(StringsText.a3_Vial,StringsText.a4_Pomade,StringsText.a5_Syrup, StringsText.a6_Injection, StringsText.a7_Pill)
                        .setRequired(true),
                new SingleFixedChoicePage(this, StringsText.a8_Instruction)
                        .setChoices(StringsText.a9_Before_meals,StringsText.a10_After_meals,StringsText.a33_no_instructions)
                        .setRequired(true),
                //-----------------------------
                new BranchPage(this,StringsText.a30_term_treatment)
        	    		.addBranch(StringsText.a13_days,
        	    				new SingleFixedChoicePage(this,StringsText.a13_days)
                					.setChoices(StringsText.a17_one_day,StringsText.a18_tow_days,StringsText.a19_three_days)
                					.setRequired(true))
                		.addBranch(StringsText.a12_weeks,
                        		new SingleFixedChoicePage(this, StringsText.a12_weeks)
                        			.setChoices(StringsText.a20_one_week,StringsText.a21_tow_weeks,StringsText.a22_three_weeks)
                        			.setRequired(true),
                        		 new MultipleFixedChoicePage(this, "Programme")
                         					.setChoices(StringsText.a23_monday,StringsText.a24_tuesday,
                         						StringsText.a25_wednesday,StringsText.a26_thursday,
                         						StringsText.a27_friday,StringsText.a28_saturday,
                         						StringsText.a29_sunday).setRequired(true))
                         						
                        .addBranch(StringsText.a11_months,
                        		new SingleFixedChoicePage(this, StringsText.a11_months)
                        			.setChoices(StringsText.a14_one_month,StringsText.a15_tow_months,StringsText.a16_three_months)
                        			.setRequired(true),
                        		new MultipleFixedChoicePage(this, StringsText.a32_programme)
                 							.setChoices(StringsText.a23_monday,StringsText.a24_tuesday,
                 								StringsText.a25_wednesday,StringsText.a26_thursday,
                 								StringsText.a27_friday,StringsText.a28_saturday,
                 								StringsText.a29_sunday).setRequired(true))
                 								.setRequired(true),
                     
                        		
                new MultipleFixedChoicePage(this, StringsText.a31_time)
                .setChoices("06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00").setRequired(true)
                        
    			);
                
        			
                 
        
    }
}