package com.tousime_alternative.service;


import com.tousime_alternative.dto.UpdatePasswordDto;
import com.tousime_alternative.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto update(UserDto dto);

    Optional<UserDto> findById(Long id);

    List<UserDto> findAll();

    void delete(Long id);

    UserDto findByEmail(String email);

    UserDto updatePassword(UpdatePasswordDto dto);

}
