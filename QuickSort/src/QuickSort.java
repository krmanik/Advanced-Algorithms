import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.swap;

public class QuickSort {

    public QuickSort(ArrayList<Integer> elements, int p, int r) {
        quickSort(elements, p, r);
    }

    private static int randPartition(ArrayList<Integer> elements, int p, int r) {
        Random rand = new Random();
        int i = rand.nextInt(r - p) + p;
        swap(elements, i, r);

        int x = elements.get(r);
        i = p - 1;

        for (int j = p; j < r; j++) {
            if (elements.get(j) <= x) {
                i++;
                swap(elements, i, j);
            }
        }

        swap(elements, i + 1, r);
        return i + 1;
    }

    private static void quickSort(ArrayList<Integer> elements, int p, int r) {
        if (p >= r) {
            return;
        }
        if (p < 0) {
            return;
        }
        if (r > elements.size() - 1) {
            return;
        }

        int q = randPartition(elements, p, r);
        quickSort(elements, p, q - 1);
        quickSort(elements, q + 1, r);
    }
}
