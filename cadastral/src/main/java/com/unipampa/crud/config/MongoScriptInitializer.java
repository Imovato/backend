package com.unipampa.crud.config;

import com.mongodb.client.MongoClients;
import com.unipampa.crud.enums.UserType;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class MongoScriptInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final Dotenv dotenv = Dotenv.load();

    public MongoScriptInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Verificando se os dados já existem no banco...");

        try (var client = MongoClients.create("mongodb://localhost:27017")) {
            var database = client.getDatabase("cadastral");
            var roleCollection = database.getCollection("role");
            var userCollection = database.getCollection("user");


            long count = roleCollection.countDocuments();

            if (count == 0) {
                System.out.println("Dados não encontrados, criando inicialização...");

                roleCollection.insertMany(List.of(
                        new Document("_id", "65f3c89d-8f45-4732-9021-f84d559d6a12").append("roleName", "ROLE_HOST"),
                        new Document("_id", "65f3c89d-8f45-4732-9021-f84d559d6a13").append("roleName", "ROLE_GUEST"),
                        new Document("_id", "65f3c89d-8f45-4732-9021-f84d559d6a14").append("roleName", "ROLE_ADMINISTRATOR")
                ));

                System.out.println("Dados de inicialização criados com sucesso!");
            } else {
                System.out.println("Dados já existem no banco. Inicialização não necessária.");
            }

            // Inicializar usuários
            long userCount = userCollection.countDocuments();

            if (userCount == 0) {
                System.out.println("Usuários não encontrados, criando inicialização...");

                Document roleAdmin = roleCollection.find(new Document("roleName", "ROLE_ADMINISTRATOR")).first();

                if (roleAdmin == null) {
                    throw new IllegalStateException("Role 'ROLE_ADMINISTRATOR' não encontrada no banco!");
                }

                userCollection.insertMany(List.of(
                        new Document("_id", "admin-1")
                                .append("userName", dotenv.get("ADMIN1_USERNAME"))
                                .append("password", passwordEncoder.encode(dotenv.get("ADMIN1_PASSWORD")))
                                .append("roles", List.of(roleAdmin)),

                        new Document("_id", "admin-2")
                                .append("userName", dotenv.get("ADMIN2_USERNAME"))
                                .append("password", passwordEncoder.encode(dotenv.get("ADMIN2_PASSWORD")))
                                .append("roles", List.of(roleAdmin))
                ));

                System.out.println("Usuários criados com sucesso!");
            } else {
                System.out.println("Usuários já existem no banco. Inicialização de usuários não necessária.");
            }


        } catch (Exception e) {
            System.err.println("Erro ao inicializar o MongoDB: " + e.getMessage());
        }
    }
}
