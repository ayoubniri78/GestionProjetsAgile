package com.ensa_agadir.agile_app.user.controllers.user_controller;

import com.ensa_agadir.agile_app.user.dtos.UtilisateurDTO;
import com.ensa_agadir.agile_app.user.dtos.request.LoginDTO;
import com.ensa_agadir.agile_app.user.dtos.request.UserCreateRequestDTO;
import com.ensa_agadir.agile_app.user.dtos.request.UserProfileUpdateDTO;
import com.ensa_agadir.agile_app.user.dtos.response.AuthResponseDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("cree-user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody UserCreateRequestDTO dto) {
        return userService.creeUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        AuthResponseDTO response = userService.authentifier(loginDTO.email(), loginDTO.motDePasse());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@PathVariable Integer id) {
        userService.deconnecter(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUserById(@PathVariable Integer id) {
        return userService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/profile")
    public UtilisateurDTO updateProfile(
            @PathVariable Integer id,
            @RequestBody UserProfileUpdateDTO profileDTO) {
        return userService.mettreAJourProfil(id, profileDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        userService.supprimerCompte(id);
    }
}
