package org.meadowhawk.identicon.pattern

import org.meadowhawk.identicon.util.SVGBuilder

import java.awt.Color

class Trichrome implements RenderPattern {
    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGBuilder.generateGrid(writer, bytes, this, width, height)
    }

    def fillColors = { byte[] bytes, int colorCt ->
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
    }

    def colorList = {hxColor ->
        SVGBuilder.hsbColor(hxColor)
    }

}
