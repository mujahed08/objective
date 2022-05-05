package com.objective.controllers;

import com.objective.payload.response.Page;
import com.objective.payload.response.User;
import com.objective.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<User>> get(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int size) {
        Page<User> page = userService.getPage(pageNo, size);
        return ResponseEntity.ok(page);
    }
}
