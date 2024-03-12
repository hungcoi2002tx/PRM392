package com.fpt.hungnm.assigmentfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.hungnm.assigmentfinal.Dal.MyDbContext;
import com.fpt.hungnm.assigmentfinal.Model.Category;
import com.fpt.hungnm.assigmentfinal.Model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InCome_Add extends AppCompatActivity {
    private static final String TAG ="Hungnm";
    private EditText edtMoney;
    private Button btnSave;
    private Spinner spCategory;
    private EditText edtDescription;

    private MyDbContext myDbContext;
    private TextView tvNoCategories;

    private List<Category> categories;

    private ImageView imgAddCategory;

    private TextView tvError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_in_come_add);
            bindingView();
            bindingAction();
            bindingData();
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - onCreate - " + ex.getMessage());
        }
    }

    private void bindingData() {
        try{
            myDbContext = new MyDbContext(this);
            categories = myDbContext.getAllCategoryByType("INCOME");
            List<String> titleCategories = new ArrayList<>();
            for (Category item :
                    categories) {
                titleCategories.add(item.getTitle());
            }
            if(titleCategories.size() == 0 ){
                tvNoCategories.setText("Chưa tạo danh mục cho thu nhập");
            }else{
                tvNoCategories.setText("");
            }
            spCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,titleCategories));
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - bindingData - " + ex.getMessage());
        }
    }

    private void bindingAction() {
        try{
            btnSave.setOnClickListener(this::onSaveClick);
            imgAddCategory.setOnClickListener(this::onAddCategory);
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - bindingAction - " + ex.getMessage());
        }
    }

    private void onAddCategory(View view) {
    }

    public Category getCategoryByTitle(String title) {
        try{
            for (Category category : categories) {
                if (category.getTitle().equals(title)) {
                    return category;
                }
            }
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - onSaveClick - " + ex.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy Category có title tương ứng
    }
    private void onSaveClick(View view) {
        try{
            if(!checkValid()){
                tvError.setText("Không được để trường nào trống");
            }else{
                Transaction transaction = new Transaction();
                Category category = getCategoryByTitle(spCategory.getSelectedItem().toString());
                transaction.setCategory(String.valueOf(category.getId()));
                transaction.setPrice(String.valueOf(edtMoney.getText()));
                transaction.setTitle(edtDescription.getText().toString());
                transaction.setIsIncome("INCOME");
                Date currentDate = new Date();
                transaction.setCreateDate(currentDate);
                long result = myDbContext.addTransaction(transaction);
                if(result == -1){
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    resetInput();
                }
            }

        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - onSaveClick - " + ex.getMessage());
        }
    }

    private boolean checkValid(){
        try{
            if(edtMoney.getText().equals("") || edtDescription.getText().equals("")){
                return false;
            }
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - checkValid - " + ex.getMessage());
        }
        return true;
    }

    private void resetInput(){
        try{
            edtMoney.setText("");
            edtDescription.setText("");
            tvError.setText("");
            tvNoCategories.setText("");
        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - resetInput - " + ex.getMessage());
        }
    }

    private void bindingView() {
        try{
            edtMoney = findViewById(R.id.tv_income_money);
            btnSave = findViewById(R.id.btn_income_submit);
            spCategory = findViewById(R.id.spin_income_add_category);
            edtDescription = findViewById(R.id.tv_income_description);
            tvError = findViewById(R.id.tv_addincome_error);
            tvNoCategories = findViewById(R.id.tv_income_nocategory);
            imgAddCategory = findViewById(R.id.img_income_add_category);

        }catch (Exception ex){
            Log.e(TAG, "InCome_Add - bindingView - " + ex.getMessage());
        }
    }
}