package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class InCome_Add extends AppCompatActivity {
    private static final String TAG ="Hungnm";
    private EditText edtMoney;
    private Button btnSave;
    private Spinner spCategory;
    private EditText edtDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_in_come_add);
            bindingView();
            bindingAction();
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - onCreate - " + ex.getMessage());
        }
    }

    private void bindingAction() {
        try{
            btnSave.setOnClickListener(this::onSaveClick);
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - bindingAction - " + ex.getMessage());
        }
    }

    private void onSaveClick(View view) {
    }

    private void bindingView() {
        try{
            edtMoney = findViewById(R.id.tv_income_money);
            btnSave = findViewById(R.id.btn_income_submit);
            spCategory = findViewById(R.id.spin_income_add_category);
            edtDescription = findViewById(R.id.tv_income_description);
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - bindingView - " + ex.getMessage());
        }
    }
}