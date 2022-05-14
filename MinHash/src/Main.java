import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Main {
    public static void main(String[] args) throws IOException {
        String fileName1 = "assets/E1_AOL-out.txt";
        String fileName2 = "assets/E1_Booking-out.txt";
        String fileName3 = "assets/E1_kosarak_100k.txt";

        getMinHash(fileName1);
        getMinHash(fileName2);
        getMinHash(fileName3);
    }

    public static void getMinHash(String fileName) throws IOException {
        long startReadTime = System.currentTimeMillis();

        System.out.format("\nReading %s file...\n", fileName);
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t", 2);
                set1.add(split[0]);
                set2.add(split[1]);
            }
        }

        long endReadTime = System.currentTimeMillis();
        float readTimeSeconds = ((float)(endReadTime - startReadTime) / 1000) % 60;
        System.out.format("Time taken for reading %s file %fs\n", fileName, readTimeSeconds);

        // similarity using minhash
        System.out.format("Calculating similarity using minhash...\n");
        long startMinHashTime = System.currentTimeMillis();
        MinHash minHash = new MinHash(256);
        double minhashSimilarity = minHash.similarity(set1, set2);
        long endMinHashCalculateTime = System.currentTimeMillis();
        float minHashTimeSeconds = ((float)(endMinHashCalculateTime - startMinHashTime) / 1000) % 60;

        // similarity using naive algorithm
        System.out.format("Calculating similarity using naive algorithm...\n");
        long startNaiveTime = System.currentTimeMillis();
        double naiveSimilarity = new Naive().similarity(set1, set2);
        long endNaiveTime = System.currentTimeMillis();
        float naiveTimeSeconds = ((float)(endNaiveTime - startNaiveTime) / 1000) % 60;

        System.out.format("%s %30s %24s", "File Name", "Naive(Time)", "MinHash(Time)\n");
        System.out.format("%s %16f %18f\n", fileName, naiveTimeSeconds, minHashTimeSeconds);

        System.out.format("%s %36s %24s", "File Name", "Naive(Similarity)", "MinHash(Similarity)\n");
        System.out.format("%s %16f %18f\n", fileName, naiveSimilarity, minhashSimilarity);
    }
}