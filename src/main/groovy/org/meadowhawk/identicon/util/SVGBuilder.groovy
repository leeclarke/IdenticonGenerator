package org.meadowhawk.identicon.util

import groovy.xml.MarkupBuilder

import java.awt.Color

class SVGBuilder {
    static createSvg(File outputFile, int width, int height, Closure content) {
        def writer = new StringWriter()
        def markupBuilder = new MarkupBuilder(writer)

        content.setDelegate(markupBuilder)
        markupBuilder.svg(width:width,height:height, xmlns:'http://www.w3.org/2000/svg', 'xmlns:xlink':'http://www.w3.org/1999/xlink', version:'1.1') {
            content(width, height)
        }
        outputFile.withWriter {
            it << writer.toString()
        }
    }

    static createSvg(StringWriter writer, int width, int height, Closure content) {
        def markupBuilder = new MarkupBuilder(writer)
        content.setDelegate(markupBuilder)
        markupBuilder.svg(width:width,height:height, xmlns:'http://www.w3.org/2000/svg', 'xmlns:xlink':'http://www.w3.org/1999/xlink', version:'1.1') {
            content(width, height)
        }
    }

    static def hsbColor = { hue, saturation = 1.0f, brightness = 1.0f ->
        def color = java.awt.Color.getHSBColor(hue as float, saturation as float, brightness as float)
        return "rgb(${color.red},${color.green},${color.blue})"
    }

    static def svgColor = { Color color ->
        return "rgb(${color.red},${color.green},${color.blue})"
    }
}
