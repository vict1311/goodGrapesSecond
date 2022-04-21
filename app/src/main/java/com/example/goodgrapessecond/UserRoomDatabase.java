package com.example.goodgrapessecond;

import android.content.Context;
import androidx.annotation.NonNull;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    // we define our DAO and create an abstract method for getting that DAO
    public abstract UserDAO userDao();

    private static final String USER_DB_NAME = "user_db";
    private static volatile UserRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // method for getting the database
    static UserRoomDatabase getDatabase(final Context context) {
        // we use the singleton pattern here to ensure we only ever have ONE database
        // so if the current database instance we use synchronized to ensure there can only be one access to the database
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                // if there is still no current database instance THEN we build it!
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class,USER_DB_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        // return the instance of the database
        return INSTANCE;
    }


// method that handles room callbacks, so that we can create the databse
private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        // we prepopulate our database!
        databaseWriteExecutor.execute(() -> {
            UserDAO dao = INSTANCE.userDao();
            //create new User object use the DAO method insertUser to add it to the database
            User victor = new User("Victor Jensen", "vict1311", "123vict456");
            dao.insertUser(victor);
            User dummy = new User("Dummy Dunder", "dummy123", "dummyPassword");
            dao.insertUser(dummy);
        });
    }
};

}