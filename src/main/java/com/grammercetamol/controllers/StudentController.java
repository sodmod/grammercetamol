package com.grammercetamol.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/studentinfo")
    public ResponseEntity<?> singleStudentRecord(String email){

        return null;
    }
}
