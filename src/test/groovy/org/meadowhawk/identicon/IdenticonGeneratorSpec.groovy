package org.meadowhawk.identicon

import org.meadowhawk.identicon.pattern.Dots
import org.meadowhawk.identicon.pattern.Monochrome
import org.meadowhawk.identicon.pattern.Patchwork
import org.meadowhawk.identicon.pattern.RandomPattern
import org.meadowhawk.identicon.pattern.Trichrome
import org.meadowhawk.identicon.util.Helper
import org.meadowhawk.identicon.util.IconSize
import spock.lang.Shared
import spock.lang.Specification

import java.security.KeyPair

class IdenticonGeneratorSpec extends Specification{

    @Shared
    KeyPair keys = Helper.getKeys()

    def "Generate a Random Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "rnd.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, new RandomPattern(), fileName)

        then:
        assert new File(fileName).exists()
    }

    def "Generate a Patchwork Styled Identicon"(){
        given:
        KeyPair keys = Helper.getKeys()
        byte[] bytes = keys.getPublic().encoded

        when:
        StringWriter writer = IdenticonGenerator.generate(bytes,new Patchwork(), IconSize.SMALL)

        then:
        assert writer != null
        String content = writer.toString()
        def startStr = "<svg width=\'${IconSize.SMALL.size}\' height=\'${IconSize.SMALL.size}\' xmlns=\'http://www.w3.org/2000/svg\' xmlns:xlink=\'http://www.w3.org/1999/xlink\' version=\'1.1\'>"
        assert content.startsWith(startStr)
        assert content.endsWith("</svg>")

        File out = new File("patch.svg").withWriter { w->
            w.write(content)
        }
    }

    def "Generate a MONOCHROME Styled Identicon using the RenderPattern"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "monoIconRP.svg"

        when:
        IdenticonGenerator.generateToFile(bytes, new Monochrome(), new File(fileName), IconSize.REGULAR)

        then:
        File monoFile = new File(fileName)
        assert monoFile.exists()
        String monoContent = new FileReader(monoFile).text
        assert monoContent.startsWith("<svg width='${IconSize.REGULAR.size}' height='${IconSize.REGULAR.size}' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")
        assert monoContent.contains("<rect x='0' y='0'")
        assert monoContent.endsWith("</svg>")
    }

    def "An error is thrown if the seed is too short"(){
        when: "The seed is too short"
        IdenticonGenerator.generateToFile("tooShort".getBytes(), new Monochrome(), "errorFile.svg")

        then:
        thrown IllegalArgumentException
    }

    def "Generate a DOTS Styled Identicon"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "dotsIcon.svg"

        when:
        IdenticonGenerator.generateToFile(bytes,new Dots(), fileName)

        then:
        File dotsFile = new File(fileName)
        assert dotsFile.exists()

        String dotsContent = new FileReader(fileName).text
        assert dotsContent.startsWith("<svg width='${IconSize.REGULAR.size}' height='${IconSize.REGULAR.size}' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")
        assert dotsContent.contains("<rect x='0' y='0' width='100' height='100' fill=")
        assert dotsContent.contains("<circle")
        assert dotsContent.endsWith("</svg>")

    }

    def "Generate a TRICHROME Styled Identicon that's LARGE"(){
        given:
        byte[] bytes = keys.getPublic().encoded
        String fileName =  "trichromeIcon.svg"
        File file = new File(fileName)

        when:
        IdenticonGenerator.generateToFile(bytes, new Trichrome(), file, IconSize.LARGE)

        then:
        assert new File(fileName).exists()
        String triContent = new FileReader(fileName).text
        assert triContent.startsWith("<svg width='${IconSize.LARGE.size}' height='${IconSize.LARGE.size}' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")
        assert triContent.contains("<rect x='0' y='0' width='10' height='10' fill=")
        assert triContent.endsWith("</svg>")
    }


    def "When calling the simplified generate method it works just like the other file gen methods."(){
        given:
        def filePath = "simpleTri.svg"
        def pattern = "Trichrome"

        when:
        IdenticonGenerator.generateToFile(pattern, filePath)

        then:
        assert new File(filePath).exists()
        String triContent = new FileReader(filePath).text
        assert triContent.startsWith("<svg width='${IconSize.REGULAR.size}' height='${IconSize.REGULAR.size}' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' version='1.1'>")
        assert triContent.contains("<rect x='0' y='0' width='10' height='10' fill=")
        assert triContent.endsWith("</svg>")
    }
}
