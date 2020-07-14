package org.meadowhawk.identicon

import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.Pattern
import spock.lang.Shared
import spock.lang.Specification

import java.security.KeyPair

class IdenticonGeneratorSpec extends Specification{

    @Shared
    KeyPair keys = Helper.getKeys()

    def "Generate a Random/rainbow Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "rainbowIcon.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, Pattern.PATCHWORK, fileName)

        then:
        assert new File(fileName).exists()
    }

    def "Generate a Patchwork Styled Identicon"(){
        given:
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded

        when:
        Writer writer = IdenticonGenerator.generate(bytes, Pattern.PATCHWORK)

        then:
        assert writer != null
    }

    def "Generate a MONOCHROME Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "monoIcon.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, Pattern.MONOCHROME, fileName)

        then:
        assert new File(fileName).exists()
    }

    def "Generate a DOTS Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "dotsIcon.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, Pattern.DOTS, fileName)

        then:
        assert new File(fileName).exists()
    }
    def "Generate a TRICHROME Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "trichromeIcon.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, Pattern.TRICHROME, fileName)

        then:
        assert new File(fileName).exists()
    }

}
