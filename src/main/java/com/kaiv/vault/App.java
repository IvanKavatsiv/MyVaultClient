package com.kaiv.vault;

import com.kaiv.vault.model.MySecrets;
import com.kaiv.vault.service.VaultSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private VaultSecretService vaultSecretService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {

        MySecrets secrets = new MySecrets();
        secrets.setUsername("MyUsername");
        secrets.setPassword("MyPassword");
        String path = "myapp/config";

        vaultSecretService.saveSecret(path, secrets);

        MySecrets secretsResponse = vaultSecretService.readSecret(path);
        System.out.println(secretsResponse);

    }
}
