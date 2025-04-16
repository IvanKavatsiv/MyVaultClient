package com.kaiv.vault.service;

import com.kaiv.vault.model.MySecret;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.support.VaultResponseSupport;

@Service
@RequiredArgsConstructor
public class VaultSecretService {

    private final VaultKeyValueOperations kvOperations;

    public void saveSecret(String path, MySecret secrets) {
        kvOperations.put(path, secrets);
    }

    public MySecret readSecret(String path) {

        VaultResponseSupport<MySecret> response = kvOperations.get(path, MySecret.class);

        if (response != null) {
            return response.getData();
        } else {
            throw new RuntimeException("Secret not found!");
        }
    }
}
