package com.example.crud_users.api.controller;

import com.example.crud_users.api.dto.RequestDTO;
import com.example.crud_users.api.dto.ResponseDTO;
import com.example.crud_users.api.dto.ResponseUpdatedDTO;
import com.example.crud_users.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable("uuid") UUID uuid){
        ResponseDTO response = userService.findById(uuid);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam UUID uuid){
        userService.deleteUserById(uuid);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<ResponseUpdatedDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping
    public ResponseEntity<ResponseUpdatedDTO> updateUserByEmail(@RequestParam UUID uuid,
                                                                @RequestBody RequestDTO update){
        ResponseUpdatedDTO response = userService.updateUserById(uuid, update);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
