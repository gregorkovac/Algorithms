# Discrete Fourier Transformation (`n`-th primitive root)

**Description:** This algorithm finds the smallest primer number `p`, such that there is at least one 'n'-th primitive root in 'Z_n'. It also finds all of the 'n'-th primitive roots and the corresponding Vandermonde matrix, which is used in the discrete Fourier transformation.

**Usage:** The input consists of a single number `n`. The output will be of the form:
```
p: n1 n2 ... nm
v11 ... v1k
...
vk1 ... vkl
```
Where `p` is the prime number, `ni` are primitive roots and `vij` are the elements of the Vandermonde matrix.
