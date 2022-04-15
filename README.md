# FScriptRenewed

FScriptRenewed - is a fork of original project, called FScript, to make it more compatible and optimised for current java versions.

The wiki page of FScript - http://fscript.sourceforge.net/.

## Goals
- [ ] Create a simple wiki for the project.
- [X] Update to the recent Java version and make some compatibility tweaks.
- [X] Add "$import" support for connection core libraries.
- [ ] Implement core libraries (math, unsafe, app, etc).
- [ ] Finish the engine.
- [ ] Understand how this whole thing works.

## Building the jar.
1. Using IDEs with inbuilt/plugin-supported Maven (Eclipse/Intellij IDEA/NetBeans).

   Open the downloaded or forked repository as a maven project, run task `package`.
3. Without IDE (maven should be installed).

   Commands that have to be typed in terminal:
   ```
   mvn install
   mvn clean compile package
   ```

## Corner of License
The fork is under [GNU Lesser General Public License v2.1](https://github.com/WaterfallFlower/FScriptRenewed/blob/main/LICENSE), but the original project is under [the mix of GNU Library General Public License v2 and Apache License](https://github.com/WaterfallFlower/FScriptRenewed/blob/main/COPYING_ORIGINAL) (as the author of the original project explains in credits file).

The commit with original code can be found [here](https://github.com/WaterfallFlower/FScriptRenewed/tree/0f145defddbdb2a5d08baf7dec5603bd3b9ffe93).

The original project is located [here](https://sourceforge.net/projects/fscript/), [copyright Joachim Van der Auwera](https://github.com/WaterfallFlower/FScriptRenewed/blob/main/CREDITS_ORIGINAL).

The fork project copyright WaterfallFlower, 2022 year.
