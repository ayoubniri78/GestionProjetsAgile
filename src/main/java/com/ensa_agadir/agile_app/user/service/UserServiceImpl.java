package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.user.dtos.request.UserCreateRequestDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;
import com.ensa_agadir.agile_app.user.mapper.UserMapper;
import com.ensa_agadir.agile_app.user.models.Utilisateur;
import com.ensa_agadir.agile_app.user.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserRepo userRepo;

    public UserServiceImpl(UserMapper userMapper, UserRepo userRepo){
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    @Override
    public UserResponseDTO creeUser(UserCreateRequestDTO dto) {
        Utilisateur user = userMapper.toEntity(dto);
        Utilisateur userSaved = userRepo.save(user);
        return userMapper.toResponseDTO(userSaved);
    }
}
