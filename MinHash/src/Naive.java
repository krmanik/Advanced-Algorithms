import java.util.HashSet;
import java.util.Set;

public class Naive {
    public Set<String> getIntersection(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    public Set<String> getUnion(Set<String> set1, Set<String> set2) {
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    public double similarity(Set<String> set1, Set<String> set2) {
        Set<String> intersection = getIntersection(set1, set2);
        Set<String> union = getUnion(set1, set2);

        if (union.size() == 0) {
            return 0;
        }

        return (double) intersection.size() / union.size();
    }
}