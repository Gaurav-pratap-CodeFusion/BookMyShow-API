package com.gpcf.BookMyShow.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookMyShowOpenAPI() {

        Info info = new Info()
                .title("BookMyShow Backend API")
                .description(
                        """
                        BookMyShow-like movie ticket booking system.
                        
                        Features:
                        • Movie management
                        • Theater & screen management
                        • Show scheduling
                        • Seat availability
                        • Booking & cancellation
                        • Payment handling
                        """
                )
                .version("1.0.0")
                .contact(new Contact()
                        .name("Gaurav Pratap")
                        .email("gauravpratap0102@gmail.com")
                        .url("https://github.com/Gaurav-pratap-CodeFusion"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));

        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Development Server");

        Server prodServer = new Server()
                .url("https://api.bookmyshow.com")
                .description("Production Server");



        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer, prodServer));

    }
}
