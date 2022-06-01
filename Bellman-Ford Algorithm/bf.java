import java.util.*;

public class bf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        Node[] nodes;

        n = sc.nextInt();
        m = sc.nextInt();

        nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node(i);

        for (int i = 0; i < m; i++) {
            int a, b, c;
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            nodes[a].addConnection(nodes[b], c);
        }

        int[] lenCurr = new int[n], lenPrev = new int[n];
        Arrays.fill(lenCurr, Integer.MAX_VALUE);
        Arrays.fill(lenPrev, Integer.MAX_VALUE);
        lenCurr[0] = 0;

        System.out.print("h0: Inf");
        for (int j = 1; j < n; j++) {
            System.out.print(" Inf");
        }
        System.out.println();

        for (int i = 0; i < n - 1; i++) {
            boolean[] changed = new boolean[n];
            for (int j = 0; j < n; j++) {
                if (lenPrev[j] != lenCurr[j] && !changed[j]) {
                    for (int k = 0; k < nodes[j].next.size(); k++) {
                        int id = nodes[j].next.get(k).id;
                        if (lenCurr[j] + nodes[j].price.get(k) < lenCurr[id]) {
                            lenPrev[id] = lenCurr[id];
                            lenCurr[id] = lenCurr[j] + nodes[j].price.get(k);
                            changed[id] = true;
                        }
                    }
                    lenPrev[j] = lenCurr[j];
                }
            }

            System.out.print("h" + (i+1) + ":");
            for (int j = 0; j < n; j++) {
                if (lenCurr[j] == Integer.MAX_VALUE)
                    System.out.print(" Inf");
                else 
                    System.out.print(" " + lenCurr[j]);
            }
            System.out.println();
        }
    }
}

class Node {
    int id;
    LinkedList<Node> next;
    LinkedList<Integer> price;

    Node(int id) {
        this.id = id;
        next = new LinkedList<Node>();
        price = new LinkedList<Integer>();
    }

    void addConnection(Node n, int p) {
        next.add(n);
        price.add(p);
    }
}
