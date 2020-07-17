package org.meadowhawk.identicon

import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import static org.meadowhawk.identicon.util.IconSize.*
import org.meadowhawk.identicon.util.Pattern
import org.meadowhawk.identicon.util.SVGBuilder

class IdenticonGenerator {
    static boolean generateToFile(byte[] bytes, Pattern pattern, File file, IconSize size){
        file.withWriter { writer ->
            writer.write(IdenticonGenerator.generate(bytes, pattern, size).toString())
        }
    }

    static boolean generateToFile(byte[] bytes, Pattern pattern, String filePath){
        generateToFile(bytes, pattern, new File(filePath), REGULAR)
    }

    static boolean generateToFile(byte[] bytes, String pattern, String filePath){
        generateToFile(bytes, Pattern.fromString(pattern), new File(filePath), REGULAR)
    }

    static boolean generateToFile(String pattern, String filePath){
        generateToFile(Helper.getRandomSeed(), Pattern.fromString(pattern), filePath)
    }

    static StringWriter generate(byte[] bytes, Pattern pattern, IconSize size){
        if (bytes.size() < 20) throw new IllegalArgumentException("Input seed should be 20 chars at least to produce good randomness. Seed Len= ${bytes.size()}")
        def writer = new  StringWriter()
        def width = size.getSize()
        def height  = size.getSize()
        switch (pattern){
            case Pattern.MIRRORED:
                SVGBuilder.generateGrid(writer, bytes, Pattern.MIRRORED, width, height)
                break
            case Pattern.MONOCHROME:
                SVGBuilder.generateGrid(writer, bytes, Pattern.MONOCHROME, width, height)
                break
            case Pattern.TRICHROME:
                SVGBuilder.generateGrid(writer, bytes, Pattern.TRICHROME, width, height)
                break
            case Pattern.PATCHWORK:
                SVGBuilder.generateGrid(writer, bytes, Pattern.PATCHWORK, width, height)
                break
            case Pattern.DOTS:
                SVGBuilder.generateCircles(writer, bytes, Pattern.DOTS, width, height)
                break
            case Pattern.RANDOM:
            default:
                SVGBuilder.generateGrid(writer, bytes, Pattern.RANDOM, width, height)
                break
        }
        writer
    }
}
