package com.wfiis.CalculatorCO2.user.security;

import com.wfiis.CalculatorCO2.user.metadata.UserAssembler;
import com.wfiis.CalculatorCO2.user.metadata.UserMetadataService;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final UserMetadataService userMetadataService;
    private final UserAssembler assembler;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;

        try {
            user = userMetadataService.findUser(email);
        } catch (RuntimeException e) {
            log.info("Could not find a user by email: {} while trying to log in.", email);
            throw new UsernameNotFoundException("User with email: " + email + " doesn't exist.");
        }

        return assembler.convertAuthenticationToModel(user);
    }
}
