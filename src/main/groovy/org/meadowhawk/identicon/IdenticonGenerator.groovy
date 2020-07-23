package org.meadowhawk.identicon

import org.meadowhawk.identicon.pattern.RenderPattern
import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import org.meadowhawk.identicon.util.RenderPatternFactory

import static org.meadowhawk.identicon.util.IconSize.*


class IdenticonGenerator {
    static boolean generateToFile(byte[] bytes, String pattern, File file, IconSize size){
        file.withWriter { writer ->
            writer.write(IdenticonGenerator.generate(bytes, RenderPatternFactory.getPattern(pattern), size).toString())
        }
    }

    static boolean generateToFile(String pattern, String filePath){
        generateToFile(Helper.getRandomSeed(), pattern, new File(filePath), IconSize.REGULAR)
    }

    static boolean generateToFile(byte[] bytes, RenderPattern pattern, File file, IconSize size){
        file.withWriter { writer ->
            writer.write(IdenticonGenerator.generate(bytes, pattern, size).toString())
        }
    }

    static boolean generateToFile(byte[] bytes, RenderPattern pattern, String filePath){
        generateToFile(bytes, pattern, new File(filePath), REGULAR)
    }


    static StringWriter generate(byte[] bytes, RenderPattern pattern, IconSize size){
        if (bytes.size() < 20) throw new IllegalArgumentException("Input seed should be 20 chars at least to produce good randomness. Seed Len= ${bytes.size()}")
        def writer = new  StringWriter()
        def width = size.getSize()
        def height = size.getSize()
        pattern.render(writer, bytes, width, height)
        writer
    }


}
