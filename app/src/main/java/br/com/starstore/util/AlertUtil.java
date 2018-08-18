package br.com.starstore.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import br.com.starstore.R;


/**
 * Created by filipenunes on 4/11/17.
 */

public class AlertUtil {

    public static void showAlert(Activity activity, String message) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity,  R.style.Theme_AppCompat_Light_Dialog);
            alertDialogBuilder.setTitle(activity.getResources().getString(R.string.atention));
            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setNegativeButton(activity.getResources().getString(R.string.ok), null);

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
        } catch (Exception e) {
            Log.e(AlertUtil.class.toString(), e.getMessage());
        }
    }

    public static void showToatsError(Context context) {
        Toast.makeText(context, context.getString(R.string.error_auth), Toast.LENGTH_SHORT).show();
    }
}
