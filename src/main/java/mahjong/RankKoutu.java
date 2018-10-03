package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
public class RankKoutu {
    public final List<RankTile> tiles;

    public Color asColor() {
        return tiles.map(RankTile::asColor).head();
    }

    public boolean sameRank(RankKoutu other) {
        return tiles.map(RankTile::asRank).eq(other.tiles.map(RankTile::asRank));
    }

    @Override
    public String toString() {
        return "RankKoutu(" + tiles.head() + ")";
    }
}
