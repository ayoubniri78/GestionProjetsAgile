package com.ensa_agadir.agile_app.user.service;

import com.ensa_agadir.agile_app.user.dtos.request.UserCreateRequestDTO;
import com.ensa_agadir.agile_app.user.dtos.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO creeUser(UserCreateRequestDTO dto);
}
