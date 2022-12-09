public class Knapsack {

    public static int maxprofit = 0;
    public static int numbest = 0;
    public static int bestset[] = new int[100];
    public static int include[] = new int[100];
    // 예제 데이터
    public int W = 16;
    public static int w[] = { 0, 2, 5, 10, 5 };
    public static int p[] = { 0, 60, 50, 50, 5 };
    // 자작데이터
    // public int W = 16;
    // public static int w[] = { 0, 2, 5, 10, 5 };
    // public static int p[] = { 0, 60, 50, 50, 5 };

    boolean promising(int i, int profit, int weight, int maxprofit) {

        int j, k, totweight;
        float bound;

        if (weight >= W) {
            System.out.println("x");
            System.out.println("=====================");

            return false;
        }

        else {
            j = i + 1;
            bound = profit;
            totweight = weight;
            System.out.println("profit: " + profit);
            System.out.println("weight: " + weight);

            while ((j <= 4) && ((totweight + w[j]) <= W)) {
                totweight = totweight + w[j];
                bound = bound + p[j];
                j++;
            }
            k = j;
            if (k <= 4) {
                bound = bound + (W - totweight) * (p[k] / w[k]);
            }
            System.out.println("bound" + bound);
            System.out.println("=====================");

            return (bound > maxprofit);
        }
    }

    void knapsackBacktracing(int i, int profit, int weight) {

        if ((weight <= W) && (profit > maxprofit)) {
            maxprofit = profit;
            numbest = i;
            for (int k = 1; k <= i; k++) {
                bestset[k] = include[k];
            }
            System.out.println("maxprofit: " + maxprofit);
        }

        if (promising(i, profit, weight, maxprofit)) {
            include[i + 1] = 1;
            System.out.println((i + 1));
            knapsackBacktracing(i + 1, profit + p[i + 1], weight + w[i + 1]);
            include[i + 1] = 0;
            System.out.println((i + 1));
            knapsackBacktracing(i + 1, profit, weight);

        }
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();

        knapsack.knapsackBacktracing(0, 0, 0);
        for (int i = 1; i <= numbest; i++) {
            System.out.println(i + ": " + bestset[i]);
        }
        System.out.println("\nmaxprofit=" + maxprofit);
    }

}
