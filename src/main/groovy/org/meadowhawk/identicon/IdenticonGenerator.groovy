package org.meadowhawk.identicon

import org.meadowhawk.identicon.util.Pattern
import org.meadowhawk.identicon.util.SVGBuilder

class IdenticonGenerator {

    static boolean generateToFile(byte[] bytes, Pattern pattern, File file){
        FileWriter fileWriter = new FileWriter(file)
        fileWriter.withWriter {
            fileWriter.write(IdenticonGenerator.generate(bytes, pattern).toString())
        }
    }

    static boolean generateToFile(byte[] bytes, Pattern pattern, String filePath){
        generateToFile(bytes, pattern, new File(filePath))
    }

    static boolean generateToFile(byte[] bytes, String pattern, String filePath){
        generateToFile(bytes, Pattern.fromString(pattern), new File(filePath))
    }

    static StringWriter generate(byte[] bytes, Pattern pattern){
        def writer = new  StringWriter()
        switch (pattern){
            case Pattern.MIRRORED:
                generateGrid(writer, bytes, Pattern.MIRRORED)
                break;
            case Pattern.MONOCHROME:
                generateGrid(writer, bytes, Pattern.MONOCHROME)
                break;
            case Pattern.PATCHWORK:
                generateGrid(writer, bytes, Pattern.PATCHWORK)
                break;
            case Pattern.RANDOM:
            default:
                generateGrid(writer, bytes, Pattern.RANDOM)
                break;
        }
        writer
    }

    static protected  void generateGrid(Writer writer, byte[] bytes, Pattern pattern){
        String[] colors = pattern.fillColors(bytes)
        SVGBuilder.createSvg(writer, 100, 100) { width, height ->
            int x = 0
            int y = 0
            int b = 0
            while (y < height) {
                while (x < width) {
                    rect(x: x, y: y, width: 10, height: 10, fill: pattern.buildColorValue(colors[b]))
                    x += 10
                    b++
                }
                y += 10
                x = 0
            }
        }
    }

}
