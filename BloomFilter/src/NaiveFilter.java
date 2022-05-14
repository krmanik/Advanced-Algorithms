import java.util.HashSet;
import java.util.Set;

/**
 * Simple hashset implementation of adding and searching string to hashset
 */
public class NaiveFilter {
    private Set<String> blocklist = new HashSet<>();

    public boolean add(String url) {
        return blocklist.add(url);
    }

    public boolean search(String url) {
        return blocklist.contains(url);
    }
}
