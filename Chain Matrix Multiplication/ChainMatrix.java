class ChainMM {
    int minmult(int n, int d[], int P[][], int M[][]) { // n=행렬 개수, d=각 행렬의 열 수, P=최소 경로 담을 배열
        int i, j, k, diagonal;

        for (int p = 0; p < n; p++) {
            for (int q = 0; q < n; q++) {
                M[p][q] = 999;
            }
        }

        for (i = 0; i < n; i++) {
            M[i][i] = 0; // 0으로 초기화
        }

        for (diagonal = 0; diagonal < n; diagonal++) {
            for (i = 0; i < n - diagonal; i++) {
                j = i + diagonal;
                for (k = i; k < j; k++) {
                    if (M[i][j] > M[i][k] + M[k + 1][j] + d[i] * d[k + 1] * d[j + 1]) {
                        M[i][j] = M[i][k] + M[k + 1][j] + d[i] * d[k + 1] * d[j + 1];
                        P[i][j] = k + 1;
                    }
                }
            }
        }
        return M[0][n - 1];
    }

    static void order(int i, int j, int P[][]) {
        if (i == j) {
            System.out.println(i + "==" + j + "->true");
            System.out.println("A" + (i + 1));
        } else {
            System.out.println(i + "==" + j + "->false\n\telse");
            System.out.println("\tk=P[" + i + "][" + j + "]");
            int k = P[i][j];
            System.out.print("(\n");
            System.out.println("\torder(" + i + "," + (k - 1) + ")");
            order(i, k - 1, P);
            System.out.println("\torder(" + k + "," + j + ")");
            order(k, j, P);
            System.out.print(")");
        }
    }
}

class ChainMatrix {
    public static void main(String[] args) {
        ChainMM chainMM = new ChainMM();
        // int d[] = { 5, 2, 3, 4, 6, 7, 8 }; // 6개의 행렬 정의-예제 데이터
        int d[] = { 2, 4, 3, 5, 9, 6, 4 };
        int n = 6;
        int P[][] = new int[n][n];
        int M[][] = new int[n][n];

        System.out.print("행렬" + n + "개를 곱하는데 필요한 원소 단위 곱셈의 최소 횟수");
        System.out.println("\n" + chainMM.minmult(n, d, P, M));
        System.out.print("\nM\n");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n최적 순서 출력");
        ChainMM.order(0, 5, P);

    }
}