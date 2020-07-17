package org.meadowhawk.identicon.util

import spock.lang.Specification
import java.security.KeyPair

class PatternSpec extends Specification {

    def "Test PATCHWORK Pattern"(){
        when:
        String hexFormat = "%02x"
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded
        String firstColor = String.format(hexFormat, bytes[0]) + String.format(hexFormat, bytes[1]) + String.format(hexFormat, bytes[2])
        String[] colors = Pattern.PATCHWORK.fillColors(bytes, IconSize.REGULAR.getSize())

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

    def "Verify that RANDOM Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

        when:
        String[] colorsSM = Pattern.RANDOM.fillColors(bytes, IconSize.SMALL.getSize())

        then:
        colorsSM.size() == IconSize.SMALL.getSize()

        when:
        String[] colors = Pattern.RANDOM.fillColors(bytes, IconSize.REGULAR.getSize())

        then:
        colors.size() == IconSize.REGULAR.getSize()

        when:
        String[] colorsLG = Pattern.RANDOM.fillColors(bytes, IconSize.LARGE.getSize())

        then:
        colorsLG.size() == IconSize.LARGE.getSize()

        when:
        String colorVal = Pattern.RANDOM.buildColorValue("000000")
        then:
        assert colorVal != null
        assert colorVal  ==~"^rgb\\(\\d{1,3}\\,\\d{1,3}\\,\\d{1,3}\\)\$"
    }

    def "Verify that TRICHROME Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

        when:
        String[] colorsSM = Pattern.TRICHROME.fillColors(bytes, IconSize.SMALL.getSize())

        then:
        colorsSM.size() == IconSize.SMALL.getSize()

        when:
        String[] colors = Pattern.RANDOM.fillColors(bytes, IconSize.REGULAR.getSize())

        then:
        colors.size() == IconSize.REGULAR.getSize()

        when:
        String[] colorsLG = Pattern.RANDOM.fillColors(bytes, IconSize.LARGE.getSize())

        then:
        colorsLG.size() == IconSize.LARGE.getSize()
    }

    def "Verify that DOTS Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

        when:
        String[] colorsSM = Pattern.DOTS.fillColors(bytes, IconSize.SMALL.getSize())

        then:
        colorsSM.size() == IconSize.SMALL.getSize()

        when:
        String[] colors = Pattern.DOTS.fillColors(bytes, IconSize.REGULAR.getSize())

        then:
        colors.size() == IconSize.REGULAR.getSize()

        when:
        String[] colorsLG = Pattern.DOTS.fillColors(bytes, IconSize.LARGE.getSize())

        then:
        colorsLG.size() == IconSize.LARGE.getSize()
    }

    def "Verify that PATCHWORK Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

        when:
        String[] colorsSM = Pattern.PATCHWORK.fillColors(bytes, IconSize.SMALL.getSize())

        then:
        colorsSM.size() == IconSize.SMALL.getSize()

        when:
        String[] colors = Pattern.PATCHWORK.fillColors(bytes, IconSize.REGULAR.getSize())

        then:
        colors.size() == IconSize.REGULAR.getSize()

        when:
        String[] colorsLG = Pattern.PATCHWORK.fillColors(bytes, IconSize.LARGE.getSize())

        then:
        colorsLG.size() == IconSize.LARGE.getSize()
    }

    def "Verify that MONOCHROME Patterns fill closure works for all sizes"(){
        given:
        byte[] bytes = Helper.getRandomSeed()

        when:
        String[] colorsSM = Pattern.MONOCHROME.fillColors(bytes, IconSize.SMALL.getSize())

        then:
        colorsSM.size() == IconSize.SMALL.getSize()

        when:
        String[] colors = Pattern.MONOCHROME.fillColors(bytes, IconSize.REGULAR.getSize())

        then:
        colors.size() == IconSize.REGULAR.getSize()

        when:
        String[] colorsLG = Pattern.MONOCHROME.fillColors(bytes, IconSize.LARGE.getSize())

        then:
        colorsLG.size() == IconSize.LARGE.getSize()
    }
}
