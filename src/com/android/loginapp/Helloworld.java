package com.android.loginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.android.Intents;

import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Create a login page to send to server to log into icbf for your herd info
 */
public class Helloworld extends Activity {
    private static final Logger logger = Logger.getLogger("logger");
    public TextView helloTextView;
    public TextView returnTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);
        initControls();

    }

    public void initControls() {
        helloTextView = (TextView) findViewById(R.id.myTextView);
    }

    public void scanNow(View view) {
        logger.log(Level.INFO, "button works!");
        //"com.google.zxing.client.android.SCAN"
        Intent intent = new Intent(Helloworld.this, Intents.class);
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "SCAN_MODE");
        logger.log(Level.INFO, "after put extras");
        startActivityForResult(intent, 0);
        logger.log(Level.INFO, "start activity for result");
        returnTxt = (TextView) findViewById(R.id.returnTxt);
        Log.d("test", "button works!");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                logger.log(Level.INFO, "in onActivityforRes successfully");
                String contents = intent.getStringExtra("SCAN_RESULT");
               // System.out.println(contents);
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.i("xZing", "contents: " + contents + " format: " + format); // Handle successful scan
                returnTxt.setText("Result" + contents + format);
            } else if (resultCode == RESULT_CANCELED) { // Handle cancel
                Log.i("xZing", "Cancelled");
            }
        }
    }
}

