package tim2.cataloging.tim2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsRunner implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:8082")
                .allowCredentials(true);
    }
}