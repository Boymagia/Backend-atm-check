import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {

    public static void main(String[] args) {
        String senhaEmTextoPuro = "Leandro25_"; // <--- COLOQUE A SENHA QUE VOCÊ QUER CRIPTOGRAFAR AQUI

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashGerado = passwordEncoder.encode(senhaEmTextoPuro);

        System.out.println("--------------------------------------------------");
        System.out.println("SENHA QUE VOCÊ DIGITOU: " + senhaEmTextoPuro);
        System.out.println("COPIE ESTE HASH PARA USAR NO SEU BANCO DE DADOS: " + hashGerado);
        System.out.println("--------------------------------------------------");
    }
}
