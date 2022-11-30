import java.util.Arrays;
import java.util.Comparator;

class Kruskal {
    public static void main(String[] args) {
        int n = 5; // vertex의 개수
        // int m = 7; // edge의 개수
        int E[][] = {
                { 1, 2, 1 },
                { 1, 3, 3 },
                { 2, 3, 3 },
                { 2, 4, 6 },
                { 3, 4, 4 },
                { 3, 5, 2 },
                { 4, 5, 5 } }; // 예제 데이터

        // int E[][] = {
        // { 1, 2, 1 },
        // { 1, 3, 4 },
        // { 1, 4, 10 },
        // { 2, 3, 3 },
        // { 3, 4, 5 },
        // { 1, 5, 6 },
        // { 4, 5, 2 } }; // 자작데이터

        int F[][] = new int[4][3];
        int U[][] = new int[n + 1][n + 1]; // U[] = parent, U[][] = depth

        Disjoint disjoint = new Disjoint();

        Arrays.sort(E, new Comparator<int[]>() { // E 배열 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });

        disjoint.initial(U, n); // 집합 초기화

        int i, j, p, q;
        int index = 0;
        int count = 0;

        for (int k = 1; k < E.length; k++) {
            // edge에 선택되지 않은 간선의 정점들 저장
            i = E[index][0];
            j = E[index][1]; // 선택되지 않은 간선의 정점들 저장

            p = disjoint.find(U, i); // i가 속한 집합 찾아서 넣어줌
            q = disjoint.find(U, j); // j가 속한 집합 찾아서 넣어줌\
            if (!disjoint.equal(p, q)) { // 두 정점이 다른 집합에 속해있다면
                disjoint.merge(U, p, q); // 합쳐줌
                F[count] = E[index]; // F에 간선 추가
                count++;
            }
            index++;
        }

        for (int s = 0; s < count; s++) {
            System.out.print("V" + F[s][0]);
            System.out.print(" <-> V" + F[s][1]);
            System.out.print(" 의 가중치: " + F[s][2]);
            System.out.print("\n");

        }
    }
}

class Disjoint {
    void initial(int U[][], int n) {
        for (int i = 1; i <= n; i++) {
            U[i][0] = i;
            U[i][1] = 0;
        }
    }

    int find(int U[][], int i) {
        int j = i;
        while (U[j][0] != j) {
            j = U[j][0];
        }
        return j;
    }

    void merge(int U[][], int p, int q) {
        if (U[p][1] == U[q][1]) {
            U[p][1] += 1;
            U[q][0] = p;
        } else if (U[p][1] < U[q][1]) {
            U[p][0] = q;

        } else {
            U[q][0] = p;
        }
    }

    boolean equal(int p, int q) {
        if (p == q) {
            return true;
        } else
            return false;
    }

}
