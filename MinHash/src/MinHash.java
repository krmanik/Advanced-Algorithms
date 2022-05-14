import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MinHash {
    private final int numHash;
    private int[][] hash;

    public MinHash(int numHash) {
        this.numHash = numHash;
    }

    private static int[][] initHashBuckets(int numSets, int numHashFunctions) {
        int[][] minHashValues = new int[numSets][numHashFunctions];
        for (int i = 0; i < numSets; i++) {
            for (int j = 0; j < numHashFunctions; j++) {
                minHashValues[i][j] = Integer.MAX_VALUE;
            }
        }
        return minHashValues;
    }

    private static double calculateSimilarityFromSignatures(int[][] minHashValues, int numHashFunctions) {
        int identicalMinHashes = 0;
        for (int i = 0; i < numHashFunctions; i++) {
            if (minHashValues[0][i] == minHashValues[1][i]) {
                identicalMinHashes++;
            }
        }
        return (1.0 * identicalMinHashes) / numHashFunctions;
    }

    private static int hash(int x, int a, int b, int c) {
        int hashValue = (a * (x >> 4) + b * x + c) & 131071;
        return Math.abs(hashValue);
    }

    public double similarity(Set<String> set1, Set<String> set2) {
        int numSets = 2;
        Map<String, boolean[]> bitMap = buildBitMap(set1, set2);
        hash = new int[bitMap.size()][numHash];

        Random random = new Random(11);

        for (int j = 0; j < bitMap.size(); j++) {
            for (int i = 0; i < numHash; i++) {
                int a = random.nextInt();
                int b = random.nextInt();
                int c = random.nextInt();
                int x = hash(a * b * c, a, b, c);
                hash[j][i] = x;
            }
        }

        int[][] minHashValues = initHashBuckets(numSets, numHash);
        calculateMinHashForSet(set1, 0, minHashValues, bitMap);
        calculateMinHashForSet(set2, 1, minHashValues, bitMap);
        return calculateSimilarityFromSignatures(minHashValues, numHash);
    }

    private void calculateMinHashForSet(Set<String> set, int setIndex, int[][] minHashValues, Map<String, boolean[]> bitArray) {
        int index = 0;
        for (String element : bitArray.keySet()) {
            if (set.contains(element)) {
                for (int i = 0; i < numHash; i++) {
                    int hindex = hash[index][i];
                    if (hindex < minHashValues[setIndex][i]) {
                        minHashValues[setIndex][i] = hindex;
                    }
                }
            }
            index++;
        }
    }

    public Map<String, boolean[]> buildBitMap(Set<String> set1, Set<String> set2) {
        Map<String, boolean[]> bitArray = new HashMap<>();

        for (String s : set1) {
            bitArray.put(s, new boolean[]{true, false});
        }

        for (String s : set2) {
            if (bitArray.containsKey(s)) {
                bitArray.put(s, new boolean[]{true, true});
            } else if (!bitArray.containsKey(s)) {
                bitArray.put(s, new boolean[]{false, true});
            }
        }
        return bitArray;
    }
}




