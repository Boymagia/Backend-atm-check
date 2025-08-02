package atm.check.atmapi.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import atm.check.atmapi.model.User;
import atm.check.atmapi.repository.UserRepository; 
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User login(String email, String senhaEmTextoPuro) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            if (passwordEncoder.matches(senhaEmTextoPuro, user.getSenha())) {
                return user;
            }
        }

        return null;
    }
}