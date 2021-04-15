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

public class TicketActivity extends AppCompatActivity {


    private TextView txt_name_surname_state_ticket, txt_card_number_ticket, txt_typeofticket_ticket;
    private Switch switch_name_surname_ticket, switch_card_number_ticket, switch_typeofticket_ticket;
    private ConstraintLayout constraintLayout_ticket;
    private Button btn_confirm_ticket_selection, btn_ticket_name_surname_qr_scan, btn_ticket_card_number_qr_scan, btn_ticket_type_of_ticket_qr_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        initializeElements();
        loadChanges();
        //Name and surname
        switch_name_surname_ticket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_name_surname_state_ticket, btn_ticket_name_surname_qr_scan);
            }
        });
        btn_ticket_name_surname_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Ticket.M_1);
            }
        });
        //Card number
        switch_card_number_ticket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_card_number_ticket, btn_ticket_card_number_qr_scan);
            }
        });
        btn_ticket_card_number_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Ticket.M_2);
            }
        });
        //Type of ticket
        switch_typeofticket_ticket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_typeofticket_ticket, btn_ticket_type_of_ticket_qr_scan);
            }
        });
        btn_ticket_type_of_ticket_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.Ticket.M_3);
            }
        });
        btn_confirm_ticket_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Name and surname
                editor.putString(Constants.Ticket.NAME_SURNAME_TICKET_STATE, String.valueOf(txt_name_surname_state_ticket.getText()));
                editor.putBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, switch_name_surname_ticket.isChecked());
                editor.putBoolean(Constants.Ticket.QR_TICKET_NAME_SURNAME_BUTTON, btn_ticket_name_surname_qr_scan.isEnabled());
                if (!switch_name_surname_ticket.isChecked()) {
                    editor.putString(Constants.Ticket.M_1, Constants.SystemParameters.EMPTY);
                }
                //Card number
                editor.putString(Constants.Ticket.CARD_NUMBER_TICKET_STATE, String.valueOf(txt_card_number_ticket.getText()));
                editor.putBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, switch_card_number_ticket.isChecked());
                editor.putBoolean(Constants.Ticket.QR_TICKET_CARD_NUMBER_BUTTON, btn_ticket_card_number_qr_scan.isEnabled());
                if (!switch_card_number_ticket.isChecked()) {
                    editor.putString(Constants.Ticket.M_2, Constants.SystemParameters.EMPTY);
                }
                //Type of ticket
                editor.putString(Constants.Ticket.TYPE_OF_TICKET_TICKET_STATE, String.valueOf(txt_typeofticket_ticket.getText()));
                editor.putBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, switch_typeofticket_ticket.isChecked());
                editor.putBoolean(Constants.Ticket.QR_TICKET_TYPE_OF_TICKET_BUTTON, btn_ticket_type_of_ticket_qr_scan.isEnabled());
                if (!switch_typeofticket_ticket.isChecked()) {
                    editor.putString(Constants.Ticket.M_3, Constants.SystemParameters.EMPTY);
                }
                //Number of Attributes in the system:
                editor.putString(Constants.Ticket.NUMBER_OF_ATTRIBUTES_KEY, Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES);
                //Enable Verification button in Main Menu
                editor.putBoolean(Constants.MainMenu.VERIFICATION_STATE, true);
                //Setting used crypto credential
                editor.putBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, true);
                editor.commit();
                //Setting number of hidden attributes for Ticket
                editor.putInt(Constants.Ticket.TICKET_NUMBER_OF_HIDDEN_ATTRIBUTES, getNumberOfHiddenTicketAttributes(sharedPreferences));
                editor.commit();
                //Reset other cryptography credentials settings
                resetOtherCryptoCredencials(editor);
                showConfirmationSnackBar();
            }
        });
    }

    private int getNumberOfHiddenTicketAttributes(SharedPreferences sharedPreferences) {
        //Initial state: All attributes are hidden
        int number_of_hidden_attributes = Integer.parseInt(Constants.Ticket.TICKET_NUMBER_OF_ATTRIBUTES, 16);
        //Name and surname
        if (sharedPreferences.getBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Card number
        if (sharedPreferences.getBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Type of ticket
        if (sharedPreferences.getBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        return number_of_hidden_attributes;
    }

    private void scanDisclosedAttribute(String attribute) {
        Intent qrAttributeIntent = new Intent(TicketActivity.this, QRAttributeActivity.class);
        qrAttributeIntent.putExtra(Constants.SystemParameters.ATTRIBUTE, attribute);
        startActivity(qrAttributeIntent);
    }

    //This method resets all non-Ticket attributes settings (Switches, State, ...)
    private void resetOtherCryptoCredencials(SharedPreferences.Editor editor) {
        //EID
        //--Name and surname
        editor.putString(Constants.Eid.NAME_SURNAME_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Eid.NAME_SURNAME_EID_SWITCH, false);
        editor.putBoolean(Constants.Eid.QR_EID_NAME_SURNAME_BUTTON, false);
        //--Birthdate
        editor.putString(Constants.Eid.BIRTHDATE_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Eid.BIRTHDATE_EID_SWITCH, false);
        editor.putBoolean(Constants.Eid.QR_EID_BIRTHDATE_BUTTON, false);
        //--Nationality
        editor.putString(Constants.Eid.NATIONALITY_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Eid.NATIONALITY_EID_SWITCH, false);
        editor.putBoolean(Constants.Eid.QR_EID_NATIONALITY_BUTTON, false);
        //--Permanent residence
        editor.putString(Constants.Eid.PERMANENT_RESIDENCE_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Eid.PERMANENT_RESIDENCE_EID_SWITCH, false);
        editor.putBoolean(Constants.Eid.QR_EID_PERMANENT_RESIDENCE_BUTTON, false);
        //--Sex
        editor.putString(Constants.Eid.SEX_EID_STATE, Constants.Eid.DISCLOSURE_IS_NOT_REQUIRED);
        editor.putBoolean(Constants.Eid.SEX_EID_SWITCH, false);
        editor.putBoolean(Constants.Eid.QR_EID_SEX_BUTTON, false);
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
        editor.putBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false);
        editor.commit();
    }

    private void loadChanges() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //Name and surname
        txt_name_surname_state_ticket.setText(sharedPreferences.getString(Constants.Ticket.NAME_SURNAME_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED));
        switch_name_surname_ticket.setChecked(sharedPreferences.getBoolean(Constants.Ticket.NAME_SURNAME_TICKET_SWITCH, false));
        btn_ticket_name_surname_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Ticket.QR_TICKET_NAME_SURNAME_BUTTON, false));
        //Card number
        txt_card_number_ticket.setText(sharedPreferences.getString(Constants.Ticket.CARD_NUMBER_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED));
        switch_card_number_ticket.setChecked(sharedPreferences.getBoolean(Constants.Ticket.CARD_NUMBER_TICKET_SWITCH, false));
        btn_ticket_card_number_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Ticket.QR_TICKET_CARD_NUMBER_BUTTON, false));
        //Type of Ticket
        txt_typeofticket_ticket.setText(sharedPreferences.getString(Constants.Ticket.TYPE_OF_TICKET_TICKET_STATE, Constants.Ticket.DISCLOSURE_IS_NOT_REQUIRED));
        switch_typeofticket_ticket.setChecked(sharedPreferences.getBoolean(Constants.Ticket.TYPE_OF_TICKET_TICKET_SWITCH, false));
        btn_ticket_type_of_ticket_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.Ticket.QR_TICKET_TYPE_OF_TICKET_BUTTON, false));
    }

    private void initializeElements() {
        constraintLayout_ticket = findViewById(R.id.ConstraintLayout_ticket);
        txt_name_surname_state_ticket = findViewById(R.id.txt_name_surname_state_ticket);
        txt_card_number_ticket = findViewById(R.id.txt_card_number_ticket);
        txt_typeofticket_ticket = findViewById(R.id.txt_typeofticket_ticket);
        switch_name_surname_ticket = findViewById(R.id.switch_name_surname_ticket);
        switch_card_number_ticket = findViewById(R.id.switch_card_number_ticket);
        switch_typeofticket_ticket = findViewById(R.id.switch_typeofticket_ticket);
        btn_confirm_ticket_selection = findViewById(R.id.btn_confirm_ticket_selection);
        btn_ticket_name_surname_qr_scan = findViewById(R.id.btn_ticket_name_surname_qr_scan);
        btn_ticket_card_number_qr_scan = findViewById(R.id.btn_ticket_card_number_qr_scan);
        btn_ticket_type_of_ticket_qr_scan = findViewById(R.id.btn_ticket_type_of_ticket_qr_scan);
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

    private void showConfirmationSnackBar() {
        Snackbar.make(constraintLayout_ticket, "Selection confirmed !", Snackbar.LENGTH_INDEFINITE)
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