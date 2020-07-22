package org.meadowhawk.identicon.util

import org.meadowhawk.identicon.pattern.Dots
import org.meadowhawk.identicon.pattern.Monochrome
import org.meadowhawk.identicon.pattern.Patchwork
import org.meadowhawk.identicon.pattern.Random
import org.meadowhawk.identicon.pattern.RenderPattern
import org.meadowhawk.identicon.pattern.Trichrome

class RenderPatternFactory {
    static RenderPattern getPattern(String patternName){
        switch (patternName.toUpperCase()){
            case "MONOCHROME":
                new Monochrome()
            case "TRICHROME":
                new Trichrome()
            case "PATCHWORK":
                new Patchwork()
            case "DOTS":
                new Dots()
            case "RANDOM":
            default:
                new Random()
        }
    }
}
