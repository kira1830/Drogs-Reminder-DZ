package amrani_hichem.apps.drugsreminder.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import amrani_hichem.apps.drugsreminder.BuildConfig;
import amrani_hichem.apps.drugsreminder.services.GuardService;
import amrani_hichem.apps.drugsreminder.services.ProtectionService;

public class GuardReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (!GuardService.isRunning()) {
			// start GuardService
			Intent guardIntent = new Intent(ProtectionService.ACTION_GUARD);
			String guardClassName = BuildConfig.APPLICATION_ID + ".services.GuardService";
			guardIntent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID,
					guardClassName));
			context.startService(guardIntent);
			// start ProtectionService
			Intent protectionIntent = new Intent(GuardService.ACTION_PROTECTION);
			String protectionClassName = BuildConfig.APPLICATION_ID + ".services.ProtectionService";
			protectionIntent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID,
					protectionClassName));
			context.startService(protectionIntent);
		}
	}
}
