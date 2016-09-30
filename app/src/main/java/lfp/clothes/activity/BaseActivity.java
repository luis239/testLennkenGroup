package lfp.clothes.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import lfp.clothes.R;
import lfp.clothes.event.BusManager;

/**
 * Created by lfagundez on 28/9/16.
 * Activity Base de la cual heredarán las otras actividades de la aplicación,
 * para facilitar el uso de métodos y reutilizar código
 */
public class BaseActivity extends AppCompatActivity {



    protected static ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getClass().getSimpleName(), "onResume: " + this.getClass().getSimpleName());
        BusManager.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause: " + this.getClass().getSimpleName());
        BusManager.unregister(this);
        onFinishTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onFinishTask();
    }

    public static Context getContext() {
        return getContext();
    }

    protected void onFinishTask() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    protected void AlertDialog(String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void AlertDialogToExit(String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.info));
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
