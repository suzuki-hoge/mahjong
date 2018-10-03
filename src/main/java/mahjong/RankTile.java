package mahjong;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class RankTile implements Tile, Comparable<RankTile> {
    private final Rank rank;
    private final Color color;

    public Rank asRank() {
        return rank;
    }

    public Color asColor() {
        return color;
    }

    @Override
    public boolean isJihai() {
        return false;
    }

    @Override
    public boolean isKazuhai() {
        return true;
    }

    @Override
    public boolean isYaotyuhai() {
        return rank.is1or9();
    }

    @Override
    public String toString() {
        return rank + color.toString();
    }

    @Override
    public int compareTo(RankTile o) {
        return rank.compareTo(o.rank);
    }
}
