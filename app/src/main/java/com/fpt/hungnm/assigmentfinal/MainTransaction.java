package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.fpt.hungnm.assigmentfinal.Adapter.TransitionSearchAdapter;
import com.fpt.hungnm.assigmentfinal.Dal.MyDbContext;
import com.fpt.hungnm.assigmentfinal.Model.Transaction;

import java.util.List;

public class MainTransaction extends AppCompatActivity {
    private static final String TAG ="HungnmError";
    private RecyclerView rcvTransaction;
    private TransitionSearchAdapter transactionAdapter;

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
        }catch (Exception ex){
            Log.e(TAG, "MainTransaction - onCreate - " + ex.getMessage());
        }
    }

    private void bindingView() {
        try{
            rcvTransaction = findViewById(R.id.rcv_main_transaction);
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
        }
        return super.onCreateOptionsMenu(menu);
    }
}