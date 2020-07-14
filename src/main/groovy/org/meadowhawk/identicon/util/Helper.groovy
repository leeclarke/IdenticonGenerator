package org.meadowhawk.identicon.util

import java.security.KeyPair
import java.security.KeyPairGenerator

class Helper {
    static KeyPair getKeys(){
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        KeyPair alice = keyPairGenerator.generateKeyPair()
        alice
    }

    static byte[] getRandomSeed(){
        Helper.getKeys().getPublic().encoded
    }
}
