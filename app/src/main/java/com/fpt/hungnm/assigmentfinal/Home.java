package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private static final String TAG ="Hungnm";
    private ImageView btnHome;
    private ImageView btnTransaction;
    private ImageView btnBudget;
    private ImageView btnAccount;
    private LinearLayout btnGoIncome;
    private LinearLayout btnGoExpense;
    private TextView tvBalance;
    private Spinner spMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            bindingView();
            bindingAction();
        }catch (Exception ex){
            Log.e(TAG, "Home - onCreate - " + ex.getMessage());
        }
    }

    private void bindingAction() {
        try{

        }catch (Exception ex){
            Log.e(TAG, "Home - bindingAction - " + ex.getMessage());
        }
    }

    private void bindingView() {
        try{
            btnAccount = findViewById(R.id.btnAccount);
            btnHome = findViewById(R.id.btnHome);
            btnTransaction = findViewById(R.id.btnTransaction);
            btnBudget = findViewById(R.id.btnBudget);
            btnGoIncome = findViewById(R.id.ll_go_income);
            btnGoExpense = findViewById(R.id.ll_go_expense);
            spMonth = findViewById(R.id.sp_home_month);
            tvBalance = findViewById(R.id.tv_AccountBalance);
        }catch (Exception ex){
            Log.e(TAG, "Home - bindingView - " + ex.getMessage());
        }
    }
}