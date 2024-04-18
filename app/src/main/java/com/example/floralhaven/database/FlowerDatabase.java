package com.example.floralhaven.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.floralhaven.database.entities.OrderHistory;
import com.example.floralhaven.MainActivity;
import com.example.floralhaven.database.entities.User;
import com.example.floralhaven.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {OrderHistory.class, User.class}, version = 1, exportSchema = false)
public abstract class FlowerDatabase extends RoomDatabase {


    public static final String USER_TABLE = "usertable";
    private static final String DATABASE_NAME = "FlowerDatabase";
    public static final String ORDER_HISTORY_TABLE = "orderHistoryTable";

    private static volatile FlowerDatabase INSTANCE;
    private static  final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FlowerDatabase getDatabase(final Context context){
        if(INSTANCE == null)
        {
            synchronized (FlowerDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FlowerDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDao();
           //     dao.deleteAll();
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });
        }
    };

    public abstract FlowerDAO flowerDAO();

    public abstract UserDAO userDao();
}
