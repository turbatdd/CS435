package lab5;

import java.util.Arrays;

public class WoodenBlock {

    public static void main(String[] args) {
        String[] A2 = {"red", "blue", "blue", "blue", "red", "blue", "red", "red", "blue"};

        sortTwoColor(A2);
        System.out.println(Arrays.toString(A2));

        String[] A3 = {"red", "blue", "green", "blue", "red", "green", "red", "red", "green"};
        sortThreeColor(A3);
        System.out.println(Arrays.toString(A3));

        String[] A4 = {"red", "yellow", "green", "blue", "red", "green", "yellow", "red", "green"};
        sortFourColor(A4);
        System.out.println(Arrays.toString(A4));
    }

    public static void sortTwoColor(String[] A) {
        int idxRed = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i].trim().toLowerCase().equals("red")) {
                swap(A, i, idxRed);
                idxRed++;
            }
        }
    }

    public static void sortThreeColor(String[] A) {
        int low = 0;
        int mid = 0;
        int high = A.length - 1;

        while (mid <= high) {
            String val = A[mid].trim().toLowerCase();

            if (val.equals("blue")) {
                swap(A, low, mid);
                low++;
                mid++;
            } else if (val.equals("red")) {
                mid++;
            } else if (val.equals("green")) {
                swap(A, mid, high);
                high--;
            }
        }
    }

    public static void sortFourColor(String[] A) {

        int n = A.length;
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (A[i].equals("blue")) {
                swap(A, i, start++);
            }
        }

        int end = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (A[i].equals("red")) {
                swap(A, i, end--);
            }
        }

        // Pass 3: Arrange Red and Green in the middle
        int midStart = start;
        int midEnd = end;

        while (midStart <= midEnd) {
            if (A[midStart].equals("yellow")) {
                midStart++;
            } else { // Green
                swap(A, midStart, midEnd);
                midEnd--;
            }
        }
    }

    private static void swap(String[] A, int i, int j) {
        String temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
