package com.wfiis.CalculatorCO2.user.metadata;

import com.wfiis.CalculatorCO2.user.exception.UserNotFoundException;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import com.wfiis.CalculatorCO2.user.metadata.repository.UserRepository;
import com.wfiis.CalculatorCO2.user.model.UserRegisterModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserMetadataService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserAssembler userAssembler;

    public User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findUsersBy(String searchValue) {
        return userRepository.findUsersByNameOrLastNameOrEmail(searchValue, searchValue, searchValue);
    }

    public User saveUser(UserRegisterModel userRegisterModel) {
        final User user = userAssembler.convertRegisterToEntity(userRegisterModel);
        encodeUserPassword(user);
        return userRepository.save(user);
    }

    private void encodeUserPassword(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
