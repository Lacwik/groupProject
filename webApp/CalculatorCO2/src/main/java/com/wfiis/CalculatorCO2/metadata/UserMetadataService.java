package com.wfiis.CalculatorCO2.metadata;

import com.wfiis.CalculatorCO2.metadata.entity.User;
import com.wfiis.CalculatorCO2.metadata.repository.UserRepository;
import com.wfiis.CalculatorCO2.security.exception.UserNotFoundException;
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


}
