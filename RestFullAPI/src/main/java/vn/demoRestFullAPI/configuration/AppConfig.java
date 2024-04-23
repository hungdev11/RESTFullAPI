package vn.demoRestFullAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http:// ten mien duoc truy xuat")
                .allowedMethods("POST,PUT, DELETE, PATCH")
                .allowedHeaders("*"); // * = all
    }
}

//@Configuration
//public class AppConfig1  {
//    @Bean
//    public WebMvcConfigurer conrsFilter() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowCredentials(true)
//                        .allowedOrigins("http:// ten mien duoc truy xuat")
//                        .allowedMethods("POST,PUT, DELETE, PATCH")
//                        .allowedHeaders("*"); // * = all
//            }
//        };
//    }
//}
