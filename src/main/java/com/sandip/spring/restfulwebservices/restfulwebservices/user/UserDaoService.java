package com.sandip.spring.restfulwebservices.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int idCounter = 1003;

    static {
        users.add(new User(1001, "Sandip Singh", LocalDate.parse("1995-10-11")));
        users.add(new User(1002, "Rajib Singh", LocalDate.parse("1997-10-24")));
        users.add(new User(1003, "Tony Singh", LocalDate.parse("1995-01-11")));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == 0) {
            user.setId(++idCounter);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
