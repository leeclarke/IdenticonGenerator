package org.meadowhawk.identicon.util

import java.awt.Color

enum Pattern {
    PATCHWORK({byte[] bytes, int colorCt ->
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
        while(colors.size() < colorCt){
            colors += colors
        }
        colors.take(colorCt)
    }, {hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),

    MIRRORED({},{}),

    DOTS({byte[] bytes, int colorCt ->
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

        while(colors.size() < colorCt){
            colors += colors
        }
        colors.take(colorCt)
    },{hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),
    MONOCHROME({ byte[] bytes, int colorCt ->
        String hexFormat = "%02x"
        int start = (bytes.size()<45)?42:10
        String[] colors = []
        String theColor = String.format(hexFormat, bytes[start]) + String.format(hexFormat, bytes[start+1]) + String.format(hexFormat, bytes[start+2])
        String white = "FFFFFF"
        bytes.each { b->
            colors += (b%2==0)? theColor: white
        }
        while(colors.size() < colorCt){
            colors += colors
        }
        colors.take(colorCt)
    },{hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }),
    TRICHROME({ byte[] bytes, int colorCt ->
        String[] colors = []
        String theColor = Math.random()
        String theOtherColor = Math.random()
        String white = 0

        bytes.each { b->
            colors += (b%2==0)? theColor: ((b%3==0)? theOtherColor:white)
        }
        while(colors.size() < colorCt){
            colors += colors
        }
        colors.take(colorCt)
    },{hxColor ->
        SVGBuilder.hsbColor(hxColor)
    }),
    RANDOM({ byte[] bytes, int colorCt ->  //ignoring for this
        String[] colors = []
        colorCt.times {colors += Math.random()}
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