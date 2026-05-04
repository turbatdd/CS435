package lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Main {
    public static void main(String[] args) {

        int[] arr = new int[]{7, 20, 18, 4, 20, 19, 20, 3};
        int res = findThirdMax(arr);

        System.out.println("Int[] is: " + Arrays.toString(arr));
        System.out.println("Third max is: " + res);

        int prepremax = findPrePreMax(arr);

        System.out.println("prepremax is: " + prepremax);
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