package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Expense_Add extends AppCompatActivity {
    private static final String TAG = "Hungnm";
    private Button btnSave;
    private EditText edtExpenseMoney;
    private EditText edtExpenseDes;

    private Spinner spinnerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_expense_add);
            bindingView();
            //đổ data
            bindingAction();
        }catch (Exception ex){
            Log.e(TAG, "Expense_Add - onCreate - " + ex.getMessage());
        }
    }

    private void bindingAction() {
        try{
            btnSave.setOnClickListener(this::onSaveClick);

        }catch (Exception ex){
            Log.e(TAG, "Expense_Add - bindingAction - " + ex.getMessage());
        }
    }

    private void onSaveClick(View view) {
        try{

        }catch (Exception ex){
            Log.e(TAG, "Expense_Add - OnSaveClick - " + ex.getMessage());
        }
    }

    private void bindingView() {
        try{
            btnSave = findViewById(R.id.btn_expense_submit);
            edtExpenseMoney = findViewById(R.id.tv_expense_money);
            edtExpenseDes = findViewById(R.id.tv_expense_description);
            spinnerCategory = findViewById(R.id.spin_category);
        }catch (Exception ex){
            Log.e(TAG, "Expense_Add - bindingView - " + ex.getMessage());
        }
    }
}