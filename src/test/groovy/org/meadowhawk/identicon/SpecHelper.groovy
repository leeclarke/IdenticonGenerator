package org.meadowhawk.identicon

import java.nio.charset.Charset
import java.security.KeyPair
import java.security.KeyPairGenerator

class SpecHelper {
    static String getFakeUserName(){
        byte[] array = new byte[12]
        new Random().nextBytes(array)
         new String(array, Charset.forName("UTF-8"));
    }
}
