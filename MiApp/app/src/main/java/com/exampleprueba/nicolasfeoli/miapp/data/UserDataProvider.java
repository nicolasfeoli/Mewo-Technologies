package com.exampleprueba.nicolasfeoli.miapp.data;

import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {
    private static UserDataProvider singletonInstance = null;
    private List<User> users;

    private UserDataProvider() {
        User testUser = new User("12","Orlando", "Hidalgo", "orlan@estudianteC.cr","");
        users = new ArrayList<User>();
        users.add(testUser);
    }

    public static UserDataProvider getSingletonInstance() {
        if (singletonInstance == null) {
            synchronized (UserDataProvider.class) {
                singletonInstance = new UserDataProvider();
            }
        }
        return singletonInstance;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserWithID(String userId){
        for(User user: users){
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }
}
