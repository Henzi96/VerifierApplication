package com.example.nfcreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CryptographyCredentialMenuActivity extends AppCompatActivity {
    private Button btn_eid, btn_ticket, btn_employee_card, btn_userDefined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptography_credential_menu);
        btn_eid = findViewById(R.id.btn_eid_selection);
        btn_ticket = findViewById(R.id.btn_ticket_selection);
        btn_employee_card = findViewById(R.id.btn_employeeCard_selection);
        btn_userDefined = findViewById(R.id.btn_userDefined_selection);
        //EID
        btn_eid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eidIntent = new Intent(CryptographyCredentialMenuActivity.this, EidActivity.class);
                startActivity(eidIntent);
            }
        });
        //TICKET
        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticketIntent = new Intent(CryptographyCredentialMenuActivity.this, TicketActivity.class);
                startActivity(ticketIntent);
            }
        });
        //EMPLOYEE CARD
        btn_employee_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employeeCardIntent = new Intent(CryptographyCredentialMenuActivity.this, EmployeeCardActivity.class);
                startActivity(employeeCardIntent);
            }
        });
        //USER DEFINED
        btn_userDefined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userDefinedIntent = new Intent(CryptographyCredentialMenuActivity.this, UserDefinedActivity.class);
                startActivity(userDefinedIntent);
            }
        });
    }


}