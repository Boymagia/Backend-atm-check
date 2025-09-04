/*package atm.check.atmapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Esta classe de configuração habilita o CORS para toda a aplicação.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       
        registry.addMapping("/**")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
                .allowedHeaders("*"); 
    }
}*/
package atm.check.atmapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Esta classe de configuração habilita o CORS para um site específico.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite requisições de uma origem específica (o site do seu amigo)
        // para todos os endpoints da sua API.
        registry.addMapping("/**")
        .allowedOrigins("*")
                /* .allowedOrigins("https://atm-check.netlify.app") */// Altera o "*" para o domínio específico
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos necessários
                .allowedHeaders("*"); // Permite todos os headers
    }
}