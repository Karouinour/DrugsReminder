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
import com.sim.drugsreminder.wizardpager.pages.CustomerInfoPage;
import com.sim.drugsreminder.wizardpager.pages.PetInfoPage;
import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
public class UserWizardModel extends AbstractWizardModel {
	
	Context mcontext;
    public UserWizardModel(Context context) {
        super(context);
        mcontext = context;
       
        
        
    }
    @Override
    protected PageList onNewRootPageList() {
    	return new PageList(  
                new BranchPage(this,StringsText.b1_Sexe)
                        .addBranch(StringsText.b2_Male,
                        		//new ImagePage(this, "Your picture"),
                        		new CustomerInfoPage(this, StringsText.b3_Your_info)
                        		.setRequired(true))
                        .addBranch(StringsText.b4_Female,
                        		//new ImagePage(this, "Your picture"),
                        		new CustomerInfoPage(this, StringsText.b3_Your_info)
                        		.setRequired(true))
                        .addBranch(StringsText.b5_Other,
                                new SingleFixedChoicePage(this, StringsText.b6_Pet_Choice)
                                        .setChoices(StringsText.b7_Dog, StringsText.b8_Cat, StringsText.b9_Bird, StringsText.b11_Horse, StringsText.b12_Rabbit)
                                        .setRequired(true),
                                new PetInfoPage(this, StringsText.b10_Your_pet_name)
                				.setRequired(true))
                        .setRequired(true)
              
        );
    }
}