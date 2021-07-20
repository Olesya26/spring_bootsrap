package ru.kolyadina.spring_boot.service;

import org.springframework.stereotype.Service;
import ru.kolyadina.spring_boot.model.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getAllRole();
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(long id);
    Role getRoleByName(String name);
}
