package com.kaiv.vault.service;

import com.kaiv.vault.model.MySecret;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

@Service
@RequiredArgsConstructor
public class VaultSecretService {

    private final VaultTemplate vaultTemplate;

    public void saveSecret(String path, MySecret secrets) {

        VaultKeyValueOperations kvOps = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        kvOps.put(path, secrets);
    }

    public MySecret readSecret(String path) {

        VaultKeyValueOperations kvOperations = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        VaultResponseSupport<MySecret> response = kvOperations.get(path, MySecret.class);

        if (response != null) {
            return response.getData();
        } else {
            throw new RuntimeException("Secret not found!");
        }
    }
}
