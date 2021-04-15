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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class UserDefinedActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout_userDefined;
    private TextView txt_stateAttribute_1, txt_stateAttribute_2, txt_stateAttribute_3, txt_stateAttribute_4,
            txt_stateAttribute_5, txt_stateAttribute_6, txt_stateAttribute_7, txt_stateAttribute_8,
            txt_stateAttribute_9;
    private Switch switch_attribute1, switch_attribute2, switch_attribute3, switch_attribute4, switch_attribute5,
            switch_attribute6, switch_attribute7, switch_attribute8, switch_attribute9;
    private EditText editText_number_of_attributes;
    private Button btn_confirm_userdefined_selection, btn_user_defined_atribute1_qr_scan, btn_user_defined_atribute2_qr_scan,
            btn_user_defined_atribute3_qr_scan, btn_user_defined_atribute4_qr_scan, btn_user_defined_atribute5_qr_scan,
            btn_user_defined_atribute6_qr_scan, btn_user_defined_atribute7_qr_scan, btn_user_defined_atribute8_qr_scan,
            btn_user_defined_atribute9_qr_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_defined);
        initializeElements();
        loadChanges();
        //Attribute 1
        switch_attribute1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_1, btn_user_defined_atribute1_qr_scan);
            }
        });
        btn_user_defined_atribute1_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_1);
            }
        });
        //Attribute 2
        switch_attribute2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_2, btn_user_defined_atribute2_qr_scan);
            }
        });
        btn_user_defined_atribute2_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_2);
            }
        });
        //Attribute 3
        switch_attribute3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_3, btn_user_defined_atribute3_qr_scan);
            }
        });
        btn_user_defined_atribute3_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_3);
            }
        });
        //Attribute 4
        switch_attribute4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_4, btn_user_defined_atribute4_qr_scan);
            }
        });
        btn_user_defined_atribute4_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_4);
            }
        });
        //Attribute 5
        switch_attribute5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_5, btn_user_defined_atribute5_qr_scan);
            }
        });
        btn_user_defined_atribute5_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_5);
            }
        });
        //Attribute 6
        switch_attribute6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_6, btn_user_defined_atribute6_qr_scan);
            }
        });
        btn_user_defined_atribute6_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_6);
            }
        });
        //Attribute 7
        switch_attribute7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_7, btn_user_defined_atribute7_qr_scan);
            }
        });
        btn_user_defined_atribute7_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_7);
            }
        });
        //Attribute 8
        switch_attribute8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_8, btn_user_defined_atribute8_qr_scan);
            }
        });
        btn_user_defined_atribute8_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_8);
            }
        });
        //Attribute 9
        switch_attribute9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeTextStateOfAttribute(isChecked, txt_stateAttribute_9, btn_user_defined_atribute9_qr_scan);
            }
        });
        btn_user_defined_atribute9_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDisclosedAttribute(Constants.UserDefined.M_9);
            }
        });
        //Confirmation button
        btn_confirm_userdefined_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Attribute 1
                editor.putString(Constants.UserDefined.ATTRIBUTE_1_STATE, String.valueOf(txt_stateAttribute_1.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_1_SWITCH, switch_attribute1.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_1_BUTTON, btn_user_defined_atribute1_qr_scan.isEnabled());
                if (!switch_attribute1.isChecked()) {
                    editor.putString(Constants.UserDefined.M_1, Constants.SystemParameters.EMPTY);
                }
                //Attribute 2
                editor.putString(Constants.UserDefined.ATTRIBUTE_2_STATE, String.valueOf(txt_stateAttribute_2.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_2_SWITCH, switch_attribute2.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_2_BUTTON, btn_user_defined_atribute2_qr_scan.isEnabled());
                if (!switch_attribute2.isChecked()) {
                    editor.putString(Constants.UserDefined.M_2, Constants.SystemParameters.EMPTY);
                }
                //Attribute 3
                editor.putString(Constants.UserDefined.ATTRIBUTE_3_STATE, String.valueOf(txt_stateAttribute_3.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_3_SWITCH, switch_attribute3.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_3_BUTTON, btn_user_defined_atribute3_qr_scan.isEnabled());
                if (!switch_attribute3.isChecked()) {
                    editor.putString(Constants.UserDefined.M_3, Constants.SystemParameters.EMPTY);
                }
                //Attribute 4
                editor.putString(Constants.UserDefined.ATTRIBUTE_4_STATE, String.valueOf(txt_stateAttribute_4.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_4_SWITCH, switch_attribute4.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_4_BUTTON, btn_user_defined_atribute4_qr_scan.isEnabled());
                if (!switch_attribute4.isChecked()) {
                    editor.putString(Constants.UserDefined.M_4, Constants.SystemParameters.EMPTY);
                }
                //Attribute 5
                editor.putString(Constants.UserDefined.ATTRIBUTE_5_STATE, String.valueOf(txt_stateAttribute_5.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_5_SWITCH, switch_attribute5.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_5_BUTTON, btn_user_defined_atribute5_qr_scan.isEnabled());
                if (!switch_attribute5.isChecked()) {
                    editor.putString(Constants.UserDefined.M_5, Constants.SystemParameters.EMPTY);
                }
                //Attribute 6
                editor.putString(Constants.UserDefined.ATTRIBUTE_6_STATE, String.valueOf(txt_stateAttribute_6.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_6_SWITCH, switch_attribute6.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_6_BUTTON, btn_user_defined_atribute6_qr_scan.isEnabled());
                if (!switch_attribute6.isChecked()) {
                    editor.putString(Constants.UserDefined.M_6, Constants.SystemParameters.EMPTY);
                }
                //Attribute 7
                editor.putString(Constants.UserDefined.ATTRIBUTE_7_STATE, String.valueOf(txt_stateAttribute_7.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_7_SWITCH, switch_attribute7.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_7_BUTTON, btn_user_defined_atribute7_qr_scan.isEnabled());
                if (!switch_attribute7.isChecked()) {
                    editor.putString(Constants.UserDefined.M_7, Constants.SystemParameters.EMPTY);
                }
                //Attribute 8
                editor.putString(Constants.UserDefined.ATTRIBUTE_8_STATE, String.valueOf(txt_stateAttribute_8.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_8_SWITCH, switch_attribute8.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_8_BUTTON, btn_user_defined_atribute8_qr_scan.isEnabled());
                if (!switch_attribute8.isChecked()) {
                    editor.putString(Constants.UserDefined.M_8, Constants.SystemParameters.EMPTY);
                }
                //Attribute 9
                editor.putString(Constants.UserDefined.ATTRIBUTE_9_STATE, String.valueOf(txt_stateAttribute_9.getText()));
                editor.putBoolean(Constants.UserDefined.ATTRIBUTE_9_SWITCH, switch_attribute9.isChecked());
                editor.putBoolean(Constants.UserDefined.QR_ATTRIBUTE_9_BUTTON, btn_user_defined_atribute9_qr_scan.isEnabled());
                if (!switch_attribute9.isChecked()) {
                    editor.putString(Constants.UserDefined.M_9, Constants.SystemParameters.EMPTY);
                }
                //Number of Attributes in the system:
                if (setNumberOfAttributes(editor)) {
                    //Enable Verification button in Main Menu
                    editor.putBoolean(Constants.MainMenu.VERIFICATION_STATE, true);
                    editor.putBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, true);
                } else {
                    //Disable Verification button in Main Menu
                    editor.putBoolean(Constants.MainMenu.VERIFICATION_STATE, false);
                    editor.putBoolean(Constants.UserDefined.USER_DEFINED_CRYPTO_CREDENCIAL, false);
                }
                //Setting number of hidden attributes for User Defined
                editor.commit();
                editor.putInt(Constants.UserDefined.USER_DEFINED_NUMBER_OF_HIDDEN_ATTRIBUTES, getNumberOfHiddenUserDefinedAttributes(sharedPreferences));
                editor.commit();
                //Reset other cryptography credentials settings
                resetOtherCryptoCredencials(editor);
                showConfirmationSnackBar();
            }
        });
    }

    private int getNumberOfHiddenUserDefinedAttributes(SharedPreferences sharedPreferences) {
        //Initial state: All attributes are hidden
        int number_of_hidden_attributes = Integer.parseInt(sharedPreferences.getString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_KEY, "00"), 16);
        //Attribute 1
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_1_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 2
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_2_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 3
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_3_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 4
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_4_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 5
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_5_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 6
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_6_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 7
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_7_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 8
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_8_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        //Attribute 9
        if (sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_9_SWITCH, false)) {
            number_of_hidden_attributes--;
        }
        return number_of_hidden_attributes;
    }


    private void scanDisclosedAttribute(String attribute) {
        Intent qrAttributeIntent = new Intent(UserDefinedActivity.this, QRAttributeActivity.class);
        qrAttributeIntent.putExtra(Constants.SystemParameters.ATTRIBUTE, attribute);
        startActivity(qrAttributeIntent);
    }

    // True -> There is at least one attribute in the system
    // False -> No attribute in the system
    private boolean setNumberOfAttributes(SharedPreferences.Editor editor) {
        String number_of_attributes_in_the_system_string = "";
        if (editText_number_of_attributes.getText().toString().equals("") || editText_number_of_attributes.getText().toString().equals("0")) {
            number_of_attributes_in_the_system_string = "00";
            editor.putString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_KEY, number_of_attributes_in_the_system_string);
            editor.putString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_KEY, number_of_attributes_in_the_system_string.substring(1, 2));
            editor.commit();
            return false;
        } else {
            int number_of_attributes_in_the_system_int = Integer.parseInt(editText_number_of_attributes.getText().toString());
            if (number_of_attributes_in_the_system_int > 9) {
                number_of_attributes_in_the_system_string = "09";
            } else {
                number_of_attributes_in_the_system_string = "0" + Integer.toHexString(number_of_attributes_in_the_system_int);
            }
            editor.putString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_KEY, number_of_attributes_in_the_system_string);
            editor.putString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_KEY, number_of_attributes_in_the_system_string.substring(1, 2));
            editor.commit();
            return true;
        }
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
        //Resetting other Crypto Credential
        editor.putBoolean(Constants.Ticket.TICKET_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.EmployeeCard.EMPLOYEE_CARD_CRYPTO_CREDENCIAL, false);
        editor.putBoolean(Constants.Eid.EID_CRYPTO_CREDENCIAL, false);
        editor.commit();
    }

    private void loadChanges() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        //Attribute 1
        txt_stateAttribute_1.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_1_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute1.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_1_SWITCH, false));
        btn_user_defined_atribute1_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_1_BUTTON, false));
        //Attribute 2
        txt_stateAttribute_2.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_2_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute2.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_2_SWITCH, false));
        btn_user_defined_atribute2_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_2_BUTTON, false));
        //Attribute 3
        txt_stateAttribute_3.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_3_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute3.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_3_SWITCH, false));
        btn_user_defined_atribute3_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_3_BUTTON, false));
        //Attribute 4
        txt_stateAttribute_4.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_4_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute4.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_4_SWITCH, false));
        btn_user_defined_atribute4_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_4_BUTTON, false));
        //Attribute 5
        txt_stateAttribute_5.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_5_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute5.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_5_SWITCH, false));
        btn_user_defined_atribute5_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_5_BUTTON, false));
        //Attribute 6
        txt_stateAttribute_6.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_6_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute6.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_6_SWITCH, false));
        btn_user_defined_atribute6_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_6_BUTTON, false));
        //Attribute 7
        txt_stateAttribute_7.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_7_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute7.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_7_SWITCH, false));
        btn_user_defined_atribute7_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_7_BUTTON, false));
        //Attribute 8
        txt_stateAttribute_8.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_8_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute8.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_8_SWITCH, false));
        btn_user_defined_atribute8_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_8_BUTTON, false));
        //Attribute 9
        txt_stateAttribute_9.setText(sharedPreferences.getString(Constants.UserDefined.ATTRIBUTE_9_STATE, Constants.UserDefined.DISCLOSURE_IS_NOT_REQUIRED));
        switch_attribute9.setChecked(sharedPreferences.getBoolean(Constants.UserDefined.ATTRIBUTE_9_SWITCH, false));
        btn_user_defined_atribute9_qr_scan.setEnabled(sharedPreferences.getBoolean(Constants.UserDefined.QR_ATTRIBUTE_9_BUTTON, false));
        //EditText number of attributes in the system
        editText_number_of_attributes.setText(sharedPreferences.getString(Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_KEY, Constants.UserDefined.NUMBER_OF_ATTRIBUTES_EDIT_TEXT_DEFAULT_VALUE));
    }

    private void initializeElements() {
        constraintLayout_userDefined = findViewById(R.id.constraintLayout_userDefined);
        txt_stateAttribute_1 = findViewById(R.id.txt_stateAttribute_1);
        txt_stateAttribute_2 = findViewById(R.id.txt_stateAttribute_2);
        txt_stateAttribute_3 = findViewById(R.id.txt_stateAttribute_3);
        txt_stateAttribute_4 = findViewById(R.id.txt_stateAttribute_4);
        txt_stateAttribute_5 = findViewById(R.id.txt_stateAttribute_5);
        txt_stateAttribute_6 = findViewById(R.id.txt_stateAttribute_6);
        txt_stateAttribute_7 = findViewById(R.id.txt_stateAttribute_7);
        txt_stateAttribute_8 = findViewById(R.id.txt_stateAttribute_8);
        txt_stateAttribute_9 = findViewById(R.id.txt_stateAttribute_9);
        switch_attribute1 = findViewById(R.id.switch_attribute1);
        switch_attribute2 = findViewById(R.id.switch_attribute2);
        switch_attribute3 = findViewById(R.id.switch_attribute3);
        switch_attribute4 = findViewById(R.id.switch_attribute4);
        switch_attribute5 = findViewById(R.id.switch_attribute5);
        switch_attribute6 = findViewById(R.id.switch_attribute6);
        switch_attribute7 = findViewById(R.id.switch_attribute7);
        switch_attribute8 = findViewById(R.id.switch_attribute8);
        switch_attribute9 = findViewById(R.id.switch_attribute9);
        btn_user_defined_atribute1_qr_scan = findViewById(R.id.btn_user_defined_atribute1_qr_scan);
        btn_user_defined_atribute2_qr_scan = findViewById(R.id.btn_user_defined_atribute2_qr_scan);
        btn_user_defined_atribute3_qr_scan = findViewById(R.id.btn_user_defined_atribute3_qr_scan);
        btn_user_defined_atribute4_qr_scan = findViewById(R.id.btn_user_defined_atribute4_qr_scan);
        btn_user_defined_atribute5_qr_scan = findViewById(R.id.btn_user_defined_atribute5_qr_scan);
        btn_user_defined_atribute6_qr_scan = findViewById(R.id.btn_user_defined_atribute6_qr_scan);
        btn_user_defined_atribute7_qr_scan = findViewById(R.id.btn_user_defined_atribute7_qr_scan);
        btn_user_defined_atribute8_qr_scan = findViewById(R.id.btn_user_defined_atribute8_qr_scan);
        btn_user_defined_atribute9_qr_scan = findViewById(R.id.btn_user_defined_atribute9_qr_scan);
        editText_number_of_attributes = findViewById(R.id.editText_number_of_attributes);
        btn_confirm_userdefined_selection = findViewById(R.id.btn_confirm_userdefined_selection);

    }

    private void showConfirmationSnackBar() {
        Snackbar.make(constraintLayout_userDefined, "Selection confirmed !", Snackbar.LENGTH_INDEFINITE)
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