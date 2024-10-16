package com.example.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheInit implements CommandLineRunner {

    @Autowired
    private AdapayConfigInit adapayConfigInit;

    @Override
    public void run(String... args) throws Exception {
        adapayConfigInit.init();
    }
}
