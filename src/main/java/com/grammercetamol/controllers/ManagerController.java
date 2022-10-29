package com.grammercetamol.controllers;

import com.grammercetamol.models.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
* In this class, handles our manager or administrator api or moderator
* */
@RestController("manager/api")
public class ManagerController {

    @Autowired
    AppUserRepository appUserRepository;

    // This path handles finding all users
    @GetMapping("/getAll")
    public List<?> getAll(){
        return appUserRepository.findAll();
    }
}
