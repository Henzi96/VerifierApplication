package com.example.nfcreader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class EidActivity extends AppCompatActivity {
    private TextView txt_name_surname_state_eid, txt_birthdate_state, txt_nationality_state, txt_permanent_residence_state, txt_sex_state;
    private Switch switch_name_surname_eid, switch_birthdate, switch_nationality, switch_permanent_residence, switch_sex;
    private Button btn_confirm_eid_selection, btn_eid_name_surname_qr_scan, btn_eid_birthdate_qr_scan, btn_eid_nationality_qr_scan, btn_eid_permanent_residence_qr_scan, btn_eid_sex_qr_scan;
    private ConstraintLayout ConstraintLayout_eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eid);
        initializeElements();
        loadChanges();
        //NAME_SURNAME
        switch_name_surname_eid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_name_surname_state_eid, btn_eid_name_surname_qr_scan);
            }
        });
        btn_eid_name_surname_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Eid.M_1);
            }
        });
        //BIRTHDATE
        switch_birthdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_birthdate_state, btn_eid_birthdate_qr_scan);
            }
        });
        btn_eid_birthdate_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Eid.M_2);
            }
        });
        //NATIONALITY
        switch_nationality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_nationality_state, btn_eid_nationality_qr_scan);
            }
        });
        btn_eid_nationality_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Eid.M_3);
            }
        });
        //PERMANENT RESIDENCE
        switch_permanent_residence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_permanent_residence_state, btn_eid_permanent_residence_qr_scan);
            }
        });
        btn_eid_permanent_residence_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Eid.M_4);
            }
        });
        //SEX
        switch_sex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_sex_state, btn_eid_sex_qr_scan);
            }
        });
        btn_eid_sex_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Eid.M_5);
            }
        });
        //Confirmation button
        btn_confirm_eid_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Name and surname
                editor.putString(Constants.Eid.NAME_SURNAME_EID_STATE, String.valueOf(txt_name_surname_state_eid.getText()));
                editor.putBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, switch_name_surname_eid.isChecked());
                editor.putBoolean(Constants.Eid.QR_EID_NAME_SURNAME_BUTTON, btn_eid_name_surname_qr_scan.isEnabled());
                if (!switch_name_surname_eid.isChecked()) {
                    editor.putString(Constants.Eid.M_1, Constants.SystemParameters.EMPTY);
                }
                //Birthdate
                editor.putString(Constants.Eid.BIRTHDATE_EID_STATE, String.valueOf(txt_birthdate_state.getText()));
                editor.putBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, switch_birthdate.isChecked());
                editor.putBoolean(Constants.Eid.QR_EID_BIRTHDATE_BUTTON, btn_eid_birthdate_qr_scan.isEnabled());
                if (!switch_birthdate.isChecked()) {
                    editor.putString(Constants.Eid.M_2, Constants.SystemParameters.EMPTY);
                }
                //Nationality
                editor.putString(Constants.Eid.NATIONALITY_EID_STATE, String.valueOf(txt_nationality_state.getText()));
                editor.putBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, switch_nationality.isChecked());
                editor.putBoolean(Constants.Eid.QR_EID_NATIONALITY_BUTTON, btn_eid_nationality_qr_scan.isEnabled());
                if (!switch_nationality.isChecked()) {
                    editor.putString(Constants.Eid.M_3, Constants.SystemParameters.EMPTY);
                }
                //Permanent residence
                editor.putString(Constants.Eid.PERMANENT_RESIDENCE_EID_STATE, String.valueOf(txt_permanent_residence_state.getText()));
                editor.putBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, switch_permanent_residence.isChecked());
                editor.putBoolean(Constants.Eid.QR_EID_PERMANENT_RESIDENCE_BUTTON, btn_eid_permanent_residence_qr_scan.isEnabled());
                if (!switch_permanent_residence.isChecked()) {
                    editor.putString(Constants.Eid.M_4, Constants.SystemParameters.EMPTY);
                }
                //Sex
                editor.putString(Constants.Eid.SEX_EID_STATE, String.valueOf(txt_sex_state.getText()));
                editor.putBoolean(Constants.Eid.SEX_EID_SWITCH, switch_sex.isChecked());
                editor.putBoolean(Constants.Eid.QR_EID_SEX_BUTTON, btn_eid_sex_qr_scan.isEnabled());
                if (!switch_sex.isChecked()) {
                    editor.putString(Constants.Eid.M_5, Constants.SystemParameters.EMPTY);
                }
                //Number of Attributes in the system:
                editor.putString(Constants.Eid.NUMBER_OF_ATTRIBUTES_KEY, Constants.Eid.EID_NUMBER_OF_ATTRIBUTES);
                //Enable Verification button in Main Menu
                editor.putBoolean(Constants.MainMenu.VERIFICATION_STATE, true);
                //Setting used crypto credential
                editor.putBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, true);
                //Setting number of hidden attributes for EID
                editor.commit();
                editor.putInt(Constants.Eid.EID_NUMBER_OF_HIDDEN_ATTRIBUTES, getNumberOfHiddenEidAttributes(sharedPreferences));
                editor.commit();
                //Reset other cryptography credentials settings
                resetOtherCryptoCredencials(editor);
                showConfirmationSnackBar();
            }
        });


    }

    private void scanDisclosedAttribute(String attribute) {
        Intent qrAttributeIntent = new Intent(EidActivity.this, QRAttributeActivity.class);
        qrAttributeIntent.putExtra(Constants.SystemParameters.ATTRIBUTE, attribute);
        startActivity(qrAttributeIntent);
    }

    private int getNumberOfHiddenEidAttributes(SharedPreferences sharedPreferences) {
        //Initial state: All attributes are hidden
        int number_of_hidden_attributes = Integer.parseInt(Constants.Eid.EID_NUMBER_OF_ATTRIBUTES, 16);
        //Name and surname
        if (sharedPreferences.getBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Birthdate
        if (sharedPreferences.getBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Nationality
        if (sharedPreferences.getBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Permanent Residence
        if (sharedPreferences.getBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Sex
        if (sharedPreferences.getBoolean(Constants.Eid.SEX_EID_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        return number_of_hidden_attributes;
    }

    private void changeTextStateOfAttribute(boolean isChecked, TextView txt_state, Button qr_scan_button) {
        if (isChecked) {
            txt_state.setText("Disclosure is required");
            qr_scan_button.setEnabled(true);
        } else {
            txt_state.setText("Disclosure is not required");
            qr_scan_button.setEnabled(false);
        }
    }

    //This method resets all non-EID attributes settings (Switches, State, ...)
    private void resetOtherCryptoCredencials(SharedPreferences.Editor editor) {
        //TICKET
        //--Name and surname
        editor.putString(Constants.Ticket.NAME_SURNAME_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, false);
        editor.putBoolean(Constants.Ticket.QR_TICKET_NAME_SURNAME_BUTTON, false);
        //--Card number
        editor.putString(Constants.Ticket.CARD_NUMBER_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, false);
        editor.putBoolean(Constants.Ticket.QR_TICKET_CARD_NUMBER_BUTTON, false);
        //--Type of Ticket
        editor.putString(Constants.Ticket.TYPE_OF_TICKET_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, false);
        editor.putBoolean(Constants.Ticket.QR_TICKET_TYPE_OF_TICKET_BUTTON, false);
        //EMPLOYEE CARD
        //--Name and surname
        editor.putString(Constants.EmployeeCard.NAME_SURNAME_EMPLOYEE_CARD_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.EmployeeCard.NAME_SURNAME_EMPLOYEE_CARD_SWITCH, false);
        editor.putBoolean(Constants.EmployeeCard.QR_EMPLOYEE_CARD_NAME_SURNAME_BUTTON, false);
        //--Employee ID
        editor.putString(Constants.EmployeeCard.EMPLOYEE_ID_EMPLOYEE_CARD_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYEE_ID_EMPLOYEE_CARD_SWITCH, false);
        editor.putBoolean(Constants.EmployeeCard.QR_EMPLOYEE_CARD_EMPLOYEE_ID_BUTTON, false);
        //--Employer
        editor.putString(Constants.EmployeeCard.EMPLOYER_EMPLOYEE_CARD_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYER_EMPLOYEE_CARD_SWITCH, false);
        editor.putBoolean(Constants.EmployeeCard.QR_EMPLOYEE_CARD_EMPLOYER_BUTTON, false);
        //--Employee position
        editor.putString(Constants.EmployeeCard.EMPLOYEE_POSITION_EMPLOYEE_CARD_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYEE_POSITION_EMPLOYEE_CARD_SWITCH, false);
        editor.putBoolean(Constants.EmployeeCard.QR_EMPLOYEE_CARD_EMPLOYEE_POSITION_BUTTON, false);
        //USER DEFINED
        //--Attribute 1
        editor.putString(Constants.UserDefined.ATTRIBUTE_1_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_1_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_1_BUTTON, false);
        //--Attribute 2
        editor.putString(Constants.UserDefined.ATTRIBUTE_2_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_2_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_2_BUTTON, false);
        //--Attribute 3
        editor.putString(Constants.UserDefined.ATTRIBUTE_3_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_3_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_3_BUTTON, false);
        //--Attribute 4
        editor.putString(Constants.UserDefined.ATTRIBUTE_4_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_4_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_4_BUTTON, false);
        //--Attribute 5
        editor.putString(Constants.UserDefined.ATTRIBUTE_5_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_5_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_5_BUTTON, false);
        //--Attribute 6
        editor.putString(Constants.UserDefined.ATTRIBUTE_6_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_6_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_6_BUTTON, false);
        //--Attribute 7
        editor.putString(Constants.UserDefined.ATTRIBUTE_7_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_7_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_7_BUTTON, false);
        //--Attribute 8
        editor.putString(Constants.UserDefined.ATTRIBUTE_8_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_8_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_8_BUTTON, false);
        //--Attribute 9
        editor.putString(Constants.UserDefined.ATTRIBUTE_9_STATE, Constants.EmployeeCard.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.UserDefined.ATTRIBUTE_9_SWITCH, false);
        editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_9_BUTTON, false);
        //--Edit text User defined
        editor.putString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_KEY, Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_DEFAULT_VALUE);
        //Resetting other Crypto Credential
        editor.putBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false);
        editor.commit();
    }

    private void loadChanges() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //Name and surname
        txt_name_surname_state_eid.setText(sharedPreferences.getString(Constants.Eid.NAME_SURNAME_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED));
        switch_name_surname_eid.setChecked(sharedPreferences.getBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, false));
        btn_eid_name_surname_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Eid.QR_EID_NAME_SURNAME_BUTTON, false));
        //Birthdate
        txt_birthdate_state.setText(sharedPreferences.getString(Constants.Eid.BIRTHDATE_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED));
        switch_birthdate.setChecked(sharedPreferences.getBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, false));
        btn_eid_birthdate_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Eid.QR_EID_BIRTHDATE_BUTTON, false));
        //Nationality
        txt_nationality_state.setText(sharedPreferences.getString(Constants.Eid.NATIONALITY_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED));
        switch_nationality.setChecked(sharedPreferences.getBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, false));
        btn_eid_nationality_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Eid.QR_EID_NATIONALITY_BUTTON, false));
        //Permanent residence
        txt_permanent_residence_state.setText(sharedPreferences.getString(Constants.Eid.PERMANENT_RESIDENCE_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED));
        switch_permanent_residence.setChecked(sharedPreferences.getBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, false));
        btn_eid_permanent_residence_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Eid.QR_EID_PERMANENT_RESIDENCE_BUTTON, false));
        //Sex
        txt_sex_state.setText(sharedPreferences.getString(Constants.Eid.SEX_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED));
        switch_sex.setChecked(sharedPreferences.getBoolean(Constants.Eid.SEX_EID_SWITCH, false));
        btn_eid_sex_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Eid.QR_EID_SEX_BUTTON, false));
    }

    private void initializeElements() {
        ConstraintLayout_eid = findViewById(R.id.ConstraintLayout_eid);
        txt_name_surname_state_eid = findViewById(R.id.txt_name_surname_state_eid);
        txt_birthdate_state = findViewById(R.id.txt_birthdate_state);
        txt_nationality_state = findViewById(R.id.txt_nationality_state);
        txt_permanent_residence_state = findViewById(R.id.txt_permanent_residence_state);
        txt_sex_state = findViewById(R.id.txt_sex_state);
        switch_name_surname_eid = findViewById(R.id.switch_name_surname_eid);
        switch_birthdate = findViewById(R.id.switch_birthdate);
        switch_nationality = findViewById(R.id.switch_nationality);
        switch_permanent_residence = findViewById(R.id.switch_permanent_residence);
        switch_sex = findViewById(R.id.switch_sex);
        btn_confirm_eid_selection = findViewById(R.id.btn_confirm_eid_selection);
        btn_eid_name_surname_qr_scan = findViewById(R.id.btn_eid_name_surname_qr_scan);
        btn_eid_birthdate_qr_scan = findViewById(R.id.btn_eid_birthdate_qr_scan);
        btn_eid_nationality_qr_scan = findViewById(R.id.btn_eid_nationality_qr_scan);
        btn_eid_permanent_residence_qr_scan = findViewById(R.id.btn_eid_permanent_residence_qr_scan);
        btn_eid_sex_qr_scan = findViewById(R.id.btn_eid_sex_qr_scan);
    }

    private void showConfirmationSnackBar() {
        Snackbar.make(ConstraintLayout_eid, "Selection confirmed !", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.parseColor("#4CAF50"))
                .setTextColor(Color.parseColor("#FFFFFF"))
                .setActionTextColor(Color.parseColor("#FFFFFF"))
                .setAction("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                }).show();
    }
}