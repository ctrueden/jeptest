package jeptest;

import java.io.File;
import jep.Interpreter;
import jep.MainInterpreter;
import jep.SharedInterpreter;

public class HelloJep {

  public static File findJepLibrary(File dir) {
    if (!dir.isDirectory()) throw new IllegalArgumentException("Not a directory: " + dir);
    File libDir = new File(dir, "lib");
    if (!libDir.isDirectory()) throw new IllegalArgumentException("Not a directory: " + libDir);
    for (File f : libDir.listFiles()) {
      if (f.getName().startsWith("python") && f.isDirectory()) {
        File sitePackages = new File(f, "site-packages");
        if (!sitePackages.isDirectory()) continue;
        File jepDir = new File(sitePackages, "jep");
        if (!jepDir.isDirectory()) continue;
        String[] libnames = {"libjep.so", "libjep.jnilib", "jep.dll"};
        for (String libname : libnames) {
          File lib = new File(jepDir, libname);
          if (lib.isFile()) return lib;
        }
      }
    }
    throw new IllegalArgumentException("jep native library not in expected location");
  }

  public static void inferJepLibraryFromConda() {
    // HACK: Let jep discover the jep shared library from conda installation.
    String condaPrefix = System.getenv("CONDA_PREFIX");
    String pythonHome = System.getenv("PYTHONHOME");
    String stupid = System.getenv("STUPID");
    if (condaPrefix != null && pythonHome == null) {
      MainInterpreter.setJepLibraryPath(findJepLibrary(new File(condaPrefix)).getAbsolutePath());
    }
  }

  public static void main(String... args) {
    inferJepLibraryFromConda();

    try (Interpreter interp = new SharedInterpreter()) {
      interp.exec("from java.lang import System");
      interp.exec("s = 'Hello World'");
      interp.exec("System.out.println(s)");
      interp.exec("print(s)");
      interp.exec("print(s[1:-1])");
    }
  }
}
