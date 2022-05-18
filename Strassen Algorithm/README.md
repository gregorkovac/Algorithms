# Strassen algorithm

**Description:** This algorithm multiplies two matrices in a manner that is faster than standard matrix multiplication.

**Usage:** In the first row enter numbers `n` and `cutoff`. `n` is the matrix dimension and `cuttoff` is the dimension at which the algorithm should stop using Strassen and multiply the matrices normally. The first row should be followed by `2*n` rows of `n` numbers of the following form:

```
a11 a12 ... a1n
a21 a22 ... a2n
...
an1 an2 ... ann
b11 b12 ... b1n
b21 b22 ... b2n
...
bn1 bn2 ... bnn
```
Numbers `aij` represent the elements of the first matrix and numbers `bij` represent the elements of the second matrix. The program's output will be of the following form:

```
c11 c12 ... c1n
c21 c22 ... c2n
...
cn1 cn2 ... cnn
```
Numbers `cij` represent the product of the matrices.