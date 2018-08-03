package com.neogineer.lunacontacts.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1 )
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static UserRoomDatabase sUserRoomDatabase;

    public static UserRoomDatabase getDatabase(final Context context) {
        if(sUserRoomDatabase == null)
            synchronized (UserRoomDatabase.class) {
                if(sUserRoomDatabase == null){
                    sUserRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_db")
                            .build();
                }
            }

        return sUserRoomDatabase;
    }
}
