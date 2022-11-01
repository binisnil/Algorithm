import java.util.Scanner;

class Floyd2 {

    void floyd2(int num, int W[][], int D[][], int P[][]) {
        int i, j, k;
        for (i = 0; i < num; i++) {
            for (j = 0; j < num; j++) {
                P[i][j] = 0; // P 배열 내부를 0으로 초기화
                D[i][j] = W[i][j]; // D는 W로 초기화
            }
        }
        for (k = 0; k < num; k++) {
            for (i = 0; i < num; i++) {
                for (j = 0; j < num; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        P[i][j] = k + 1; // index 번호가 0 부터 시작이므로 1씩 더해주기
                        D[i][j] = D[i][k] + D[k][j]; // 최단거리 저장
                    }
                }
            }
        }
    }

    void path(int vertex1, int vertex2, int P[][]) {
        if (P[--vertex1][--vertex2] != 0) { // 인덱스 번호 고려해야함. 매개변수 번호는 1부터, 배열 인덱스는 0부터 이므로!
            path(vertex1 + 1, P[vertex1][vertex2], P); // P의 매개변수가 아닌 vertex는 1부터 시작해야하므로, 앞에서 --된 값을 다시 +1 시켜줌
            System.out.print(" V" + P[vertex1][vertex2] + " ->");
            path(P[vertex1][vertex2], vertex2 + 1, P);
        }

    }
};

class Floyd {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Floyd2 floyd = new Floyd2();

        int num = 5;

        // int W[][] = { { 0, 1, 999, 1, 5 }, { 9, 0, 3, 2, 999 }, { 999, 999, 0, 4, 999
        // }, { 999, 999, 2, 0, 3 },
        // { 3, 999, 999, 999, 0 } }; // 예제 데이터
        int W[][] = { { 0, 5, 10, 999, 2 }, { 999, 0, 999, 3, 9 }, { 999, 6, 0, 999,
                999 }, { 4, 999, 1, 0, 999 },
                { 999, 999, 999, 8, 0 } }; // 자작 데이터
        int D[][] = new int[num][num];
        int P[][] = new int[num][num];

        floyd.floyd2(num, W, D, P);

        System.out.println("최단거리의 길이입니다.");
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(" " + D[i][j]);
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        System.out.print("Vi와 Vj 사이에 놓여있는 정점 중, 가장 큰 인덱스번호를 가진 정점입니다.\n그 중간에 놓여있는 정점이 없으면, 0이 출력됩니다.\n");
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(" " + P[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.print("정점 두개를 입력하세요:");
        int vertex1 = scan.nextInt();
        int vertex2 = scan.nextInt();

        System.out.println("");
        System.out.println("V" + vertex1 + "과 V" + vertex2 + "의 최단경로입니다.");
        System.out.print(" V" + vertex1 + " ->");
        floyd.path(vertex1, vertex2, P);
        System.out.print(" V" + vertex2);

        scan.close();
    }
}
