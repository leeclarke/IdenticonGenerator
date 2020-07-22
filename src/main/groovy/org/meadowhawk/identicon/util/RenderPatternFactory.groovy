package org.meadowhawk.identicon.util

import org.meadowhawk.identicon.pattern.Dots
import org.meadowhawk.identicon.pattern.Monochrome
import org.meadowhawk.identicon.pattern.Patchwork
import org.meadowhawk.identicon.pattern.RandomPattern
import org.meadowhawk.identicon.pattern.RenderPattern
import org.meadowhawk.identicon.pattern.Trichrome

class RenderPatternFactory {
    static RenderPattern getPattern(String patternName){
        def ptrnNameUpper =patternName.toUpperCase()
        RenderPattern pattern = new RandomPattern()
        switch (ptrnNameUpper){
            case "MONOCHROME":
                pattern = new Monochrome()
                break
            case "TRICHROME":
                pattern = new Trichrome()
                break
            case "PATCHWORK":
                pattern = new Patchwork()
                break
            case "DOTS":
                pattern = new Dots()
                break
            case "RANDOM":
                pattern = new RandomPattern()
        }
        pattern
    }
}
