class OBST {

    double sum(int start, int fin, double p[]) {
        double Sum = 0;
        for (int i = start; i <= fin; i++) {
            Sum += p[i - 1];
        }
        return Sum;
    }

}

class BST {
    public static void main(String[] args) {
        OBST obst = new OBST();

        int i = 0, j = 0, k = 0, diagonal = 0, n = 4;
        // double p[] = { 0.375, 0.375, 0.125, 0.125 }; // 예제 데이터
        double p[] = { 0.9, 0.7, 0.3, 0.5 }; // 자작 데이터
        double A[][] = new double[n + 2][n + 2];
        int R[][] = new int[n + 2][n + 2];

        for (i = 0; i < 6; i++) {
            for (j = 0; j < 6; j++) {
                A[i][j] = 999;
                // R[i][j] = 999;
            }
        }

        for (i = 0; i < 4; i++) {
            A[i + 1][i] = 0;
            A[i + 1][i + 1] = p[i];
            R[i + 1][i + 1] = i + 1;
            R[i + 1][i] = 0;
        }
        A[5][4] = 0;
        R[5][4] = 0;

        for (diagonal = 1; diagonal < 4; diagonal++) {
            for (i = 1; i <= 4 - diagonal; i++) {
                j = i + diagonal;
                for (k = i; k <= j; k++) {
                    if (A[i][j] > A[i][k - 1] + A[k + 1][j] + obst.sum(i, j, p)) {
                        A[i][j] = A[i][k - 1] + A[k + 1][j] + obst.sum(i, j, p);
                        R[i][j] = k;
                    }
                }
            }
        }
        System.out.println("A\n");
        for (i = 1; i < n + 2; i++) {
            for (j = 0; j < n + 1; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.print("\n\n");
        }
        System.out.print("\n");
        System.out.println("R\n");
        for (i = 1; i < n + 2; i++) {
            for (j = 0; j < n + 1; j++) {
                System.out.print(R[i][j] + " ");
            }
            System.out.print("\n\n");
        }

    }
}