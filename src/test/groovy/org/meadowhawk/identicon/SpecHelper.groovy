package org.meadowhawk.identicon

import java.nio.charset.Charset
import java.security.KeyPair
import java.security.KeyPairGenerator

class SpecHelper {

    static KeyPair getKeys(){
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        KeyPair alice = keyPairGenerator.generateKeyPair()
        alice
    }

    static String getFakeUserName(){
        byte[] array = new byte[12]
        new Random().nextBytes(array)
         new String(array, Charset.forName("UTF-8"));
    }
}
