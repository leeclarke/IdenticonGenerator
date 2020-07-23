package org.meadowhawk.identicon.pattern

import org.meadowhawk.identicon.util.SVGBuilder

import java.awt.Color

class Monochrome implements  RenderPattern{
    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGBuilder.generateGrid(writer, bytes, this, width, height)
    }

    def fillColors = { byte[] bytes, int colorCt ->
        int start = (bytes.size()<45)?42:10
        String[] colors = []
        String theColor = SVGBuilder.getHexColor(bytes[start],bytes[start+1], bytes[start+2])
        String white = "FFFFFF"
        bytes.each { b->
            colors += (b%2==0)? theColor: white
        }
        while(colors.size() < colorCt){
            colors += colors
        }
        colors.take(colorCt)
    }

    def renderColor = {hxColor ->
        SVGBuilder.rbgColor(Color.decode("#${hxColor}"))
    }
}
