package com.example.nfcreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button btn_verification, btn_attribute_selection, btn_history, btn_parametersSetUp,btn_settings ,btnAbout, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_verification = findViewById(R.id.btn_verification);
        btn_verification.setEnabled(verificationAccess());
        btn_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verificationIntent = new Intent(MenuActivity.this, VerificationActivity.class);
                startActivity(verificationIntent);
            }
        });
        //Attributes selection
        btn_attribute_selection = findViewById(R.id.btn_attribute_selection);
        btn_attribute_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attributesSelectionIntent = new Intent(MenuActivity.this, CryptographyCredentialMenuActivity.class);
                startActivity(attributesSelectionIntent);
            }
        });
        //History
        btn_history = findViewById(R.id.btn_history);
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(MenuActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
            }
        });
        //Parameters set-up
        btn_parametersSetUp = findViewById(R.id.btn_parameters_setup);
        btn_parametersSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parametersSetpIntent = new Intent(MenuActivity.this, QRActivity.class);
                startActivity(parametersSetpIntent);
            }
        });
        //Settings
        btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
        //About
        btnAbout = findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        });
        //Exit
        btn_exit = findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit(view);
            }
        });
    }

    private boolean verificationAccess() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        return sharedPreferences.getBoolean(Constants.MainMenu.VERIFICATION_STATE, false);
    }

    public void exit(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MenuActivity.this, R.style.AlertDialogStyle)
                .setTitle("Exit")
                .setMessage("Do you want to exit ?")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_verification.setEnabled(verificationAccess());
    }
}