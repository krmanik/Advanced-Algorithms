import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // String fileName = "assets/top-500-domains.txt";
        // String fileName = "assets/top-100k-domains.txt";
        String fileName = "assets/top-1m-domains.txt";

        // created two filter, the top website will be added for filter i.e. blocklist
        NaiveFilter naiveFilter = new NaiveFilter();
        BloomFilter bloomFilter = new BloomFilter();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                naiveFilter.add(line);
                bloomFilter.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Filename: " + fileName);
        // Naive Filter
        // ------------
        // adding new website to naive filter
        long startNaiveTime = System.nanoTime();
        naiveFilter.add("www.example.com");
        long endNaiveTime = System.nanoTime();
        long naiveAddTimeSeconds = endNaiveTime - startNaiveTime;

        // search website in naive filter
        startNaiveTime = System.nanoTime();
        boolean existInNaive = naiveFilter.search("www.google.com");
        endNaiveTime = System.nanoTime();
        long naiveSearchTimeSeconds = endNaiveTime - startNaiveTime;
        System.out.println("Website exist in naive filter: " + existInNaive);

        // Bloom Filter
        // ------------
        // adding new website to bloom filter
        long startBloomTime = System.nanoTime();
        bloomFilter.add("www.example.com");
        long endBloomTime = System.nanoTime();
        long bloomAddTimeSeconds = endBloomTime - startBloomTime;

        // search website in bloom filter
        startBloomTime = System.nanoTime();
        boolean existInBloom = bloomFilter.search("www.google.com");
        endBloomTime = System.nanoTime();
        long bloomSearchTimeSeconds = endBloomTime - startBloomTime;
        System.out.println("Website exist in bloom filter: " + existInBloom);

        System.out.format("\n%s %20s %18s", "Method", "Naive Filter", "Bloom Filter\n");
        System.out.format("%s %19d %16d\n", "Add", naiveAddTimeSeconds, bloomAddTimeSeconds);
        System.out.format("%s %16d %16d\n", "Search", naiveSearchTimeSeconds, bloomSearchTimeSeconds);
        System.out.println("\nNote: Time is in nano seconds");
    }
}
