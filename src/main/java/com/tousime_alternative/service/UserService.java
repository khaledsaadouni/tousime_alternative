package com.tousime_alternative.service;


import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.dto.UpdatePasswordDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto update(UserDto dto);

    UserDto save(UserDto dto);

    Optional<UserDto> findById(Long id);

    List<UserDto> findAll();

    void delete(Long id);

    UserDto findByEmail(String email);

    UserDto updatePassword(UpdatePasswordDto dto);

}
