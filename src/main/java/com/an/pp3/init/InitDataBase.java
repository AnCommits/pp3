package com.an.pp3.init;

import com.an.pp3.constant.Gender;
import com.an.pp3.model.User;
import com.an.pp3.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitDataBase {

    private final UserService userService;

    public InitDataBase(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initUsers() {
        List<User> users = getInitialUsersList();
        userService.removeAllUsers();
        userService.saveUsers(users);
    }

    private List<User> getInitialUsersList() {
        List<User> users = new ArrayList<>();

        users.add(new User("Альберт", "Эйнштейн", Gender.MALE, null,
                new GregorianCalendar(1879, Calendar.MARCH, 14)));

        users.add(new User("Мария", "Склодовская-Кюри", Gender.FEMALE, null,
                new GregorianCalendar(1867, Calendar.NOVEMBER, 7)));

        users.add(new User("Анонимный", null, Gender.UNKNOWN, null, null));

        users.add(new User("Спамер", "Банковский", Gender.FEMALE, "+74996491850", null));

        users.add(new User("Менеджер", "Банковский", Gender.FEMALE, "+74996491850, доб.123", null));

        return users;
    }
}
