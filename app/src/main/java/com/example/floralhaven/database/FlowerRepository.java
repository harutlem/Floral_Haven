package com.example.floralhaven.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.floralhaven.MainActivity;
import com.example.floralhaven.database.entities.OrderHistory;
import com.example.floralhaven.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import kotlinx.coroutines.flow.Flow;

public class FlowerRepository {
    private final FlowerDAO flowerDAO;
    private final UserDAO userDAO;
    private ArrayList<OrderHistory> allLogs;

    private static FlowerRepository repository;
    private FlowerRepository(Application application)
    {
        FlowerDatabase db = FlowerDatabase.getDatabase(application);
        this.flowerDAO = db.flowerDAO();
        this.userDAO = db.userDao();
        this.allLogs = (ArrayList<OrderHistory>) this.flowerDAO.getAllRecords();
    }


    public static FlowerRepository getRepository(Application application){
        if(repository != null)
        {
            return repository;
        }
        Future<FlowerRepository> future = FlowerDatabase.databaseWriteExecutor.submit(
                new Callable<FlowerRepository>() {
                    @Override
                    public FlowerRepository call() throws Exception {
                        return new FlowerRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.i(MainActivity.TAG, "Problem getting FlowerRepository, thread error.");
        }
        return null;

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

    public void insertUser(User...user){
        FlowerDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

}
