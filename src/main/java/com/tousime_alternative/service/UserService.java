package com.tousime_alternative.service;


import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.dto.auth.UpdatePasswordDto;

import java.util.List;

public interface UserService {
    UserDto update(UserDto dto);

    UserDto save(UserDto dto);

    UserDto findById(Integer id);

    List<UserDto> findAll();

    void delete(Integer id);

    UserDto findByEmail(String email);

    UserDto updatePassword(UpdatePasswordDto dto);

}
