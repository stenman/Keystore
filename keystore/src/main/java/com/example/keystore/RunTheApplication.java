package com.example.keystore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: Change name of this class and its methods.
//TODO: Also, this is probably not the way it should be implemented. Fix later.
@Component
public class RunTheApplication {
    @Autowired
    StartMyBatis startMyBatis;

    @Autowired
    PasswordEncryptionService passwordEncryptionService;

    public void startStuff() {
        startMyBatis.gogo();
        //Simulate service here...
    }
}
