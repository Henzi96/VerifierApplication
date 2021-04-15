package com.example.nfcreader;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    public ArrayList<HistoryItem> historyLogsList;

    private RecyclerView historyRecyclerView;
    //Bridge between data and RecyclerView -> Provide as many items as we need
    private HistoryAdapter historyRecyclerViewAdapter;
    //Responsible for align item layout to recyclerView
    private RecyclerView.LayoutManager historyRecyclerViewLayoutManager;
    Button btnclearAllLogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        createHistoryList();
        buildRecyclerView();
    }


    public void buildRecyclerView() {
        historyRecyclerView = findViewById(R.id.recyclerView_history);
        historyRecyclerView.setHasFixedSize(true);
        historyRecyclerViewLayoutManager = new LinearLayoutManager(this);
        historyRecyclerViewAdapter = new HistoryAdapter(historyLogsList);
        historyRecyclerView.setLayoutManager(historyRecyclerViewLayoutManager);
        historyRecyclerView.setAdapter(historyRecyclerViewAdapter);
        btnclearAllLogs = findViewById(R.id.btn_clear_logs);
        btnclearAllLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int totalNumberOfLogs = sharedPreferences.getInt(Constants.SystemParameters.NUMBER_OF_LOGS, 0);
                for (int i = 1; i <= totalNumberOfLogs; i++) {
                    editor.remove(Constants.SystemParameters.LOG_STATE + i);
                    editor.remove(Constants.SystemParameters.LOG_DATE + i);
                }
                editor.putInt(Constants.SystemParameters.NUMBER_OF_LOGS, 0);
                editor.commit();
            }
        });
    }

    public void createHistoryList() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("VerifierData", MODE_PRIVATE);
        historyLogsList = new ArrayList<>();
        for (int i = 1; i <= sharedPreferences.getInt(Constants.SystemParameters.NUMBER_OF_LOGS, 0); i++) {
            if (sharedPreferences.getString(Constants.SystemParameters.LOG_STATE + i, "").equals("Verified")) {
                historyLogsList.add(new HistoryItem(R.drawable.ic_baseline_verified_user_24, sharedPreferences.getString(Constants.SystemParameters.LOG_DATE + i, ""), "Verified"));
            } else {
                historyLogsList.add(new HistoryItem(R.drawable.ic_baseline_not_interested_24, sharedPreferences.getString(Constants.SystemParameters.LOG_DATE + i, ""), "Not Verified"));
            }
        }
    }

}