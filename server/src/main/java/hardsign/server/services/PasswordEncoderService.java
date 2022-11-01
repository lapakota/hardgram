package hardsign.server.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderService {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public BCryptPasswordEncoder getEncoder() {
        return encoder;
    }
}
