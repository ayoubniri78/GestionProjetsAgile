package com.ensa_agadir.agile_app.user.controllers.user_controller;
import com.ensa_agadir.agile_app.user.dtos.request.UserCreateRequestDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public UserResponseDTO createUser(@RequestBody UserCreateRequestDTO dto) {
        return userService.creeUser(dto);
    }
}
