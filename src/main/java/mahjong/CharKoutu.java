package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class CharKoutu implements Comparable<CharKoutu> {
    public final List<CharTile> tiles;

    @Override
    public String toString() {
        return "CharKoutu(" + tiles.head() + ")";
    }

    @Override
    public int compareTo(CharKoutu o) {
        return tiles.head().compareTo(o.tiles.head());
    }
}
