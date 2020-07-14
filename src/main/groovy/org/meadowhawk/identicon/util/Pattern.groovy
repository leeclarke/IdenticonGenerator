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
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),

    MIRRORED({},{}),

    DOTS({byte[] bytes ->
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
    },{hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),
    MONOCHROME({ byte[] bytes ->
        String hexFormat = "%02x"
        String[] colors = []
        String theColor = String.format(hexFormat, bytes[42]) + String.format(hexFormat, bytes[43]) + String.format(hexFormat, bytes[44])
        String white = "FFFFFF"
        bytes.each { b->
            colors += (b%2==0)? theColor: white
        }
        if(colors.size() > 100) {colors = colors.take(100)}
        colors
    },{hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),
    TRICHROME({ byte[] bytes ->
        String[] colors = []
        String theColor = Math.random()
        String theOtherColor = Math.random()
        String white = 0
        bytes.each { b->
            colors += (b%2==0)? theColor: ((b%3==0)? theOtherColor:white)
        }
        colors
    },{hxColor ->
        SVGBuilder.hsbColor(hxColor)
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