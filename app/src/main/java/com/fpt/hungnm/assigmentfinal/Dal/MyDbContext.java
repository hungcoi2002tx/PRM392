package com.fpt.hungnm.assigmentfinal.Dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import com.fpt.hungnm.assigmentfinal.Model.Budget;
import com.fpt.hungnm.assigmentfinal.Model.Category;
import com.fpt.hungnm.assigmentfinal.Model.Transaction;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDbContext extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MoneyManage.db";
    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_BUDGET = "budgets";
    private static int DATABASE_VERSION = 1;

    public MyDbContext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateTransition = "CREATE TABLE " +
                TABLE_TRANSACTIONS +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Title TEXT,"+
                "CategoryId TEXT,"+
                "Price TEXT,"+
                "IsIncome TEXT,"+
                "CreateDate DATETIME)";
        db.execSQL(sqlCreateTransition);

        String sqlCreateCategory = "CREATE TABLE " +
                TABLE_CATEGORY +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Title TEXT,"+
                "IsIncome TEXT,"+
                "CreateDate DATETIME)";
        db.execSQL(sqlCreateCategory);

        String sqlCreateBudget = "CREATE TABLE "+
                TABLE_BUDGET+
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Title TEXT,"+
                "TypeOfBudget TEXT,"+
                "Time TEXT,"+
                "CategoryId TEXT,"+
                "CreateDate DATETIME)";
        db.execSQL(sqlCreateBudget);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<Transaction> getAllTransition(){
        List<Transaction> list = new ArrayList<>();
        try{
            SQLiteDatabase sql = getReadableDatabase();
            String orderBy = "CreateDate DESC";
            Cursor cs = sql.query(TABLE_TRANSACTIONS,null,null,null,null,null,orderBy);
            while (cs != null && cs.moveToNext()){
                int id = cs.getInt(0);
                String title = cs.getString(1);
                String categoryId = cs.getString(2);
                String price = cs.getString(3);
                String isIncome = cs.getString(4);
                long createDateMillis = cs.getLong(5);
                Date createDate = new Date(createDateMillis);
                list.add(new Transaction(id,title,categoryId,price,isIncome,createDate));
            }
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - getAllTransition - " + ex.getMessage());
        }
        return list;
    }

    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        try{
            SQLiteDatabase sql = getReadableDatabase();
            String orderBy = "CreateDate DESC";
            Cursor cs = sql.query(TABLE_CATEGORY,null,null,null,null,null,orderBy);
            while (cs != null && cs.moveToNext()){
                int id = cs.getInt(0);
                String title = cs.getString(1);
                String isIncome = cs.getString(2);

                long createDateMillis = cs.getLong(3);
                Date createDate = new Date(createDateMillis);
                list.add(new Category(id,title,isIncome,createDate));
            }
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - getAllCategory - " + ex.getMessage());
        }
        return list;
    }

    public List<Budget> getAllBudget(){
        List<Budget> list = new ArrayList<>();
        try{
            SQLiteDatabase sql = getReadableDatabase();
            String orderBy = "CreateDate DESC";
            Cursor cs = sql.query(TABLE_BUDGET,null,null,null,null,null,orderBy);
            while (cs != null && cs.moveToNext()){
                int id = cs.getInt(0);
                String title = cs.getString(1);
                String typeOfBudget = cs.getString(2);
                String time = cs.getString(3);
                String categoryId = cs.getString(4);
                long createDateMillis = cs.getLong(5);
                Date createDate = new Date(createDateMillis);
                list.add(new Budget(id,title,typeOfBudget,time,categoryId,createDate));
            }
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - getAllBudget - " + ex.getMessage());
        }
        return list;
    }

    public long addTransaction(Transaction transaction){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", transaction.getTitle());
            values.put("categoryId", transaction.getCategory());
            values.put("price", transaction.getPrice());
            values.put("isIncome", transaction.getIsIncome());


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(transaction.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            result = sqLiteDatabase.insert(TABLE_TRANSACTIONS,null,values);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - addTransactions - " + ex.getMessage());
        }
        return  result;
    }

    public long updateTransaction(Transaction transaction){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", transaction.getTitle());
            values.put("categoryId", transaction.getCategory());
            values.put("price", transaction.getPrice());
            values.put("isIncome", transaction.getIsIncome());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(transaction.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            String whereClause = "id = ?";
            String[] whereArgs = {Integer.toString(transaction.getId())};
            result = sqLiteDatabase.update(TABLE_TRANSACTIONS,values,whereClause,whereArgs);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - updateTransaction - " + ex.getMessage());
        }
        return result;
    }

    public boolean deleteTransaction(int transactionId) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(transactionId)};
            int rowsDeleted = db.delete(TABLE_TRANSACTIONS, whereClause, whereArgs);
            return rowsDeleted > 0;
        } catch (Exception ex) {
            Log.e("HungnmError", "MyDbContext - deleteTransaction - " + ex.getMessage());
            return false;
        }
    }

    public long addCategory(Category category){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", category.getTitle());
            values.put("isIncome", category.getIsIncome());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(category.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            result = sqLiteDatabase.insert(TABLE_CATEGORY,null,values);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - addCategory - " + ex.getMessage());
        }
        return  result;
    }

    public long updateCategory(Category item){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", item.getTitle());
            values.put("isIncome", item.getIsIncome());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(item.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            String whereClause = "id = ?";
            String[] whereArgs = {Integer.toString(item.getId())};
            result = sqLiteDatabase.update(TABLE_CATEGORY,values,whereClause,whereArgs);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - updateCategory - " + ex.getMessage());
        }
        return result;
    }

    public boolean deleteCategory(int categoryId) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(categoryId)};
            int rowsDeleted = db.delete(TABLE_CATEGORY, whereClause, whereArgs);
            return rowsDeleted > 0;
        } catch (Exception ex) {
            Log.e("HungnmError", "MyDbContext - deleteCategory - " + ex.getMessage());
            return false;
        }
    }

    public long addBudget(Budget budget){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", budget.getTitle());
            values.put("typeOfBudget", budget.getTypeOfBudget());
            values.put("time", budget.getTime());
            values.put("categoryId", budget.getCategotyId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(budget.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            result = sqLiteDatabase.insert(TABLE_BUDGET,null,values);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - addCategory - " + ex.getMessage());
        }
        return  result;
    }
    public long updateBudget(Budget item){
        long result = -1;
        try{
            ContentValues values = new ContentValues();
            values.put("title", item.getTitle());
            values.put("typeofbudget", item.getTypeOfBudget());
            values.put("time", item.getTime());
            values.put("categoryId", item.getCategotyId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDateStr = dateFormat.format(item.getCreateDate());
            values.put("createDate", createDateStr);
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            String whereClause = "id = ?";
            String[] whereArgs = {Integer.toString(item.getId())};
            result = sqLiteDatabase.update(TABLE_BUDGET,values,whereClause,whereArgs);
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - updateCategory - " + ex.getMessage());
        }
        return result;
    }

    public boolean deleteBudget(int budgetId) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(budgetId)};
            int rowsDeleted = db.delete(TABLE_BUDGET, whereClause, whereArgs);
            return rowsDeleted > 0;
        } catch (Exception ex) {
            Log.e("HungnmError", "MyDbContext - deleteBudget - " + ex.getMessage());
            return false;
        }
    }

    public List<Transaction> getTransactionByMonth(int month){
        List<Transaction> list = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        try{
            String selection = "strftime('%m', CreateDate) = ? AND strftime('%Y', CreateDate) = ?";
            String[] selectionArgs = {String.format("%02d", month), String.valueOf(currentYear)};
            String orderBy = "CreateDate DESC";
            SQLiteDatabase st = getReadableDatabase();
            Cursor cs = st.query(TABLE_TRANSACTIONS,null,selection,selectionArgs,null,null,orderBy);
            while (cs != null && cs.moveToNext()){
                int id = cs.getInt(0);
                String title = cs.getString(1);
                String categoryId = cs.getString(2);
                String price = cs.getString(3);
                String isIncome = cs.getString(4);
                long createDateMillis = cs.getLong(5);
                Date createDate = new Date(createDateMillis);
                list.add(new Transaction(id,title,categoryId,price,isIncome,createDate));
            }
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - getTransactionByDate - " + ex.getMessage());
        }
        return list;
    }

    public List<Transaction> getTransactionByFilter(Date startDate, Date endDate, Boolean isIncomeFilter, String categoryIdFilter){
        List<Transaction> list = new ArrayList<>();
        String type = null;
        if(isIncomeFilter){
            type = "y";
        }else if(!isIncomeFilter){
            type = "N";
        }
        try{
            String selection = null;
            List<String> selectionArgsList = new ArrayList<>();
            if(startDate != null){
                selection = "CreateDate >= ?";
                selectionArgsList.add(String.valueOf(startDate.getTime()));
            }
            if(endDate != null){
                if(selection != null){
                    selection = "CreateDate <= ?";
                    selectionArgsList.add(String.valueOf(endDate.getTime()));
                }else{
                    selection = "AND CreateDate <= ?";
                    selectionArgsList.add(String.valueOf(endDate.getTime()));
                }
            }
            if(type != null){
                if(selection != null){
                    selection = "IsIncome = ?";
                    selectionArgsList.add(type);
                }else{
                    selection = "AND IsIncome = ?";
                    selectionArgsList.add(type);
                }
            }
            if(categoryIdFilter != null){
                if(selection != null){
                    selection = "CategoryId = ?";
                    selectionArgsList.add(categoryIdFilter);
                }else{
                    selection = "AND CategoryId = ?";
                    selectionArgsList.add(categoryIdFilter);
                }
            }
            String[] selectionArgs = new String[selectionArgsList.size()];
            selectionArgs = selectionArgsList.toArray(selectionArgs);

            String orderBy = "CreateDate DESC";
            SQLiteDatabase st = getReadableDatabase();
            Cursor cs = st.query(TABLE_TRANSACTIONS,null,selection,selectionArgs,null,null,orderBy);
            while (cs != null && cs.moveToNext()){
                int id = cs.getInt(0);
                String title = cs.getString(1);
                String categoryId = cs.getString(2);
                String price = cs.getString(3);
                String isIncome = cs.getString(4);
                long createDateMillis = cs.getLong(5);
                Date createDate = new Date(createDateMillis);
                list.add(new Transaction(id,title,categoryId,price,isIncome,createDate));
            }
        }catch (Exception ex){
            Log.e("HungnmError","MyDbContext - getTransactionByFilter - " + ex.getMessage());
        }
        return list;
    }

}
