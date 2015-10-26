package com.simonmacdonald.cordova.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephoneNumber extends CordovaPlugin {

    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
        if (action.equals("get")) {
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        TelephonyManager telephonyManager =
                                (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);

                        String result = telephonyManager.getLine1Number();
                        if (result != null && !result.isEmpty()) {
                            callbackContext.success(result);
                        }
                    } catch (java.lang.SecurityException e) {
                        // Permissions are disabled
                        callbackContext.error(e.getMessage());
                        return;
                    }
                    callbackContext.error("");
                }
            });
            return true;
        }
        return false;
    }
}
