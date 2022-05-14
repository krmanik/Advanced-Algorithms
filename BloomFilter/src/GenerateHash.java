import java.nio.charset.StandardCharsets;

public class GenerateHash {
    /**
     * Generate Murmur hash for given string
     *
     * @param str
     * @return
     */
    int MurmurHash(String str) {
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        int length = str.length();
        int seed = 1234;
        int m = 0x5bd1e995;
        int r = 24;
        int h = seed ^ length;
        int len_4 = length >> 2;

        for (int i = 0; i < len_4; i++) {
            int i_4 = i << 2;
            int k = data[i_4 + 3];
            k = k << 8;
            k = k | (data[i_4 + 2] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 1] & 0xff);
            k = k << 8;
            k = k | (data[i_4 + 0] & 0xff);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        int len_m = len_4 << 2;
        int left = length - len_m;

        switch (left) {
            case 3:
                h ^= (int) data[length - 3] << 16;
                break;
            case 2:
                h ^= (int) data[length - 2] << 8;
                break;
            case 1:
                h ^= data[length - 1];
                break;
        }

        h *= m;
        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;
        return h;
    }


    /**
     * Generate FNV hash for given string
     *
     * @param str
     * @return
     */
    int FNVHash(String str) {
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        return hash32(data);
    }

    private int hash32(byte[] data) {
        return hash32(data, data.length);
    }

    private int hash32(byte[] data, int length) {
        int FNV1_32_INIT = 0x811c9dc5;
        int FNV1_PRIME_32 = 16777619;

        int hash = FNV1_32_INIT;
        for (int i = 0; i < length; i++) {
            hash ^= (data[i] & 0xff);
            hash *= FNV1_PRIME_32;
        }

        return hash;
    }
}