package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fpt.hungnm.assigmentfinal.Dal.MyDbContext;

import java.util.Date;

public class Home extends AppCompatActivity {

    private static final String TAG ="Hungnm";

    private MyDbContext dbContext;
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
            bindingData();
        }catch (Exception ex){
            Log.e(TAG, "Home - onCreate - " + ex.getMessage());
        }
    }

    private void bindingData() {
        try{
            dbContext = new MyDbContext(this);
            Date currentDate = new Date();
            int currentMonth = currentDate.getMonth() + 1;
            String spClicked = "Tháng " + String.valueOf(currentMonth);
            spMonth.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.month)));
            spMonth.setSelection(currentMonth);
            tvBalance.setText(String.valueOf(dbContext.getBalance()));
        }catch (Exception ex){
            Log.e(TAG, "Home - bindingData - " + ex.getMessage());
        }
    }

    private void bindingAction() {
        try{
            btnGoIncome.setOnClickListener(this::goToIncome);
            btnGoExpense.setOnClickListener(this::goToExpense);
        }catch (Exception ex){
            Log.e(TAG, "Home - bindingAction - " + ex.getMessage());
        }
    }

    private void goToExpense(View view) {
        try{
            Intent i = new Intent(this,Expense_Add.class);
            startActivity(i);
        }catch (Exception ex){
            Log.e(TAG, "Home - goToExpense - " + ex.getMessage());
        }
    }

    private void goToIncome(View view) {
        try{
            Intent i = new Intent(this, InCome_Add.class);
            startActivity(i);
        }catch (Exception ex){
            Log.e(TAG, "Home - goToIncome - " + ex.getMessage());
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
            tvBalance = findViewById(R.id.tv_home_balance);
        }catch (Exception ex){
            Log.e(TAG, "Home - bindingView - " + ex.getMessage());
        }
    }
}