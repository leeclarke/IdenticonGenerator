package org.meadowhawk.identicon.util

//Remember, we are making svg files here so technically you can resize them to whatever you want.
enum IconSize {
    SMALL(50),  REGULAR(100), LARGE(200)

    int size
    IconSize(int pixels){
        this.size = pixels
    }

    int getSize(){ this.size}
}