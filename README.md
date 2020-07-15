![IdenticonGen Logo](./docs/img/IdenticonGen.png)
# IdenticonGenerator
A lite weight tool for generating Identicons in Java and Groovy.

### A what?
  An [Identicon](https://en.wikipedia.org/wiki/Identicon) is a visual representation of a hash value, usually of an IP address, that serves to identify a user of a computer system as a form of avatar while protecting the users' privacy. The original Identicon was a 9-block graphic, and the representation has been extended to other graphic forms by third parties.

  Note: Some of the patterns are using randomization to ensure an interesting pattern while Patchwork, & Dots are derived from the hex value from a public Key.



## A Few Samples
| DOTS | Patchwork | Monochrome | Trichrome | Random|
|------|-----------|------------|-----------|-------|
![DOTS Pattern](./docs/img/dotsIcon.svg) | ![DOTS Pattern](./docs/img/patchworkIcon.svg) | ![Monochrome Pattern](./docs/img/monoIcon.svg) | ![Trichrome Pattern](./docs/img/trichromeIcon.svg) | ![Random Pattern](./docs/img/random.svg)

## Getting Started


### TODOs
* Reactor the Pattern Enum into a base class. Including closures in an enum was pretty amazing but this would benefit from a default class that can be overridden and allow for more patterns to be created without messing with an enum thats getting kinda ugly/
* Allow for icon size changes, default is currently 100x100
* Refine Hex to color algorithm