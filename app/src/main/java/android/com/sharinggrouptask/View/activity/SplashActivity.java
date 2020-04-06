package android.com.sharinggrouptask.View.activity;

import android.com.sharinggrouptask.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
/******************************************************************************
 * Module: SplashActivity
 *
 * File Name: SplashActivity.java
 *
 * Description: Source file for SplashActivity
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        navigateToMainActivity();
    }
    /************************************************************************************
     * Function Name: navigateToMainActivity
     * Parameters (in): None
     * Return value: None
     * Description: responsible to navigate To MainActivity
     **********************************************************************************/
    private void navigateToMainActivity(){
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, 3000);
    }
}
