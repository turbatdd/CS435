package lab2;

import java.util.Arrays;

/*

Algorithm findThirdMax(A, n)
    Input array A of n integers
    Output 3rd maximum element of A

    max1 ← -∞
    max2 ← -∞
    max3 ← -∞
    maxUsedIndex[0..1]

    FOR i ← 0 TO n-1 DO
        IF A[i] >= max1 THEN
            max1 ← A[i]
            maxUsedIndex[0] ← i
        END IF
    END FOR

    FOR i ← 0 TO n-1 DO
        IF i ≠ maxUsedIndex[0] THEN
            IF A[i] > max2 THEN
                max2 ← A[i]
                maxUsedIndex[1] ← i
        END IF
    END FOR

    FOR i ← 0 TO n-1 DO
        IF i ≠ maxUsedIndex[0] AND i ≠ maxUsedIndex[1] THEN
            IF A[i] > max3 THEN
                max3 ← A[i]
        END IF
    END FOR

    RETURN max3


3 full scans of array. Each scan O(n)
T(n) = 3n → Θ(n)


Algorithm findPrepremax(A, n)
    Input array A of n integers
    Output prepremax element of A

    max ← -∞
    preMax ← -∞
    prepreMax ← -∞

    FOR i ← 0 TO n-1 DO
        val ← A[i]

        IF val >= max THEN
            prepreMax ← preMax
            preMax ← max
            max ← A[i]
        ELSE IF val >= preMax THEN
            prepreMax ← preMax
            preMax ← A[i]
        ELSE IF val >= prepreMax THEN
            prepreMax ← A[i]
        END IF
    END FOR

    RETURN prepreMax

                Best case      Average case      Worst case
Algorithm 1     Θ(n)           Θ(n)              Θ(n)
Algorithm 2     Θ(n)           Θ(n)              Θ(n)
Algorithm 3     Θ(n log n)     Θ(n log n)        Θ(n log n)


ALGORITHM ThirdMax_TreeMap(A)
Create ordered dictionary M
FOR each element x in A DO
    insert x into M

count ← 0
FOR each key in M in descending order DO
    max ← key
    count ← count + 1
    IF count == 3 THEN
        RETURN max

 */
public class Day3 {
    public static void main(String[] args) {

        int[][] arr1 = sortedSquare(5);
        System.out.println("\n");
        printMatrix(arr1);

        int[][] arr2 = sortedSquare2(5);
        System.out.println("\n");
        printMatrix(arr2);

        int[][] arr3 = sortedSquare3(5);
        System.out.println("\n");
        printMatrix(arr3);


        searchSS(arr1, 18);
        searchSS(arr2, 18);
        searchSS(arr3, 30);

        int[] result = DACsearchSS(arr1, 7);

        if (result[0] != -1) {
            System.out.println("Key " + 7 + " found at Row: " + result[0] + ", Col: " + result[1]);
        } else {
            System.out.println("Key not found.");
        }
    }
//
//    public static void main(String[] args) {
//        int[][] matrix = {
//                {5,  6,  7,  8,  9},
//                {10, 11, 12, 13, 14},
//                {15, 16, 17, 18, 19},
//                {20, 21, 22, 23, 24},
//                {25, 26, 27, 28, 29}
//        };
//
//        int key = 23;
//        int[] result = DACsearchSS(matrix, key);
//
//        if (result[0] != -1) {
//            System.out.println("Key " + key + " found at Row: " + result[0] + ", Col: " + result[1]);
//        } else {
//            System.out.println("Key not found.");
//        }
//    }


    public static int[] DACsearchSS(int[][] M, int key) {
        if (M == null || M.length == 0) return new int[]{-1, -1};
        return recursiveSearch(M, key, 0, 0, M.length - 1, M[0].length - 1);
    }

    private static int[] recursiveSearch(int[][] M, int key, int r1, int c1, int r2, int c2) {
        if (r1 > r2 || c1 > c2) {
            return new int[]{-1, -1};
        }

        int midRow = r1 + (r2 - r1) / 2;
        int midCol = c1 + (c2 - c1) / 2;

        if (M[midRow][midCol] == key) {
            return new int[]{midRow, midCol};
        }

        if (M[midRow][midCol] > key) {
            int[] res = recursiveSearch(M, key, r1, c1, midRow - 1, c2);
            if (res[0] != -1) return res;

            return recursiveSearch(M, key, midRow, c1, r2, midCol - 1);
        } else {
            int[] res = recursiveSearch(M, key, midRow + 1, c1, r2, c2);
            if (res[0] != -1) return res;

            return recursiveSearch(M, key, r1, midCol + 1, midRow, c2);
        }
    }

    public static int[][] sortedSquare(int n) {
        int[][] res = new int[n][n];
        int val = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = val++;
            }
        }
        return res;
    }

    public static int[][] sortedSquare2(int n) {
        int[][] res = new int[n][n];
        int num = 5;

        for (int s = 0; s <= 2 * (n - 1); s++) {
            if (s % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    int j = s - i;
                    if (j >= 0 && j < n) {
                        res[i][j] = num++;
                    }
                }
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    int j = s - i;
                    if (j >= 0 && j < n) {
                        res[i][j] = num++;
                    }
                }
            }
        }
        return res;
    }

    public static int[][] sortedSquare3(int n) {
        int[][] res = new int[n][n];
        int val = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = val++;
            }
        }
        return res;
    }

    public static void searchSS(int[][] M, int key) {

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == key) {
                    System.out.println(key + ": " + i + ", " + j);
                    return;
                }
            }
        }

        System.out.println(key + ": Not found");
    }


    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                // Using \t (tab) to keep the columns aligned
                System.out.print(matrix[i][j] + "\t");
            }
            // Move to the next line after each row
            System.out.println();
        }
    }


    public static int findThirdMax(int[] A) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int[] maxUsedIndex = new int[3];


        for (int i = 0; i < A.length; i++) {
            if (max1 <= A[i]) {
                max1 = A[i];
                maxUsedIndex[0] = i;
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (i != maxUsedIndex[0] && max2 < A[i]) {
                max2 = A[i];
                maxUsedIndex[1] = i;
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (i != maxUsedIndex[0] && i != maxUsedIndex[1] && A[i] > max3) max3 = A[i];
        }

        return max3;
    }

    public static int findPrePreMax(int[] A) {
        int max = Integer.MIN_VALUE;
        int preMax = Integer.MIN_VALUE;
        int prepremax = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            int val = A[i];

            if (val >= max) {
                prepremax = preMax;
                preMax = max;
                max = val;
            } else if (val >= preMax) {
                prepremax = preMax;
                preMax = val;
            } else if (val >= prepremax) {
                prepremax = val;
            }
        }

        return prepremax;
    }


}