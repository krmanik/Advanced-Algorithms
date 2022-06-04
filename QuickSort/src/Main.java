import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    static DefaultCategoryDataset datasetListSizeSortTime = new DefaultCategoryDataset();

    public static void main(String[] args) {
        int n = 20;
        // for elements with repeat rate 0% to 100%
        for (int rate = 0; rate <= 100; rate += 10) {
            generateSortPrintData(n, rate);
        }

        SwingUtilities.invokeLater(() -> {
            LineChart lineChart1 = new LineChart("time - rate", "rate", "time", datasetListSizeSortTime);
            lineChart1.plotLineChart();
        });
    }

    /**
     * Generate and sort list with N and repeat rate
     * @param n size of list to generate random list of numbers
     * @param rate repeat rate
     */
    public static void generateSortPrintData(int n, int rate) {
        System.out.format("\nGenerating and sorting list with [N = %d] and [rate = %d]\n", n, rate);

        // start time of generating data sets
        long startGenTime = System.nanoTime();
        // generating datasets with N and repeat rate
        ArrayList<Integer> list = generateData(n, rate);
        ArrayList<Integer> copyList = new ArrayList<>(list);
        int[] copyList2 = list.stream().mapToInt(Integer::intValue).toArray();
        ArrayList<Integer> copyList3 = new ArrayList<>(list);

        long endGenTime = System.nanoTime();
        float genTime = (float) (endGenTime - startGenTime) / 1000000;

        // start time of sorting
        long startSortTime = System.nanoTime();
        // sorting data sets
        new QuickSort(list, 0, list.size() - 1);
        long endSortTime = System.nanoTime();
        float sortTime = (float) (endSortTime - startSortTime) / 1000000;

        // sorting the list with inbuilt library
        long startInbuiltSortTime = System.nanoTime();
        // Collections.sort(copyList2);
        Arrays.sort(copyList2);
        long endInbuiltSortTime = System.nanoTime();
        float inbuiltSortTime = (float) (endInbuiltSortTime - startInbuiltSortTime) / 1000000;

        // sorting the list with better quick sort
        long startBetterSortTime = System.nanoTime();
        new BetterQuickSort(copyList3, 0, list.size() - 1);
        long endBetterSortTime = System.nanoTime();
        float betterSortTime = (float) (endBetterSortTime - startBetterSortTime) / 1000000;

        // print results
        System.out.println("[Unsorted random list]");
        System.out.println(copyList);
        System.out.println("[Sorted list]");
        System.out.println(list);

        System.out.format("[Time taken for generating datasets]: %f ms\n", genTime);
        System.out.format("[Time taken for sorting using QuickSort]: %f ms\n", sortTime);
        System.out.format("[Time taken for sorting using Inbuilt sort]: %f ms\n", inbuiltSortTime);
        System.out.format("[Time taken for sorting using better sort]: %f ms\n", betterSortTime);

        datasetListSizeSortTime.addValue(genTime, "generate time", String.valueOf(rate));
        datasetListSizeSortTime.addValue(sortTime, "quicksort time", String.valueOf(rate));
        datasetListSizeSortTime.addValue(inbuiltSortTime, "inbuilt sort", String.valueOf(rate));
        datasetListSizeSortTime.addValue(betterSortTime, "better sort", String.valueOf(rate));
    }

    /**
     * Generate list with N and repeat rate
     * @param n list size
     * @param rate repeat rate
     * @return random list of numbers
     */
    public static ArrayList<Integer> generateData(int n, int rate) {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();

        if (rate == 100) {
            int num = random.nextInt(n);
            for (int i = 0; i < n; i++) {
                list.add(num);
            }
            return list;
        }

        if (rate == 0) {
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
            Collections.shuffle(list);
            return list;
        }

        int m = (int) (n * 0.01 * rate);

        // not repeat
        for (int i = 0; i < n - m; i++) {
            list.add(i);
        }

        // repeat
        int num = n - m;
        for (int i = 0; i < m; i++) {
            list.add(num);
        }

        Collections.shuffle(list);
        return list;
    }
}