package mahjong;

import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Traversable;
import javaslang.control.Option;
import lombok.AllArgsConstructor;
import lombok.ToString;

import static javaslang.Function1.identity;
import static javaslang.control.Option.none;

@AllArgsConstructor
public class ToituFixedHandTiles {
    private final List<Tile> tiles;

    public static List<ToituFixedHandTiles> fixAll(List<Tile> tiles) {
        return tiles.groupBy(identity())
                .map(Tuple2::_2)
                .filter(vs -> vs.size() >= 2)
                .map(Traversable::head)
                .toList()
                .map(tile -> tiles.remove(tile).remove(tile))
                .map(ToituFixedHandTiles::new);
    }

    public Option<NormalHandTiles> grouping() {
        Tuple2<List<Tile>, List<Tile>> spd = tiles
                .sortBy(tile -> tile.isKazuhai() ? 0 : 1)
                .splitAt(Tile::isJihai);

        List<ColoredRankTiles> crts = spd._1
                .map(tile -> (RankTile) tile)
                .groupBy(RankTile::asColor)
                .map(Tuple2::_2)
                .sortBy(tiles -> tiles.head().asColor())
                .map(ColoredRankTiles::new)
                .toList();

        List<ColoredTripleRankTiles> ctrts = crts.flatMap(ColoredRankTiles::maybeTriple);

        List<Shuntu> ss = ctrts.flatMap(ColoredTripleRankTiles::maybeShuntus);
        List<RankKoutu> rks = ctrts.flatMap(ColoredTripleRankTiles::maybeKoutus);

        List<List<CharTile>> chars = spd._2
                .map(tile -> (CharTile) tile)
                .sorted()
                .groupBy(identity())
                .map(Tuple2::_2)
                .sortBy(Traversable::head)
                .toList();

        List<CharKoutu> cs = chars
                .filter(tiles -> tiles.size() == 3 && tiles.distinct().size() == 1)
                .map(CharKoutu::new);

        return Option.when(
                ss.size() + rks.size() + cs.size() == 4,
                () -> new NormalHandTiles(ss, rks, cs, none(), none())
        );
    }

    @AllArgsConstructor
    @ToString(includeFieldNames = false)
    private class ColoredRankTiles {
        private final List<RankTile> tiles;

        private Option<ColoredTripleRankTiles> maybeTriple() {
            return Option.when(
                    tiles.size() % 3 == 0,
                    () -> new ColoredTripleRankTiles(tiles.grouped(3).toList())
            );
        }
    }

    @AllArgsConstructor
    @ToString(includeFieldNames = false)
    private class ColoredTripleRankTiles {
        private final List<List<RankTile>> tiles;

        private List<Shuntu> maybeShuntus() {
            return tiles.flatMap(xs -> Option.when(isIncremental(xs), () -> new Shuntu(xs)));
        }

        private boolean isIncremental(List<RankTile> tiles) {
            List<Rank> ranks = tiles.map(RankTile::asRank);
            Rank first = ranks.head();
            return ranks.eq(first.mkRange(3));
        }

        private List<RankKoutu> maybeKoutus() {
            return tiles.flatMap(xs -> Option.when(isSameAll(xs), () -> new RankKoutu(xs)));
        }

        private boolean isSameAll(List<RankTile> tiles) {
            return tiles.distinct().size() == 1;
        }
    }
}
