package com.kaiv.vault.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final VaultTemplate vaultTemplate;

    @Bean
    public VaultKeyValueOperations kvOperations(){
        return vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
    }
}
