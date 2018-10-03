package mahjong;

import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Traversable;
import javaslang.control.Option;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

import static javaslang.Function1.identity;
import static javaslang.control.Option.none;
import static mahjong.Color.*;

@AllArgsConstructor
public class ToituFixedHandTiles3 {
    private final List<Tile> tiles;

    public static List<ToituFixedHandTiles3> fixAll(List<Tile> tiles) {
        return tiles.groupBy(identity())
                .map(Tuple2::_2)
                .filter(vs -> vs.size() >= 2)
                .map(Traversable::head)
                .toList()
                .map(tile -> tiles.remove(tile).remove(tile))
                .map(ToituFixedHandTiles3::new);
    }

    private List<RankTile> extract(List<RankTile> tiles, Color color) {
        return tiles.filter(tile -> tile.asColor() == color);
    }

    public Option<NormalHandTiles> grouping() {
        List<RankTile> rts = tiles.filter(Tile::isKazuhai).map(tile -> (RankTile) tile);

        List<List<List<RankTile>>> ms = permutate(rts, M);
        List<List<List<RankTile>>> ss = permutate(rts, S);
        List<List<List<RankTile>>> ps = permutate(rts, P);

        List<List<Shuntu>> mss = ms.map(ll -> ll.filter(isShuntu).map(Shuntu::new));
        List<List<Shuntu>> sss = ss.map(ll -> ll.filter(isShuntu).map(Shuntu::new));
        List<List<Shuntu>> pss = ps.map(ll -> ll.filter(isShuntu).map(Shuntu::new));

        List<List<RankKoutu>> mks = ms.map(ll -> ll.filter(isKoutu).map(RankKoutu::new));
        List<List<RankKoutu>> sks = ss.map(ll -> ll.filter(isKoutu).map(RankKoutu::new));
        List<List<RankKoutu>> pks = ps.map(ll -> ll.filter(isKoutu).map(RankKoutu::new));

        List<Shuntu> shuntus = mss
                .appendAll(sss).appendAll(pss)
                .filter(x -> !x.isEmpty())
                .flatMap(identity());

        List<RankKoutu> rankKoutus = mks
                .appendAll(sks).appendAll(pks)
                .filter(x -> !x.isEmpty())
                .flatMap(identity());

        List<CharTile> cts = tiles.filter(Tile::isJihai).map(tile -> (CharTile) tile);

        List<CharKoutu> charKoutus = cts
                .groupBy(identity())
                .map(Tuple2::_2)
                .toList()
                .filter(ts -> ts.size() == 3)
                .map(CharKoutu::new)
                .sorted();

        return Option.when(
                shuntus.size() + rankKoutus.size() + charKoutus.size() == 4,
                () -> new NormalHandTiles(shuntus, rankKoutus, charKoutus, none(), none())
        );
    }

    private static Predicate<List<RankTile>> isShuntu = tiles -> {
        List<Rank> ranks = tiles.map(RankTile::asRank);
        Rank first = ranks.head();
        return ranks.eq(first.mkRange(3));
    };

    private static Predicate<List<RankTile>> isKoutu = tiles -> tiles.distinct().size() == 1;

    private static Predicate<List<RankTile>> isShuntuOrKoutu = isShuntu.or(isKoutu);

    private List<List<List<RankTile>>> permutate(List<RankTile> tiles, Color color) {
        List<RankTile> coloredTiles = extract(tiles, color).size() % 3 == 0 ? extract(tiles, color) : List.empty();

        return coloredTiles
                .permutations()
                .map(ts -> ts.grouped(3).map(List::sorted).toList())
                .distinct()
                .filter(groupedTiles -> groupedTiles.forAll(isShuntuOrKoutu))
                .headOption()
                .toList();
    }
}
