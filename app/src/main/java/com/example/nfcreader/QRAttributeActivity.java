package com.example.nfcreader;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class QRAttributeActivity extends AppCompatActivity {
    private CodeScanner codeScanner;
    private CodeScannerView scannerViewAttribute;
    private Button btnConfirmQRDatAttribute;
    private TextView txtScanInfoAttribute;
    private ConstraintLayout qr_attribute_layout;
    private String attribute_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_attribute);
        qr_attribute_layout = findViewById(R.id.qr_attribute_layout);
        btnConfirmQRDatAttribute = findViewById(R.id.btn_confirm_qr_data_attribute);
        txtScanInfoAttribute = findViewById(R.id.txtScanInfoAttribute);
        scannerViewAttribute = findViewById(R.id.scannerViewAttribute);
        codeScanner = new CodeScanner(this, scannerViewAttribute);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String receivedData = result.getText();
                        if (saveResult(receivedData)) {
                            btnConfirmQRDatAttribute.setEnabled(true);
                            txtScanInfoAttribute.setText("SUCCESS !\nPlease confirm !");
                            txtScanInfoAttribute.setTextColor(Color.parseColor("#4CAF50"));
                        } else {
                            btnConfirmQRDatAttribute.setEnabled(false);
                            txtScanInfoAttribute.setText("FAIL!\nWrong data format !");
                            txtScanInfoAttribute.setTextColor(Color.parseColor("#F44336"));
                        }
                        codeScanner.startPreview();
                    }
                });
            }
        });
        scannerViewAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
        btnConfirmQRDatAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (getIntent().hasExtra(Constants.SystemParameters.ATTRIBUTE)) {
                    editor.putString(getIntent().getStringExtra(Constants.SystemParameters.ATTRIBUTE), attribute_value);
                    editor.commit();
                }
            }
        });
    }

    private boolean saveResult(String receivedData) {
        //32Bytes (64 chars) is the length of received data (m_z)
        if (receivedData.length() == 64) {
            attribute_value = receivedData;
            return true;
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();

    }

    private void requestForCamera() {
        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(QRAttributeActivity.this, "Camera Permission required!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    public void showConfirmationSnackBar() {
        Snackbar.make(qr_attribute_layout, "Confirmed !", Snackbar.LENGTH_INDEFINITE)
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