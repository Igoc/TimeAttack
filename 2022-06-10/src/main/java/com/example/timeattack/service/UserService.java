package com.example.timeattack.service;

import com.example.timeattack.domain.User;
import com.example.timeattack.dto.UserDto;
import com.example.timeattack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String join(UserDto userDto) {
        userRepository.save(new User(userDto));

        return userDto.getId();
    }

    public Optional<User> findUser(String id) {
        return userRepository.findById(id);
    }

    public List<User> recommendUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));

        return userRepository.findAllByAgeAndGenderNotOrderByCreatedDateAsc(user.getAge(), user.getGender());
    }

    @Transactional
    public String updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID"));

        user.update(userDto);

        return user.getId();
    }

    @Transactional
    public String deleteUser(String id) {
        userRepository.deleteById(id);

        return id;
    }
}
