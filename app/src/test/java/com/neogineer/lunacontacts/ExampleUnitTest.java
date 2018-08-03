package com.neogineer.lunacontacts;

import com.neogineer.lunacontacts.db.User;
import com.neogineer.lunacontacts.db.UserDao;
import com.neogineer.lunacontacts.db.UserRoomDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {
    @Test
    public void createUserInDb(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserRoomDatabase db = UserRoomDatabase.getDatabase(ShadowApplication.getInstance().getApplicationContext());
                UserDao dao = db.userDao();
                List<User> users = new LinkedList<>();
                User u = new User();
                u.firstName = "Achraf";
                users.add(u);
                dao.insertAll(users);
            }
        }).start();


    }
}