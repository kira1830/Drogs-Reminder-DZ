package amrani_hichem.apps.drugsreminder.elements;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import amrani_hichem.apps.drugsreminder.libraries.ElementsLibrary;

public class Drug implements IElement {
	private int ID;
	private String name;
	private Bitmap bitmap;
	public Drug(int ID, String name, Bitmap bitmap) {
		this.ID = ID;
		this.name = name;
		this.bitmap = bitmap;
	}
	@Override
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	@Override
	public List<Integer> getInvolvingReminderIDs() {
		List<Integer> result = new ArrayList<>();
		for (int reminderID = 0; reminderID < ElementsLibrary.remindersNumber(); reminderID++) {
			if (!ElementsLibrary.doesNotHaveReminder(reminderID) &&
					    ElementsLibrary.findReminderByID(reminderID).getDrugIDs().contains(ID))
				result.add(reminderID);
		}
		return result;
	}
}
