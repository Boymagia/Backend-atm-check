package atm.check.atmapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Leandro25_";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Senha original: " + rawPassword);
        System.out.println("Senha criptografada (hash): " + encodedPassword);
    }
}