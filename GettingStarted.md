## Setting Up the Environment ##

1. Get the latest version of the JDK, Eclipse and Mercurial ([TortoiseHg](http://tortoisehg.bitbucket.org/) works well for Windows).

2. Add the following update site to get the JavaCC plugin for Eclipse: http://eclipse-javacc.sourceforge.net/.
<a href='Hidden comment: 
After installing the JavaCC plugin, right click on the compilerjava project and go to Properties.  Click on JavaCC Options in the navigation pane on the left side of the Properties dialog.  Then click on the JavaCC Options tab.  Uncheck the STATIC checkbox and accept the changes.  After a moment, all of the missing files should be generated.
'></a>

3. Clone the repository as instructed on the [Source](http://code.google.com/p/plaid-lang/source/checkout) page.  Import all of the projects contained in the plaid-lang directory into your Eclipse workspace.

## Building the Plaid Compiler and Runtime ##

Close Eclipse (**Important!**). From the command line, navigate to the `tests` directory and say `ant build`. Everything should build automatically. If you want to confirm that everything build correctly call `ant test`.

## Building and Running Plaid Programs ##

### From Windows ###

  1. Put the directory `${PLAID-LANG}\fastbuilder` in your `PATH`, where `${PLAID-LANG}` is the top-level directory of the code tree.
  1. To compile Plaid programs call `plaidc.bat SOURCEPATH TEMPPATH OUTPUTPATH`. Where:
    * `SOURCEPATH` is the directory containing the Plaid files you want to compile.
    * `TEMPPATH` is the directory where the Plaid compiler should put the temporary Java files your Plaid programs generate.
    * `OUTPUTPATH`  is the directory where the Plaid compiler should put the class files your Plaid programs generate.
  1. To run the Plaid programs call `plaid.bat CLASSPATH CLASS`. Where:
    * `CLASSPATH` is a semicolon-separated list of directories containing the class files you need to run your program. Note: You will likely inlcude the `OUTPUTPATH` from the previous step.
    * `CLASS` is the name of the class containing your main method. Any top-level method in Plaid is runnable in this way. For example, if you want to run a top-level method named `hello` in package `edu.cmu.simple` then `CLASS` should be `edu.cmu.simple.hello`.
### From Mac OSX or Linux ###
  1. Instruction coming soon.

### From Inside Eclipse ###

To build the `compilerTests` project from inside Eclipse, run BuildAllExamples.java as a JUnit test.

To run a single program, find the Java file and run it as a Java application.  Here we'll run the Java program located at `coreOutput/coreExamples/hello/main.java`.  First refresh the `coreOutput` directory.  Then right click on the `main.java` file, select `Run As`, and select `Java Application`.  You should see the same friendly message as before on the Eclipse console.

In Eclipse you can also run multiple programs using the JUnit framework.  To build all the examples as JUnit tests, right click on `plaid.compiler.java.test/BuildAllExamples.java`, select `Run As`, and select `JUnit Test`.  You can also run all the examples by running `RunAllExamples.java` as a JUnit test.

## Plaid Syntax Highlighting in Eclipse ##

To get syntax highlighting for Plaid files working in Eclipse, first install the Eclipse Colorer plugin.  The easiest way to do this is via Eclipse's software installation manager, so go to `Help -> Install New Software`, add http://colorer.sf.net/eclipsecolorer/ as a new location and follow the installation wizard.

Next, copy the files "plaid.hrc" and "proto.hrc" from `plaid-lang/stdlib/misc/` to the directory where Colorer keeps its syntax description files, overwriting the old "proto.hrc" in the process.  Under Linux the syntax directory is located at `~/.eclipse/org.eclipse.platform_$VERSIONSTRING/plugins/net.sf.colorer_$ANOTHERVERSIONSTRING/colorer/hrc/`.
Alternatively, you can also place a symbolic link to the plaid.hrc file under version control in the plugin directory instead of copying the file (proto.hrc probably won't change).  That way, the link always points to the most recent version of the syntax description file.

At this point, after your restarted Eclipse, it should already know about Plaid.  Look at `Window -> Preferences -> Color Library Editor -> File Types` and search for "Plaid".  It should show up under "Rare Languages" (for now ;-).  The only thing left to do is to tell Eclipse that it should actually use the colorer plugin for `.plaid` files.  While still in Preferences, go to `General -> Editors -> File Associations`, add "`*`.plaid" as a file extension and link it to "Colorer Editor" using the menu at the bottom.  After that you should have syntax highlighting for Plaid files.