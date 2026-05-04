package lab4;

public class Stairs {

    public static void main(String[] args) {
        int n = 5;
        int res = numberOfWays(5);

        System.out.println("Number of ways " + n + ": " + res);
    }

    public static int numberOfWays(int n) {
        if (n <= 1)
            return 1;

        int cur = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 2; i <= n; i++) {
            cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }

        return cur;
    }
}
