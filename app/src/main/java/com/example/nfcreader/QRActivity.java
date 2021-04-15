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

public class QRActivity extends AppCompatActivity {

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private Button btnConfirmQRData;
    private TextView txtScanInfo;
    private String[] sk_Verifier;
    private String epoch;
    private String pk_RA;
    private String h1;
    private String h2;
    private ConstraintLayout qr_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);
        qr_layout = findViewById(R.id.qr_layout);
        btnConfirmQRData = findViewById(R.id.btn_confirm_qr_data);
        txtScanInfo = findViewById(R.id.txtScanInfo);
        scannerView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String receivedData = result.getText();
                        if (saveResult(receivedData)) {
                            btnConfirmQRData.setEnabled(true);
                            txtScanInfo.setText("SUCCESS !\nPlease confirm !");
                            txtScanInfo.setTextColor(Color.parseColor("#4CAF50"));
                        } else {
                            btnConfirmQRData.setEnabled(false);
                            txtScanInfo.setText("FAIL!\nWrong data format !");
                            txtScanInfo.setTextColor(Color.parseColor("#F44336"));
                        }
                        codeScanner.startPreview();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });
        btnConfirmQRData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //epoch
                editor.putString(Constants.SystemParameters.EPOCH, epoch);
                //x_0, x1....x_9
                for (int i = 0; i < sk_Verifier.length - 1; i++) {
                    editor.putString(Constants.SystemParameters.X_ + i, sk_Verifier[i]);
                }
                //x_r
                editor.putString(Constants.SystemParameters.X_R, sk_Verifier[10]);
                //pk_RA
                editor.putString(Constants.SystemParameters.PK_RA, pk_RA);
                //h1
                editor.putString(Constants.SystemParameters.H_1, h1);
                //h2
                editor.putString(Constants.SystemParameters.H_2, h2);
                editor.commit();
                showConfirmationSnackBar();

            }
        });
    }

    private boolean saveResult(String result) {
        //1100 is the length of received data (epoch, x0, x1....xn-1, xr, pkRA, h1, h2)
        //epoch..............................[4B]....substring(0, 8)
        //(x0, x1....xn-1)...................each [32B]....substring(8, 712)
        //pkRA...............................[64B]....substring(712, 840)
        //h1.................................[65B]....substring(840, 970)
        //h2.................................[65B]....substring(970, 1100)
        if (result.length() == 1100) {
            epoch = result.substring(0, 8);
            sk_Verifier = new String[11];
            int indexHolder = 8;
            for (int i = 0; i <= 10; i++) {
                sk_Verifier[i] = result.substring(indexHolder, indexHolder + 64);
                indexHolder += 64;
            }
            pk_RA = result.substring(indexHolder, indexHolder + 128);
            indexHolder += 128;
            h1 = result.substring(indexHolder, indexHolder + 130);
            indexHolder += 130;
            h2 = result.substring(indexHolder, indexHolder + 130);
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
                Toast.makeText(QRActivity.this, "Camera Permission required!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    public void showConfirmationSnackBar() {
        Snackbar.make(qr_layout, "Confirmed !", Snackbar.LENGTH_INDEFINITE)
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
