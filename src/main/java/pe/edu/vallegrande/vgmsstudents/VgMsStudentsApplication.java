package pe.edu.vallegrande.vgmsstudents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class VgMsStudentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VgMsStudentsApplication.class, args);
    }

}
