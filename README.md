A simple example of [jep](https://github.com/ninia/jep) backed by Maven and conda.

NB: This example installs jep from PyPI, not jep from conda-forge. Unfortunately,
jep on conda-forge only has linux-64 binaries, and is outdated.

Run me via:

```
conda env update
conda activate jeptest
mvn -Pexec
```

Note that maven eats the Python-side output. To see that as well, you can do:
```
mvn package dependency:copy-dependencies
java -cp target/hello-world-0-SNAPSHOT.jar:target/dependency/'*' jeptest.HelloJep
```

If the `conda create` command is too slow for you, try using
[mamba](https://github.com/mamba-org/mamba#readme) instead.
