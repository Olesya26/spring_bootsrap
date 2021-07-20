package ru.kolyadina.spring_boot.initDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kolyadina.spring_boot.model.Role;
import ru.kolyadina.spring_boot.model.User;
import ru.kolyadina.spring_boot.service.RoleService;
import ru.kolyadina.spring_boot.service.UserService;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitUsersDB {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public InitUsersDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void initApiUserData() {
        Role user = new Role("ROLE_USER");
        roleService.addRole(user);
        Role admin = new Role("ROLE_ADMIN");
        roleService.addRole(admin);

        Set<Role> roleUser = new HashSet<>();
        for (Role role : roleService.getAllRole()) {
            if (role.getName().equals("ROLE_USER")) {
                roleUser.add(role);
            }
        }
        User user1 = new User("USER", "$2y$12$ckq8vl6/nGRcBNdOmRSgF.ZH3Dt7/OqoJvJZEXcxcQR32MrN8GiCG", // пароль USER
                "Andrey", "Gorbunov", "andrey@yandex.com");
        user1.setRoles(roleUser);

        Set<Role> roleAdmin = new HashSet<>(roleService.getAllRole());
        User user2 = new User("ADMIN", "$2y$12$9gPYP/2rDjSAzo5BISb1tucVusJ50r/rDMnhnH0Y276kJ4RYkOJIC", // пароль ADMIN
                "Olga", "Gorbunova", "olga@mail.ru");
        user2.setRoles(roleAdmin);

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
