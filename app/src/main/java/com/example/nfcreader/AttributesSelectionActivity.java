package com.example.nfcreader;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class AttributesSelectionActivity extends AppCompatActivity {

    TextView txt_stateAttribute_1, txt_stateAttribute_2, txt_stateAttribute_3, txt_stateAttribute_4, txt_stateAttribute_5,
            txt_stateAttribute_6, txt_stateAttribute_7, txt_stateAttribute_8, txt_stateAttribute_9;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_attribute1, switch_attribute2, switch_attribute3, switch_attribute4, switch_attribute5,
            switch_attribute6, switch_attribute7, switch_attribute8, switch_attribute9;

    Button btn_confirm_selection;

    LinearLayout attributes_selection_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes_selection);
        //Linear Layout
        attributes_selection_ll = findViewById(R.id.attributes_selection_ll);
        //Attribute 1 (Text State)
        txt_stateAttribute_1 = findViewById(R.id.txt_stateAttribute_1);
        //Attribute 1 (Switch)
        switch_attribute1 = findViewById(R.id.switch_attribute1);
        switch_attribute1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_1);
            }
        });
        //Attribute 2 (Text State)
        txt_stateAttribute_2 = findViewById(R.id.txt_stateAttribute_2);
        //Attribute 2 (Switch)
        switch_attribute2 = findViewById(R.id.switch_attribute2);
        switch_attribute2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_2);
            }
        });
        //Attribute 3 (Text State)
        txt_stateAttribute_3 = findViewById(R.id.txt_stateAttribute_3);
        //Attribute 3 (Switch state)
        switch_attribute3 = findViewById(R.id.switch_attribute3);
        switch_attribute3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_3);
            }
        });
        //Attribute 4 (Text State)
        txt_stateAttribute_4 = findViewById(R.id.txt_stateAttribute_4);
        //Attribute 4 (Switch)
        switch_attribute4 = findViewById(R.id.switch_attribute4);
        switch_attribute4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_4);
            }
        });
        //Attribute 5 (Text State)
        txt_stateAttribute_5 = findViewById(R.id.txt_stateAttribute_5);
        //Attribute 5 (Switch)
        switch_attribute5 = findViewById(R.id.switch_attribute5);
        switch_attribute5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_5);
            }
        });
        //Attribute 6 (Text State)
        txt_stateAttribute_6 = findViewById(R.id.txt_stateAttribute_6);
        //Attribute 6 (Switch)
        switch_attribute6 = findViewById(R.id.switch_attribute6);
        switch_attribute6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_6);
            }
        });
        //Attribute 7 (Text State)
        txt_stateAttribute_7 = findViewById(R.id.txt_stateAttribute_7);
        //Attribute 7 (Switch)
        switch_attribute7 = findViewById(R.id.switch_attribute7);
        switch_attribute7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_7);
            }
        });
        //Attribute 8 (Text State)
        txt_stateAttribute_8 = findViewById(R.id.txt_stateAttribute_8);
        //Attribute 8 (Switch)
        switch_attribute8 = findViewById(R.id.switch_attribute8);
        switch_attribute8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_8);
            }
        });
        //Attribute 9 (Text State)
        txt_stateAttribute_9 = findViewById(R.id.txt_stateAttribute_9);
        //Attribute 9 (Switch)
        switch_attribute9 = findViewById(R.id.switch_attribute9);
        switch_attribute9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_9);
            }
        });
        //Confirmation button
        btn_confirm_selection = findViewById(R.id.btn_confirm_selection);
        btn_confirm_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmAttributeSelection();
            }
        });
        //Update Activity state
        updateActivityState();
    }


    private void updateActivityState() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //Attribute 1 (State)
        txt_stateAttribute_1.setText(sharedPreferences.getString("attribute_1_state", "State: Disclosure is not required"));
        //Attribute 1 (Switch state)
        switch_attribute1.setChecked(sharedPreferences.getBoolean("attribute_1_switch_state", false));
        //Attribute 2 (State)
        txt_stateAttribute_2.setText(sharedPreferences.getString("attribute_2_state", "State: Disclosure is not required"));
        //Attribute 2 (Switch state)
        switch_attribute2.setChecked(sharedPreferences.getBoolean("attribute_2_switch_state", false));
        //Attribute 3 (State)
        txt_stateAttribute_3.setText(sharedPreferences.getString("attribute_3_state", "State: Disclosure is not required"));
        //Attribute 3 (Switch state)
        switch_attribute3.setChecked(sharedPreferences.getBoolean("attribute_3_switch_state", false));
        //Attribute 4 (State)
        txt_stateAttribute_4.setText(sharedPreferences.getString("attribute_4_state", "State: Disclosure is not required"));
        //Attribute 4 (Switch state)
        switch_attribute4.setChecked(sharedPreferences.getBoolean("attribute_4_switch_state", false));
        //Attribute 5 (State)
        txt_stateAttribute_5.setText(sharedPreferences.getString("attribute_5_state", "State: Disclosure is not required"));
        //Attribute 5 (Switch state)
        switch_attribute5.setChecked(sharedPreferences.getBoolean("attribute_5_switch_state", false));
        //Attribute 6 (State)
        txt_stateAttribute_6.setText(sharedPreferences.getString("attribute_6_state", "State: Disclosure is not required"));
        //Attribute 6 (Switch state)
        switch_attribute6.setChecked(sharedPreferences.getBoolean("attribute_6_switch_state", false));
        //Attribute 7 (State)
        txt_stateAttribute_7.setText(sharedPreferences.getString("attribute_7_state", "State: Disclosure is not required"));
        //Attribute 7 (Switch state)
        switch_attribute7.setChecked(sharedPreferences.getBoolean("attribute_7_switch_state", false));
        //Attribute 8 (State)
        txt_stateAttribute_8.setText(sharedPreferences.getString("attribute_8_state", "State: Disclosure is not required"));
        //Attribute 8 (Switch state)
        switch_attribute8.setChecked(sharedPreferences.getBoolean("attribute_8_switch_state", false));
        //Attribute 9 (State)
        txt_stateAttribute_9.setText(sharedPreferences.getString("attribute_9_state", "State: Disclosure is not required"));
        //Attribute 9 (Switch state)
        switch_attribute9.setChecked(sharedPreferences.getBoolean("attribute_9_switch_state", false));
    }

    private void confirmAttributeSelection() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Attribute 1
        editor.putString("attribute_1_state", (String) txt_stateAttribute_1.getText());
        editor.putBoolean("attribute_1_switch_state", switch_attribute1.isChecked());
        //Attribute 1
        editor.putString("attribute_2_state", (String) txt_stateAttribute_2.getText());
        editor.putBoolean("attribute_2_switch_state", switch_attribute2.isChecked());
        //Attribute 1
        editor.putString("attribute_3_state", (String) txt_stateAttribute_3.getText());
        editor.putBoolean("attribute_3_switch_state", switch_attribute3.isChecked());
        //Attribute 1
        editor.putString("attribute_4_state", (String) txt_stateAttribute_4.getText());
        editor.putBoolean("attribute_4_switch_state", switch_attribute4.isChecked());
        //Attribute 1
        editor.putString("attribute_5_state", (String) txt_stateAttribute_5.getText());
        editor.putBoolean("attribute_5_switch_state", switch_attribute5.isChecked());
        //Attribute 1
        editor.putString("attribute_6_state", (String) txt_stateAttribute_6.getText());
        editor.putBoolean("attribute_6_switch_state", switch_attribute6.isChecked());
        //Attribute 1
        editor.putString("attribute_7_state", (String) txt_stateAttribute_7.getText());
        editor.putBoolean("attribute_7_switch_state", switch_attribute7.isChecked());
        //Attribute 1
        editor.putString("attribute_8_state", (String) txt_stateAttribute_8.getText());
        editor.putBoolean("attribute_8_switch_state", switch_attribute8.isChecked());
        //Attribute 1
        editor.putString("attribute_9_state", (String) txt_stateAttribute_9.getText());
        editor.putBoolean("attribute_9_switch_state", switch_attribute9.isChecked());
        //Commit
        editor.commit();
        showConfirmationSnackBar();

    }

    public void showConfirmationSnackBar() {
        Snackbar.make(attributes_selection_ll, "Selection confirmed !", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.parseColor("#4CAF50"))
                .setTextColor(Color.parseColor("#FFFFFF"))
                .setActionTextColor(Color.parseColor("#FFFFFF"))
                .setAction("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();
    }


    private void changeTextStateOfAttribute(boolean switchIsChecked, TextView attribute_text_state) {
        if (switchIsChecked) {
            attribute_text_state.setText("State: Disclosure is required");
            attribute_text_state.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            attribute_text_state.setText("State: Disclosure is not required");
            attribute_text_state.setTextColor(Color.parseColor("#757575"));
        }
    }
}


