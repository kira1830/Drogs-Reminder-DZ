package amrani_hichem.apps.drugsreminder.libraries;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;

import amrani_hichem.apps.drugsreminder.preferences.Preferences;
import amrani_hichem.apps.drugsreminder.services.NotificationService;
import amrani_hichem.apps.drugsreminder.util.BackgroundThread;

public final class Initializer {
	public static void init(Context context, OnInitializedListener listener) {
		AsyncTask.execute(() -> {
			Looper.prepare();
			ElementsLibrary.init(context);
			Preferences.init(context);
			NotificationService.init(context);
			BackgroundThread.init();

			if (listener != null) listener.onInitialized();
		});
	}
	public static void init(Context context) {
		init(context, null);
	}
	public interface OnInitializedListener {
		void onInitialized();
	}
}
