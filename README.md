![IdenticonGen Logo](./docs/img/IdenticonGen.png)
# IdenticonGenerator
A lite weight tool for generating Identicons in Java and Groovy.   

[ ![Download](https://api.bintray.com/packages/meadowhawk/utils/IdenticonGenerator/images/download.svg?version=1.1.0) ](https://bintray.com/meadowhawk/utils/IdenticonGenerator/1.1.0/link)

### A what?
  An [Identicon](https://en.wikipedia.org/wiki/Identicon) is a visual representation of a hash value, usually of an IP address, that serves to identify a user of a computer system as a form of avatar while protecting the users' privacy. The original Identicon was a 9-block graphic, and the representation has been extended to other graphic forms by third parties.

  Note: Some of the patterns are using randomization to ensure an interesting pattern while Patchwork, & Dots are derived from the hex value from a public Key.



## A Few Samples
| DOTS | Patchwork | Monochrome | Trichrome | Random|
|------|-----------|------------|-----------|-------|
![DOTS Pattern](./docs/img/dotsIcon.svg) | ![DOTS Pattern](./docs/img/patchworkIcon.svg) | ![Monochrome Pattern](./docs/img/monoIcon.svg) | ![Trichrome Pattern](./docs/img/trichromeIcon.svg) | ![Random Pattern](./docs/img/random.svg)

## Getting Started

### Gradle
#### Add Jcenter to your repos if your not already using it.
 ```
repositories {
    mavenCentral()
    jCenter()
}
 ```
#### Import Identicon Generator into your project from JCenter Note:
```
implementation 'org.meadowhawk:IdenticonGenerator:1.0.0'
```
### Maven
#### Add to your pom
```
<dependency>
	<groupId>org.meadowhawk</groupId>
	<artifactId>IdenticonGenerator</artifactId>
	<version>1.0.0</version>
	<type>pom</type>
</dependency>
```

### Code Examples
The interface is intended to be easy to use so all calls can be made on the static methods in [IdenticonGenerator.groovy](./src/main/groovy/org/meadowhawk/identicon/IdenticonGenerator.groovy) Unless you want to do something that isn't already coded, this is all you have to do!

#### Write a little Code to create an Identicon in a file!
```
IdenticonGenerator.generateToFile(new Monochrome(), "TEST.SVG")
```

#### Want more control where the image goes? Just get an OutputStream so you can do what you want with it later
```
StringWriter out = IdenticonGenerator.generate("TheByteArrayFromaUserNameOrPublickKey".getBytes(), new Trichrome, IconSize.REGULAR)

```
There are quire a few other static methods available to call, take a look at IdenticonGenerator for more.

### More Stuff

#### Unique Seeds
To ensure that the users get unique Identicons you can base them of a byte array generated from their unique id. There are a few ways to generate this byte array:
1. User their Public Key: (This is what the no byte array method do but using a randomly generated Key. of corse its better to use an actual public key)  ``` KeyPair.getPublic().encoded ```  see. [Helper.groovy](./src/main/groovy/org/meadowhawk/identicon/util/Helper.groovy) for an example.

2. Use the Users Id  and add on their ip address (must be >= 20 chars) ``` "lees2bytes@188.192.9.109".getBytes()```
3. User their email address; really any unique identifier will work, just make sure its on the longer side (50+ chars  is nice) or the uniqueness wills suffer.


#### Patterns
There are a number of default patterns that are provided to give the Identicon a unique looks. Some are based entirely off the seed byte array and others have varying degrees of randomness in them just to amp up the interest level. 

* PATCHWORK - Seeded from byte array, provides a nifty patchwork look when using a users public key.
* MONOCHROME - Seeded from the 42nd - 44th bytes in the seed, it provides a single color on a white background similar to the gitHub Identicon.
* TRICHROME - Seeded Randomly, it provides 2 colors on a white background similar to MONOCHROME.
* DOTS - Seeded from byte array, it randomly throws varying sized dots on a dark or light background. 
* RANDOM - Comes out a lot like Patchwork but the colors are randomly selected. Not based on seed and probably less unique?

#### Make your Own Pattern
Want something different for you pattern?  No problem, you can implement your own pattern by implementing the [RenderPattern](./src/main/groovy/org/meadowhawk/identicon/pattern/RenderPattern.groovy) interface and passing that as one of the params to the appropriate ```IdenticonGenerator``` method.

To implement your own pattern you would simply implement the ```.render()``` method, and provided closures for fillColors and colorList which are sued by the SVGBuilder methods. If you don't want to render grids or circles you can write your own version of the the SVGBuider function and likely skip the closures. The best way to understand how to build a pattern is to look at one of the default patterns. a very simple example is the [RandomPattern](./src/main/groovy/org/meadowhawk/identicon/pattern/RandomPattern.groovy) shown below.

```
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

    def renderColor = { hxColor -> SVGBuilder.hsbColor(hxColor)}
}

```
* render()  makes a call to the SVGBuilder to build a grid based svg file passing the writer, the seed bytes used to generate the colors (public key, username etc.) and of course dimensions. note: it expects ht & wt to be equal because its building a square. 
* fillColors is a closure that the render method will use to determine what colors will be used and puts them in the correct order, in this case one color per square to be rendered. (This is  pretty simple and a little limiting, if you want something very different then I would skip this and build it out in the render method..)
* renderColor - Provides a translation from the generated color to the SVG color. In most of the defaults the color is either a web hex color or an hsb color and they have to be translated to rbg colors for teh svg format.

Just to reiterate, if you aren't going to use the SVGBuilder, you can set these closures to ={} and just do your own thing in the render(). This oddity will likely go on my todo list. 

### Want to help?
Dive on in or give me a shout! There's a few issues below that need work and I'm open to any expansions etc.

### TODOs
- [x] Reactor the Pattern Enum into a base class. Including closures in an enum was pretty amazing but this would benefit from a default class that can be overridden and allow for more patterns to be created without messing with an enum thats getting kinda ugly/
- [x] Allow for icon size changes, default is currently 100x100
- [ ] Refine Hex to color algorithm
- [x] Make TRICHROME based off seed.
