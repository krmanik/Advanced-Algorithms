import java.util.ArrayList;

import static java.util.Collections.swap;

public class BetterQuickSort {
    public BetterQuickSort(ArrayList<Integer> elements, int p, int r) {
        betterQuickSort(elements, p, r);
    }

    /**
     * Sorting using 3-way partitioning
     * @param elements random list of numbers
     * @param p lower limit
     * @param r upper limit
     */
    private static void betterQuickSort(ArrayList<Integer> elements, int p, int r) {
        if ( r <= p) {
            return;
        }
        int lt = p, gt = r;
        int i = p + 1;
        int x = elements.get(p);

        while (i <= gt) {
            if (elements.get(i) < x) {
              swap(elements, lt++, i++);
            } else if (elements.get(i) > x) {
                swap(elements, i, gt--);
            } else {
                i++;
            }
        }

        betterQuickSort(elements, p, lt - 1);
        betterQuickSort(elements, gt + 1, r);
    }
}
