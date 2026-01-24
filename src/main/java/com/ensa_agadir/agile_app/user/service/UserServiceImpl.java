package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.user.dtos.UtilisateurDTO;
import com.ensa_agadir.agile_app.user.dtos.request.*;
import com.ensa_agadir.agile_app.user.dtos.response.AuthResponseDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.exception.EmailAlreadyExistsException;
import com.ensa_agadir.agile_app.exception.InvalidCredentialsException;
import com.ensa_agadir.agile_app.exception.UserNotFoundException;
import com.ensa_agadir.agile_app.user.mapper.UserMapper;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import com.ensa_agadir.agile_app.user.repository.UserRepo;
import com.ensa_agadir.agile_app.user.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;



    @Override
    @Transactional
    public UserResponseDTO creeUser(UserCreateRequestDTO dto) {
        userRepo.findByEmail(dto.email())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException("Un utilisateur avec cet email existe déjà : " + dto.email());
                });

        Utilisateur user = userMapper.toEntity(dto);
        user.setMotDePasse(PasswordUtil.hashPassword(dto.motDePasse()));
        Utilisateur userSaved = userRepo.save(user);
        return userMapper.toResponseDTO(userSaved);
    }

    @Override
    public AuthResponseDTO authentifier(String email, String motDePasse) {
        Utilisateur utilisateur = userRepo.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Email ou mot de passe incorrect"));

        boolean passwordValid = PasswordUtil.verifyPassword(motDePasse, utilisateur.getMotDePasse());
        
        Optional.of(passwordValid)
                .filter(Boolean::booleanValue)
                .orElseThrow(() -> new InvalidCredentialsException("Email ou mot de passe incorrect"));

        String token = generateToken(utilisateur);
        UserResponseDTO userResponse = userMapper.toResponseDTO(utilisateur);
        
        return new AuthResponseDTO(token, userResponse);
    }

    @Override
    public void deconnecter(Integer userId) {

        userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));
    }

    @Override
    @Transactional
    public UtilisateurDTO mettreAJourProfil(Integer userId, UserProfileUpdateDTO profileDTO) {
        Utilisateur utilisateur = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        Optional.ofNullable(profileDTO.email())
                .filter(email -> !email.equals(utilisateur.getEmail()))
                .ifPresent(email -> {
                    userRepo.findByEmail(email)
                            .ifPresent(existingUser -> {
                                throw new EmailAlreadyExistsException("Cet email est déjà utilisé : " + email);
                            });
                    utilisateur.setEmail(email);
                });

        Optional.ofNullable(profileDTO.nom()).ifPresent(utilisateur::setNom);
        Optional.ofNullable(profileDTO.prenom()).ifPresent(utilisateur::setPrenom);

        Utilisateur updatedUser = userRepo.save(utilisateur);
        return UtilisateurDTO.from(userMapper.toResponseDTO(updatedUser));
    }



    @Override
    public Optional<UtilisateurDTO> getUtilisateurById(Integer id) {
        return userRepo.findById(id)
                .map(userMapper::toResponseDTO)
                .map(UtilisateurDTO::from);
    }



    @Override
    public boolean emailExiste(String email) {
        return userRepo.existsByEmail(email);
    }



    @Override
    public List<String> getRolesDansProjet(Integer userId, Integer projetId) {
        return userRepo.findById(userId)
                .map(Utilisateur::getMembreProjets)
                .orElse(List.of())
                .stream()
                .filter(mp -> mp.getProjet().getId() == projetId)
                .map(mp -> mp.getRoleDansProjet().name())
                .collect(Collectors.toList());
    }





    @Override
    @Transactional
    public void supprimerCompte(Integer userId) {
        userRepo.findById(userId)
                .ifPresentOrElse(
                        userRepo::delete,
                        () -> {
                            throw new UserNotFoundException("Utilisateur non trouvé avec l'ID : " + userId);
                        }
                );
    }




    private String generateToken(Utilisateur utilisateur) {
        return UUID.randomUUID().toString() + "-" + utilisateur.getId();
    }
}
