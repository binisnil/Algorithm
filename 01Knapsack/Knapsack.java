public class Knapsack {

    public static int maxprofit = 0;
    public static int numbest = 0;
    public static int bestset[] = new int[100];
    public static int include[] = new int[100];
    public int W = 16;
    public static int w[] = { 0, 2, 5, 10, 5 };
    public static int p[] = { 0, 40, 30, 50, 10 };
    public int Weight = 0;
    public int Profit = 0;

    boolean promising(int i) {

        int j, k, totweight;
        float bound;

        if (Weight >= W)
            return false;

        else {
            j = i + 1;
            bound = Profit;
            totweight = Weight;

            while ((j <= 4) && ((totweight + w[j]) <= W)) {
                totweight = totweight + w[j];
                bound = bound + p[j];
                j++;
            }
            k = j;

            if (k <= 4) {
                bound = bound + (W - totweight) * (p[k] / w[k]);
            }
            return (bound > maxprofit);
        }
    }

    void knapsackBacktracing(int i, int profit, int weight) {

        Weight = weight;
        Profit = profit;

        if ((weight <= W) && (profit > maxprofit)) {
            maxprofit = profit;
            numbest = i;
            for (int k = 1; k <= i; k++) {
                bestset[k] = include[k];
            }
        }

        if (promising(i)) {
            include[i + 1] = 1;
            knapsackBacktracing(i + 1, profit + p[i + 1], weight + w[i + 1]);
            include[i + 1] = 0;
            knapsackBacktracing(i + 1, profit, weight);

        }
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();

        knapsack.knapsackBacktracing(0, 0, 0);
        for (int i = 1; i <= numbest; i++) {
            System.out.println(bestset[i]);
        }
        System.out.println("\nmaxprofit=" + maxprofit);
    }

}
