package org.meadowhawk.identicon.util

import org.meadowhawk.identicon.pattern.Monochrome
import spock.lang.Specification

class RenderPatternFactorSpec extends Specification {

    def "When calling the Factory it returns the correct object."(){
        when:
            def pattern = RenderPatternFactory.getPattern("Monochrome")
        then:
            assert pattern instanceof Monochrome
    }
}
