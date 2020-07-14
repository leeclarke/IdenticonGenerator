package org.meadowhawk.identicon.util

import spock.lang.Specification
import org.meadowhawk.identicon.SpecHelper
import java.security.KeyPair

class PatternSpec extends Specification {

    def "Test RANDOM Pattern"(){
        when:
            def fillRtn = Pattern.RANDOM.fillColors()
        then:
            fillRtn == []
        when:
            String colorVal = Pattern.RANDOM.buildColorValue("000000")
        then:
            assert colorVal != null
            assert colorVal  ==~"^rgb\\(\\d{1,3}\\,\\d{1,3}\\,\\d{1,3}\\)\$"
    }

    def "Test PATCHWORK Pattern"(){
        when:
        String hexFormat = "%02x"
        KeyPair keys = SpecHelper.getKeys()
        byte[] bytes = keys.getPublic().encoded
        String firstColor = String.format(hexFormat, bytes[0]) + String.format(hexFormat, bytes[1]) + String.format(hexFormat, bytes[2])
        String[] colors = Pattern.PATCHWORK.fillColors(bytes)

        then:
        assert colors.size() >= 100
        assert colors[0] == firstColor
        colors.each {c ->
            assert c.length() == 6
        }

        when:

        String fill = Pattern.PATCHWORK.buildColorValue(firstColor)

        then:
        assert fill != null
        assert fill ==~"^rgb\\(\\d{1,3}\\,\\d{1,3}\\,\\d{1,3}\\)\$"
    }

//    def "Test MIRRORED Pattern"(){
//
//    }
//
//    def "Test MONOCHROME Pattern"(){
//
//    }

    def "patternTest"(){
        when:
        def ptrn = Pattern.fromString("TEST")

        then:
        assert ptrn == Pattern.RANDOM
    }
}
