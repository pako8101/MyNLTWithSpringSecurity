package bg.softuni.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
@Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(
                mappingContext -> LocalDate.parse(mappingContext.getSource(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                String.class,
                LocalDate.class
        );
        return modelMapper;
    }
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
}
}
