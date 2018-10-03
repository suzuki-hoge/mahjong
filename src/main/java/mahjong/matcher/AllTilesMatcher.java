package mahjong.matcher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mahjong.AllTiles;
import mahjong.RankTile;
import mahjong.Tile;

import java.util.function.Predicate;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class AllTilesMatcher {
    private final AllTiles tiles;

    public boolean isMatch(Predicate<AllTiles> p) {
        return p.test(tiles);
    }

    public boolean no(Predicate<Tile> p) {
        return tiles.no(p);
    }

    public boolean any(Predicate<Tile> p) {
        return tiles.any(p);
    }

    public boolean uniqueColor() {
        return tiles.trimCharTiles().map(RankTile::asColor).distinct().size() == 1;
    }
}
