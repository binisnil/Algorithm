class Dijkstra_Algo {

    static final int INF = 9999999;

    void loadDijkstra() {

        int n = 5;
        int min;
        int touch[] = new int[5];
        int length[] = new int[5];
        int vnear = 0;
        int F[][] = new int[2][5];
        F[0][0] = 0;
        F[1][0] = 0;

        int W[][] = { { 0, 7, 4, 6, 1 }, { INF, 0, INF, INF, INF },
                { INF, 2, 0, 5, INF },
                { INF, 3, INF, 0, INF },
                { INF, INF, INF, 1, 0 } };

        for (int i = 0; i < n; i++) {
            touch[i] = 1;
            length[i] = W[0][i];
        }
        touch[0] = 0;

        for (int i = 1; i < n; i++) {
            min = 999;

            for (int j = 1; j < n; j++) {

                if ((length[j] > -1) && (length[j] < min)) {

                    min = length[j];
                    vnear = j;
                }

                F[0][i] = touch[vnear];
                F[1][i] = vnear + 1;

            }

            System.out.print("min: " + min);
            System.out.println(" vnear: " + vnear);
            System.out.print("\n");

            for (int k = 1; k < n; k++) {

                if ((length[vnear] + W[vnear][k]) < length[k]) {

                    length[k] = length[vnear] + W[vnear][k];
                    touch[k] = vnear + 1;

                }
            }
            length[vnear] = -1;

            for (int j = 0; j < n; j++) {
                System.out.print(" " + length[j]);
            }

            System.out.print("\n");

            for (int j = 1; j < n; j++) {
                System.out.print(" " + touch[j]);
            }
            System.out.print("\n");

            System.out.print("=======\n");

        }
        System.out.print("\n=======\n");
        System.out.print("\n");
        System.out.print("F 출력");
        System.out.print("\n\n");
        for (int i = 1; i < n; i++) {
            System.out.print(" v" + F[0][i]);
            System.out.println(" v" + F[1][i]);
            System.out.print("=======\n\n");
        }
    }

}

public class tempCodeRunnerFile {
    public static void main(String[] args) {
        Dijkstra_Algo dijkstra_Algo = new Dijkstra_Algo();
        dijkstra_Algo.loadDijkstra();
    }

}