package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.user.exception.UserNotFoundException;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.metadata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMetadataService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User saveUser(User user) {
        encodeUserPassword(user);
        return userRepository.save(user);
    }

    private void encodeUserPassword(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
