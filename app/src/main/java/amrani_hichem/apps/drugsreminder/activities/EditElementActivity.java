package amrani_hichem.apps.drugsreminder.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import amrani_hichem.apps.drugsreminder.R;
import amrani_hichem.apps.drugsreminder.elements.IElement;
import amrani_hichem.apps.drugsreminder.libraries.ElementsLibrary;

public abstract class EditElementActivity<T extends IElement> extends AppCompatActivity {
	private AlertDialog.Builder alertBuilder;
	private int ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layoutFile());
		Button editDelete = findViewById(R.id.edit_delete);
		Toolbar editToolbar = findViewById(R.id.edit_toolbar);
		alertBuilder = new AlertDialog.Builder(this);
		ID = getIntent().getIntExtra("ID", 0);
		setupViews();
		if (savedInstanceState != null)
			loadViews(savedInstanceState);
		else if (isNotCreating(ID))
			loadViews(getElement(ID));
		else
			loadViews();
		setSupportActionBar(editToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		editToolbar.setNavigationOnClickListener(view -> finish());
		editDelete.setOnClickListener(view -> {
			if (isNotCreating(ID)) {
				List<Integer> involvingReminderIDs = getElement(ID).getInvolvingReminderIDs();
				if (involvingReminderIDs.isEmpty())
					alert(R.string.delete_hint, (dialogInterface, i) -> {
						deleteElement(ID);
						finish();
					});
				else
					alert(R.string.involved_hint,
							(dialogInterface1, i1) -> {
								for (int reminderID : involvingReminderIDs)
									ElementsLibrary.deleteReminder(reminderID);
								deleteElement(ID);
								finish();
							});
			} else
				alert(R.string.delete_hint, (dialogInterface, i) -> finish());
		});
	}
	protected abstract int layoutFile();
	protected abstract void setupViews();
	protected abstract void loadViews();
	protected abstract void loadViews(T element);
	protected abstract void loadViews(Bundle savedInstanceState);
	protected abstract void deleteElement(int ID);
	protected abstract boolean saveChanges(int ID);
	protected void alert(int stringID) {
		alertBuilder.setMessage(stringID);
		alertBuilder.setPositiveButton(R.string.positive_text, null);
		alertBuilder.create().show();
	}
	protected void alert(int stringID, DialogInterface.OnClickListener positiveListener) {
		alertBuilder.setMessage(stringID);
		alertBuilder.setPositiveButton(R.string.positive_text, positiveListener);
		alertBuilder.setNegativeButton(R.string.negative_text, null);
		alertBuilder.create().show();
	}
	protected void alert(ListAdapter adapter, DialogInterface.OnClickListener listener) {
		alertBuilder.setMessage(null);
		alertBuilder.setAdapter(adapter, listener);
		alertBuilder.setNegativeButton(R.string.negative_text, null);
		alertBuilder.create().show();
	}
	protected abstract boolean isNotCreating(int ID);
	protected abstract T getElement(int ID);
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.edit_elements_toolbar_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.action_save && saveChanges(ID)) {
			finish();
			return true;
		} else return super.onOptionsItemSelected(item);
	}
}
