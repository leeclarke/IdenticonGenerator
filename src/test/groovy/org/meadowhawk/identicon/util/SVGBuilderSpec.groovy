package org.meadowhawk.identicon.util

import org.meadowhawk.identicon.util.SVGBuilder
import spock.lang.*

class SVGBuilderSpec extends Specification {

    def svgTestFile = "test.svg"

    def "Calling SVGBuilder produces an image at given location"(){
        given:
        SVGBuilder.createSvg(new File(svgTestFile), 100, 100) {  width,height ->
            int x = 0
            int y = 0
            while (y < height) {
                while (x < width) {
                    rect(x: x, y: y, width: 10, height: 10, fill: SVGBuilder.hsbColor(Math.random()))
                    x += 10
                }
                y += 10
                x = 0
            }
        }

        expect:
        def theImage =  new File(svgTestFile)
        assert theImage.exists()
        String fileContent
        theImage.eachLine { line ->
            if(line != null) fileContent += line
        }
        assert fileContent.contains("<svg width='100' height='100' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")

    }

    def "Calling SVGBuilder produces a writer with the svg data for saving later."(){
        given:
        def writer = new  StringWriter()
        SVGBuilder.createSvg(writer, 100, 100) {  width,height ->
            int x = 0
            int y = 0
            while (y < height) {
                while (x < width) {
                    rect(x: x, y: y, width: 10, height: 10, fill: SVGBuilder.hsbColor(Math.random()))
                    x += 10
                }
                y += 10
                x = 0
            }
        }

        expect:
        String content = writer.dump()
        writer.withWriter {
         content = writer.toString()
        }

        assert content != null
        assert content.startsWith("<svg width='100' height='100' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")
    }
}
