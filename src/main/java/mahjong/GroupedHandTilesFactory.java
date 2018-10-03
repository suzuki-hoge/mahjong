package mahjong;

import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.control.Option;

import static javaslang.Function1.identity;

public class GroupedHandTilesFactory {
    public static List<GroupedHandTiles> grouping(List<Tile> tiles) {
        return normal(tiles).appendAll(titoitu(tiles));
    }

    private static List<GroupedHandTiles> normal(List<Tile> tiles) {
        return ToituFixedHandTiles3.fixAll(tiles).flatMap(ToituFixedHandTiles3::grouping);
    }

    private static Option<TitoituHandTiles> titoitu(List<Tile> tiles) {
        return Option.when(
                tiles.groupBy(identity()).map(Tuple2::_2).forAll(grouped -> grouped.size() == 2),
                () -> new TitoituHandTiles(new AllTiles(tiles))
        );
    }
}
