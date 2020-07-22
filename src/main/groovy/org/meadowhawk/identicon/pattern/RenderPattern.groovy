package org.meadowhawk.identicon.pattern

interface RenderPattern {
    void  render(StringWriter writer, byte[] bytes, int width, int height )
    def fillColors
    def colorList
}
