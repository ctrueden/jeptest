Run me via:

```
conda create -n jeptest python=3.7 openjdk=8 jep
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
