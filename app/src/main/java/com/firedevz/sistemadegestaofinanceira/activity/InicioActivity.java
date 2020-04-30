package com.firedevz.sistemadegestaofinanceira.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.firedevz.sistemadegestaofinanceira.R;

/**
 * Created by PUDENTE on 2/5/2018.
 */

public class InicioActivity extends AppCompatActivity{



    private  static int SPLASH_TIME_OUT = 5000;
    public ProgressDialog mProgressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_inicio);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(InicioActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}