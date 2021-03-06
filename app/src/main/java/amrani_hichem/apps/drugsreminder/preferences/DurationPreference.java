package amrani_hichem.apps.drugsreminder.preferences;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

import amrani_hichem.apps.drugsreminder.R;
import amrani_hichem.apps.drugsreminder.util.Time;

public class DurationPreference extends Preference {
	public DurationPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}
	public DurationPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	public DurationPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public DurationPreference(Context context) {
		super(context);
	}
	@Override
	protected void onClick() {
		super.onClick();
		Time time = new Time(getPersistedInt(30));
		new TimePickerDialog(getContext(), R.style.AppTheme_TimePickerWithSpinner,
				(view, hourOfDay, minute) -> {
					Time newTime = new Time(hourOfDay, minute);
					if (callChangeListener(newTime)) persistInt(newTime.minutes());
				}, time.getHour(), time.getMinute(),
				true).show();
	}
}
