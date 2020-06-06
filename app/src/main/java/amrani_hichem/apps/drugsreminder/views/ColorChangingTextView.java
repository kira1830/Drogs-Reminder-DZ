package amrani_hichem.apps.drugsreminder.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckedTextView;

public class ColorChangingTextView extends AppCompatCheckedTextView {
	public ColorChangingTextView(Context context) {
		super(context);
	}
	public ColorChangingTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public ColorChangingTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		setTextColor(checked ? Color.BLACK : 0xff606060);
	}
}
