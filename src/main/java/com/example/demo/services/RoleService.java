package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository rd;
    @Autowired
    public RoleService(RoleRepository rd) {
        this.rd = rd;
    }
    public Role getRoleByName(String name) {
        return rd.getRoleByName(name);
    }
}
