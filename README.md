Run me via:

```
conda create -n jeptest python=3.7 openjdk=8 jep
conda activate jeptest
mvn -Pexec
```

If the `conda create` command is too slow for you, try using
[mamba](https://github.com/mamba-org/mamba#readme) instead.
