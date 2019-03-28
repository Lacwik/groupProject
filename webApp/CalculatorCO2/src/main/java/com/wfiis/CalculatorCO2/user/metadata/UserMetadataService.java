package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.user.exception.UserNotFoundException;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.metadata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMetadataService {
    private final UserRepository userRepository;

    public User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
