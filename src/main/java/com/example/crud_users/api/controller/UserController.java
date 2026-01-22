package com.example.crud_users.api.controller;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.ResponseUpdateDTO;
import com.example.crud_users.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody RequestDTO request){
        ResponseDTO response = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findById(@RequestParam UUID uuid){
        ResponseDTO response = userService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam UUID uuid){
        userService.deleteUserById(uuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ResponseUpdateDTO> updateUserByEmail(@RequestParam UUID uuid,
                                                               @RequestBody RequestDTO update){
        ResponseUpdateDTO response = userService.updateUserById(uuid, update);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
