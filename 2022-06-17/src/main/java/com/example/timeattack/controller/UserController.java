package com.example.timeattack.controller;

import com.example.timeattack.domain.User;
import com.example.timeattack.dto.UserDto;
import com.example.timeattack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.join(userDto);
    }

    @GetMapping("/read/{id}")
    public User getUser(@PathVariable String id) {
        Optional<User> user = userService.findUser(id);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }

    @GetMapping("/recommend/{id}")
    public List<User> recommendUser(@PathVariable String id) {
        return userService.recommendUser(id);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
