package org.meadowhawk.identicon.util

import java.awt.Color

enum Pattern {
    PATCHWORK({byte[] bytes ->
        //Take every 3 bytes to generate a color.
        String[] colors = []
        int c = 0
        String tmp = ""
        bytes.each { b->
            def hex = String.format("%02x", b)
            if(c <3) {tmp += hex; c++}
            else {
                colors += tmp
                c = 0; tmp = ""
            }
        }
        if(colors.size() <= 100) {colors += colors.take(100 - colors.size())}
        colors
    }, {hxColor ->
        SVGBuilder.svgColor(Color.decode("#${hxColor}"))
    }),

    MIRRORED({},{}),

    MONOCHROME({ byte[] bytes ->
        String hexFormat = "%02x"
        String[] colors = []
        String theColor = String.format(hexFormat, bytes[0]) + String.format(hexFormat, bytes[1]) + String.format(hexFormat, bytes[2])
        String white = "FFFFFF"
        bytes.each { b->
            colors += (b%2==0)? theColor: white
        }
        if(colors.size() > 100) {colors = colors.take(100)}
        colors
    },{hxColor ->
        SVGBuilder.svgColor(Color.decode("#${hxColor}"))
    }),

    RANDOM({ byte[] bytes ->  //ignoring for this
        String[] colors = []
        100.times {Math.random()}
        colors
    },{ hxColor -> SVGBuilder.hsbColor(hxColor)})

    Closure fillColors
    Closure buildColorValue
    Pattern(Closure fillColors, Closure colorList){
        this.fillColors = fillColors
        this.buildColorValue = colorList
    }

    static Pattern fromString(String patternName){
        try{
            Pattern.valueOf(patternName)
        } catch(IllegalArgumentException e){
            Pattern.RANDOM
        }
    }
}