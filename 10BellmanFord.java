import java.util.*;

class Edge {
    int u, v, w;
    Edge(int a, int b, int c) {
        u = a;
        v = b;
        w = c;
    }
}

public class BellmanFord {
    static final int INF = Integer.MAX_VALUE;

    static void runBellmanFord(int n, List<Edge> E, int src) {
        int[] d = new int[n];
        Arrays.fill(d, INF);
        d[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge e : E) {
                if (d[e.u] != INF && d[e.u] + e.w < d[e.v]) {
                    d[e.v] = d[e.u] + e.w;
                }
            }
        }

        for (Edge e : E) {
            if (d[e.u] != INF && d[e.u] + e.w < d[e.v]) {
                System.out.println("Negative weight cycle found");
                return;
            }
        }

        System.out.println("Node\tDist");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + (d[i] == INF ? "INF" : d[i]));
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int m = sc.nextInt();

        List<Edge> E = new ArrayList<>();
        System.out.println("Enter edges in format (u v w):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            E.add(new Edge(u, v, w));
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        runBellmanFord(n, E, src);
    }
}
