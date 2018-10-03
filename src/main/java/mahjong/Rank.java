package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;


@AllArgsConstructor
@EqualsAndHashCode
public class Rank implements Comparable<Rank> {
    private final int value;

    public final boolean is1or9() {
        return value == 1 || value == 9;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(Rank o) {
        return Integer.compare(value, o.value);
    }

    public List<Rank> mkRange(int count) {
        return List.range(value, value + count).map(Rank::new);
    }
}
