package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Shuntu {
    public final List<RankTile> tiles;

    public Shuntu(RankTile... ts) {
        tiles = List.of(ts);
    }

    public List<Rank> asRunks() {
        return tiles.map(RankTile::asRank);
    }

    public Color asColor() {
        return tiles.map(RankTile::asColor).head();
    }

    public boolean same(Shuntu other) {
        return tiles.equals(other);
    }

    public boolean sameRank(Shuntu other) {
        return tiles.map(RankTile::asRank).equals(other.tiles.map(RankTile::asRank));
    }

    @Override
    public String toString() {
        return "Shuntu(" + tiles.mkString(", ") + ")";
    }
}
