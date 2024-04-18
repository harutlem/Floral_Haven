package com.example.floralhaven.database;

import android.app.Application;
import android.util.Log;

import com.example.floralhaven.MainActivity;
import com.example.floralhaven.database.entities.OrderHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import kotlinx.coroutines.flow.Flow;

public class FlowerRepository {
    private FlowerDAO flowerDAO;
    private ArrayList<OrderHistory> allLogs;
    public FlowerRepository(Application application)
    {
        FlowerDatabase db = FlowerDatabase.getDatabase(application);
        this.flowerDAO = db.flowerDAO();
        this.allLogs = (ArrayList<OrderHistory>) this.flowerDAO.getAllRecords();
    }

    public ArrayList<OrderHistory> getAllLogs(){

        Future<ArrayList<OrderHistory>> future = FlowerDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<OrderHistory>>() {
                    @Override
                    public ArrayList<OrderHistory> call() throws Exception {
                        return (ArrayList<OrderHistory>) flowerDAO.getAllRecords();
                    }
                }
        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.i(MainActivity.TAG, "Problem when getting all OrderHistory in the repository");
        }
        return null;

    }

    public void insertOrderHistory(OrderHistory orderHistory){
        FlowerDatabase.databaseWriteExecutor.execute(()->{
            flowerDAO.insert(orderHistory);
        });
    }
}
