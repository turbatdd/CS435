package lab4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] S = {3, 4, 7, 8};
        int k = 15;

        boolean s = findHasSolution(S, 0, k);
        System.out.println("Has solution: " + s);
        List<Integer> result1 = new ArrayList();

        findOneSolution(S, S.length, k, result1);
        System.out.println("Has one solution: " + result1);


        List<List<Integer>> results = new ArrayList();
        findManySolution(S, 0, k, new ArrayList<>(), results);

        System.out.println("Many solutions: " + results);
    }

    public static boolean findHasSolution(int[] S, int idx, int k) {
        if (k == 0)
            return true;

        if (idx >= S.length || k < 0)
            return false;
        return findHasSolution(S, idx + 1, k - S[idx]) || findHasSolution(S, idx + 1, k);
    }

    public static boolean findOneSolution(int[] S, int idx, int k, List<Integer> curr) {
        if (k == 0)
            return true;
        if (idx == 0 || k < 0)
            return false;

        curr.add(S[idx - 1]);
        if (findOneSolution(S, idx - 1, k - S[idx - 1], curr)) {
            return true;
        }

        curr.remove(curr.size() - 1);

        return findOneSolution(S, idx - 1, k, curr);
    }

    public static void findManySolution(int[] S, int idx, int k, List<Integer> curr, List<List<Integer>> results) {
        if (k == 0) {
            results.add(new ArrayList<>(curr));
            return;
        }
        if (idx >= S.length || idx < 0) {
//            System.out.println("                  E idx:" + idx + " -k:-" + k);
            return;
        }
        curr.add(S[idx]);
//        System.out.println(curr + " ---1");
        findManySolution(S, idx + 1, k - S[idx], curr, results);

        curr.remove(curr.size() - 1);
//        System.out.println(curr + " ---22");

        findManySolution(S, idx + 1, k, curr, results);

    }
}
