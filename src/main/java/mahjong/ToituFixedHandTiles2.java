package mahjong;

import javaslang.Function1;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Traversable;
import javaslang.control.Option;
import lombok.AllArgsConstructor;

import static javaslang.Function1.identity;

@AllArgsConstructor
public class ToituFixedHandTiles2 {
    private final List<Tile> tiles;

    public static List<ToituFixedHandTiles2> fixAll(List<Tile> tiles) {
        return tiles.groupBy(identity())
                .map(Tuple2::_2)
                .filter(vs -> vs.size() >= 2)
                .map(Traversable::head)
                .toList()
                .map(tile -> tiles.remove(tile).remove(tile))
                .map(ToituFixedHandTiles2::new);
    }

    public Option<NormalHandTiles> grouping() {
        Tuple2<List<Tile>, List<Tile>> spd = tiles
                .sortBy(tile -> tile.isKazuhai() ? 0 : 1)
                .splitAt(Tile::isJihai);

        List<RankTile> remainRanks = spd._1.map(tile -> (RankTile) tile);
        List<RankTile> uniqueRanks = remainRanks.distinct();
        List<CharTile> remainChars = spd._2.map(tile -> (CharTile) tile);
        List<CharTile> uniqueChars = remainChars.distinct();

        System.out.println(remainChars);
        List<CharKoutu> cks = fixCharKoutu(uniqueChars.get(0), uniqueChars.pop(), remainChars, List.empty());
        System.out.println(cks);

        System.out.println("");
        return Option.none();
    }

    private static List<CharKoutu> fixCharKoutu(CharTile unique, List<CharTile> uniques, List<CharTile> remains, List<CharKoutu> acc) {
        if (remains.filter(x -> x.equals(unique)).size() == 3) {
            Function1<CharTile, CharKoutu> c = t -> new CharKoutu(List.of(t, t, t));

            return uniques.isEmpty()
                    ? acc.append(c.apply(unique))
                    : fixCharKoutu(
                    uniques.get(0),
                    uniques.pop(),
                    remains.remove(unique).remove(unique).remove(unique),
                    acc.append(c.apply(unique))
            );
        } else {
            return uniques.isEmpty()
                    ? acc
                    : fixCharKoutu(
                    uniques.get(0),
                    uniques.pop(),
                    remains,
                    acc
            );
        }

    }

//    private Option<List<CharKoutu>> maybeCharKoutus(List<CharTile> tiles) {
//        System.out.println(
//                tiles
//                        .groupBy(identity())
//                        .map(Tuple2::_2)
//                        .toList()
//        );
//
//        return Option.none();
//    }
}
