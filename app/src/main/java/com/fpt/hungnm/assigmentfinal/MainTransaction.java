package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.fpt.hungnm.assigmentfinal.Adapter.TransitionSearchAdapter;
import com.fpt.hungnm.assigmentfinal.Dal.MyDbContext;
import com.fpt.hungnm.assigmentfinal.Model.Budget;
import com.fpt.hungnm.assigmentfinal.Model.Transaction;

import java.util.List;

public class MainTransaction extends AppCompatActivity {
    private static final String TAG ="HungnmError";
    private static final int REQUEST_CODE =2;
    private RecyclerView rcvTransaction;
    private TransitionSearchAdapter transactionAdapter;

    private ImageView btnHome;
    private ImageView btnBudget;
    private ImageView btnAccount;

    private ImageView btnTransaction;

    private MyDbContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transaction);
        supportInvalidateOptionsMenu();

        bindingView();
        bindingAction();
        setAdapter();
    }

    private void setAdapter() {
        try{
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcvTransaction.setLayoutManager(linearLayoutManager);
            transactionAdapter = new TransitionSearchAdapter(getListTransaction());
            rcvTransaction.setAdapter(transactionAdapter);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            rcvTransaction.addItemDecoration(itemDecoration);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - setAdapter - " + ex.getMessage());
        }
    }

    private List<Transaction> getListTransaction() {
        try{
            List<Transaction> list = dbContext.getAllTransition();
            return list;
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - getListTransaction - " + ex.getMessage());
        }
        return null;
    }

    private void bindingAction() {
        try{
            dbContext= new MyDbContext(this);
            btnHome.setOnClickListener(this::goToHome);
            btnBudget.setOnClickListener(this::goToBudget);
            btnAccount.setOnClickListener(this::goToAccount);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - onCreate - " + ex.getMessage());
        }
    }

    private void goToAccount(View view) {
        try{
            Intent i = new Intent(this,MainTransaction.class);
            startActivityForResult(i,REQUEST_CODE);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - goToAccount - " + ex.getMessage());
        }
    }

    private void goToBudget(View view) {
        try{
            Intent i = new Intent(this, Budget.class);
            startActivityForResult(i,REQUEST_CODE);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - goToBudget - " + ex.getMessage());
        }
    }

    private void goToHome(View view) {
        try{
            Intent i = new Intent(this,Home.class);
            startActivityForResult(i,REQUEST_CODE);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - goToHome - " + ex.getMessage());
        }
    }

    private void bindingView() {
        try{
            rcvTransaction = findViewById(R.id.rcv_main_transaction);
            btnHome = findViewById(R.id.img_transaction_btnHome);
            btnBudget = findViewById(R.id.img_transaction_btnBudget);
            btnAccount = findViewById(R.id.img_transaction_btnAccount);
            btnTransaction = findViewById(R.id.img_transaction_btnTransaction);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - bindingView - " + ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{
            getMenuInflater().inflate(R.menu.menu_transaction,menu);
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - bindingView - " + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnTransaction.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN);
    }
}