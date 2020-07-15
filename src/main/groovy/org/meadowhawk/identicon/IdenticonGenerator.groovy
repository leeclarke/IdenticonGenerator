package org.meadowhawk.identicon

import org.meadowhawk.identicon.util.Helper
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

    static boolean generateToFile(String pattern, String filePath){
        generateToFile(Helper.getRandomSeed(), Pattern.fromString(pattern), new File(filePath))
    }

    static StringWriter generate(byte[] bytes, Pattern pattern){
        def writer = new  StringWriter()
        switch (pattern){
            case Pattern.MIRRORED:
                SVGBuilder.generateGrid(writer, bytes, Pattern.MIRRORED)
                break
            case Pattern.MONOCHROME:
                SVGBuilder.generateGrid(writer, bytes, Pattern.MONOCHROME)
                break
            case Pattern.TRICHROME:
                SVGBuilder.generateGrid(writer, bytes, Pattern.TRICHROME)
                break
            case Pattern.PATCHWORK:
                SVGBuilder.generateGrid(writer, bytes, Pattern.PATCHWORK)
                break
            case Pattern.DOTS:
                SVGBuilder.generateCircles(writer, bytes, Pattern.DOTS)
                break
            case Pattern.RANDOM:
            default:
                SVGBuilder.generateGrid(writer, bytes, Pattern.RANDOM)
                break
        }
        writer
    }
}
