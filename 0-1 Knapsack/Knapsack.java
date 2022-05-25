import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int W, n;
        int w, v;
        Map<Integer, Integer> candidates = new TreeMap<Integer, Integer>();

        W = sc.nextInt();
        n = sc.nextInt();

        candidates.put(0, 0);

        System.out.println("0: (0, 0)");

        for (int i = 0; i < n; i++) {
            w = sc.nextInt();
            v = sc.nextInt();

            Iterator<Map.Entry<Integer, Integer>> iter = candidates.entrySet().iterator();
            Map<Integer, Integer> tmpMap = new TreeMap<Integer, Integer>();

            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entry = iter.next();
                int newV = entry.getValue() + v;
                int newW = entry.getKey() + w;

                if (newW > W)
                    continue;

                if (candidates.get(newW) == null || newV >= candidates.get(newW).intValue()) {
                    tmpMap.put(newW, newV);
                }
            }

            candidates.putAll(tmpMap);

            iter = candidates.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entry = iter.next();

                int currW = entry.getKey();
                int currV = entry.getValue();
                for (int j = 0; j < currW; j++) {
                    Integer cmpV = candidates.get(j);
                    if (cmpV != null && cmpV.intValue() >= currV) {
                        iter.remove();
                        break;
                    }
                }
            }

            boolean first = true;

            System.out.print((i + 1) + ": ");

            for (Map.Entry<Integer, Integer> e : candidates.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    System.out.print(" ");
                }
                System.out.print("(" +e.getKey() + ", " + e.getValue() + ")");
            }
            System.out.println();
        }

    }

}