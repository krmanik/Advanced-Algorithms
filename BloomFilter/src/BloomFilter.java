public class BloomFilter {
    private final int setSize = 10240;
    private final byte[] bitvector;

    public BloomFilter() {
        bitvector = new byte[setSize];
    }

    /**
     * add string to bloom
     *
     * @param url add url to bloom filter
     */
    void add(String url) {
        int[] hash = getHash(url);
        for (int i : hash) {
            bitvector[i] = 1;
        }
    }

    /**
     * search if given string is in bloom or not
     *
     * @param url search for given url in bloom
     * @return boolean, if exist true else false
     */
    boolean search(String url) {
        int[] hash = getHash(url);
        for (int i : hash) {
            if (bitvector[i] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Using two hash function to generate hash for given sting
     *
     * @param url get hash for given url, here array of two hash as index will be returned
     * @return index array where bit will be set to one
     */
    private int[] getHash(String url) {
        int[] hash = new int[2];
        GenerateHash generateHash = new GenerateHash();
        hash[0] = Math.abs(generateHash.MurmurHash(url) % setSize);
        hash[1] = Math.abs(generateHash.FNVHash(url) % setSize);
        return hash;
    }
}
