package org.meadowhawk.identicon.util

import org.junit.Ignore
import spock.lang.Specification

import java.security.KeyPair

@Ignore
class MirrorSpec extends Specification {

    def "Verify that MIRRORED Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

//        when:
//        String[] colorsSM = Pattern.MIRRORED.fillColors(bytes, IconSize.SMALL.getSize())
//
//        then:
//        colorsSM.size() == IconSize.SMALL.getSize()

//        when:
//        String[] colors = Pattern.MIRRORED.fillColors(bytes, IconSize.REGULAR.getSize())
//
//        then:
//        colors.size() == IconSize.REGULAR.getSize()
//
//        when:
//        String[] colorsLG = Pattern.MIRRORED.fillColors(bytes, IconSize.LARGE.getSize())
//
//        then:
//        colorsLG.size() == IconSize.LARGE.getSize()
    }
}
