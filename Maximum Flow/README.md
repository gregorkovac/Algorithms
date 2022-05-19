# Ford-Fulkerson Algorithm (Maximum flow problem)

**Description:** This program takes the description of a graph and finds the maximum flow using the Ford-Fulkerson algorithm.

**Usage:** The first line of input contains the number `n`, which is the number of nodes. The second line contains `m`, which is the number of edges. The following `m` lines contain edge descriptions of the form:

```
node_1 node_2 capacity
```
The values above should all be numbers and `node_1` and `node_2` should be between `0` and `n-1`. The program will then run the algorithm and output the augmented edges at each step. The maximum flow will be printed in the last line.