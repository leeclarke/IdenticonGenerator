package org.meadowhawk.identicon.pattern

import org.meadowhawk.identicon.util.SVGBuilder

class RandomPattern implements RenderPattern {
    @Override
    void render(StringWriter writer, byte[] bytes, int width, int height) {
        SVGBuilder.generateGrid(writer, bytes, this, width, height)
    }

    def fillColors = { byte[] bytes, int colorCt ->  //ignoring for this
        String[] colors = []
        colorCt.times {colors += Math.random()}
        colors
    }

    def colorList = { hxColor -> SVGBuilder.hsbColor(hxColor)}
}
