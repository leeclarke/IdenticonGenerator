package org.meadowhawk.identicon.pattern

import org.meadowhawk.identicon.util.SVGBuilder

import java.awt.Color

class Dots implements  RenderPattern{

    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGBuilder.generateCircles(writer, bytes, this, width, height)
    }

    def fillColors = {byte[] bytes, int colorCt ->
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
    }

    def colorList = {hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }
}
