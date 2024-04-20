package com.example.floralhaven.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.floralhaven.MainActivity;
import com.example.floralhaven.database.entities.Flower;
import com.example.floralhaven.database.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FlowerRepository {
    private final FlowerDAO flowerDAO;
    private final UserDAO userDAO;
    private ArrayList<Flower> allLogs;

    private static FlowerRepository repository;
    private FlowerRepository(Application application)
    {
        FlowerDatabase db = FlowerDatabase.getDatabase(application);
        this.flowerDAO = db.flowerDAO();
        this.userDAO = db.userDao();
        this.allLogs = (ArrayList<Flower>) this.flowerDAO.getAllRecords();
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

    public ArrayList<Flower> getAllLogs(){

        Future<ArrayList<Flower>> future = FlowerDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Flower>>() {
                    @Override
                    public ArrayList<Flower> call() throws Exception {
                        return (ArrayList<Flower>) flowerDAO.getAllRecords();
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

    public void insertFlower(Flower flower){
        FlowerDatabase.databaseWriteExecutor.execute(()->{
            flowerDAO.insert(flower);
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
