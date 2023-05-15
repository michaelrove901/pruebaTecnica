package credibanco.credibanco.assessment.card.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;

@Configuration
public class BeansConfig {
    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

}

