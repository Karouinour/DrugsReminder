package com.sim.drugsreminder;

import java.util.ArrayList;
import java.util.List;

/*
 Copyright 2012 Aphid Mobile

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
public class Travels {

	public static final List<Data> IMG_DESCRIPTIONS = new ArrayList<Data>();

	static {
		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data("", "Precaution1.png", ""));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Follow the instructions",
						"Precaution2.png",
						"A drug is intended to cure, relieve or prevent many specific diseases.<br>"
								+ " Apart from this case,the medication can be dangerous and increases the risk of developing side effects.<br>"
								+ "Never give a medication that was prescribed to someone else, even if you do have the symptoms."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Beware of cons-indications",
						"Precaution3.png",
						"It is the case you should never take certain medications, for example if you are allergic to or if you are pregnant."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Be very attentive to situations that change conditions of employment",
						"Precaution4.png",
						"Coexistence of another disease, malfunction of an organ, age ...<br>"
								+ "Taking one or more other drugs.<br>"
								+ "Presence of certain excipients (different substances of the active substances of the medicament, for example lactose or sucrose, and presenting risks during certain diseases or in allergic subjects)."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Adjust your lifestyle",
						"Precaution5.png",
						"Attention to certain foods or drinks that can influence the activity of your medication.<br>"
								+ "Your ability to drive or operate machinery, to breastfeed or to practice competitive sports can be changed by the drug.<br>"
								+ "Some treatments may require precautions, such as following a contraceptive method."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Respect the the medication treatment modalities",
						"Precaution6.png",
						"Dosage: the precise amount of drug that must be taken and at what pace.<br>"
								+ "Do not under any circumstances modify the medication without telling your doctor or pharmacist.<br>"
								+ "Treatment duration: In some cases, you should never interrupt it.<br>"
								+ "Some drugs require administration conditions specific: specific times, taken with or between meals ...<br>"
								+ "The administration device of your medicine (measuring spoon, dropper, syringe graduated ...) must not be used with other drugs.<br>"
								+ "The notice also tells you what to do if you have taken more medicine that you should or, conversely, if you forget to take your medicine."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Adopt the right attitude if you notice any side effects",
						"Precaution7.png",
						"If you see an adverse reaction (the drug has been used at normal doses or in non-recommended conditions), contact your doctor or pharmacist; it will give you what to do."));

		Travels.IMG_DESCRIPTIONS
				.add(new Travels.Data(
						"Use extra caution if you are taking a medication without a prescription",
						"Precaution8.png",
						"The drug has been prescribed to you or that you bought it from a pharmacy by your self, you must read all the rubric of the notice.<br>"
								+ "In case of inefficiency, of appearance of an adverse effect or simply doubt, ask the advice of your doctor or pharmacist."));

	}

	public static final class Data {

		public final String title;
		public final String imageFilename;
		public final String description;

		private Data(String title, String imageFilename, String description) {
			this.title = title;
			this.imageFilename = imageFilename;
			this.description = description;
		}
	}
}
