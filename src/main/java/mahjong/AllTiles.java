package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.function.Predicate;

@AllArgsConstructor
@EqualsAndHashCode
public class AllTiles {
    private final List<Tile> values;

    public boolean no(Predicate<Tile> p) {
        return !any(p);
    }

    public boolean any(Predicate<Tile> p) {
        return values.map(p::test).contains(true);
    }

    public List<RankTile> trimCharTiles() {
        return values.filter(Tile::isKazuhai).map(x -> (RankTile) x); // todo
    }

    @Override
    public String toString() {
        return values.mkString(", ");
    }
}
