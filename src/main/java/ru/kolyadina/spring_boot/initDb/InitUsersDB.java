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
        Role user = new Role("USER");
        roleService.addRole(user);
        Role admin = new Role("ADMIN");
        roleService.addRole(admin);

        User user1 = new User("user", "user", (byte) 20, "user@yandex.com", "USER");
        user1.setRoles(Set.of(user));

        Set<Role> roleAdmin = new HashSet<>(roleService.getAllRole());
        User user2 = new User("admin", "admin", (byte) 25, "admin@mail.ru","ADMIN");
        user2.setRoles(roleAdmin);

        userService.addUser(user1);
        userService.addUser(user2);
    }
}
