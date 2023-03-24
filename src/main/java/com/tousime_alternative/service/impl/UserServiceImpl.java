package com.tousime_alternative.service.impl;

import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.dto.auth.UpdatePasswordDto;
import com.tousime_alternative.model.User;
import com.tousime_alternative.repository.UserRepository;
import com.tousime_alternative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean userAlreadyExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public UserDto update(UserDto dto) {
        return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
    }

    @Override
    public UserDto save(UserDto dto) {
        return null;
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .stream().findFirst();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updatePassword(UpdatePasswordDto dto) {
        return null;
    }
}
