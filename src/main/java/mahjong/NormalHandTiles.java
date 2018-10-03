package mahjong;

import javaslang.collection.List;
import javaslang.control.Option;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mahjong.matcher.AllTilesMatchers;
import mahjong.matcher.NormalHandTilesMatchers;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NormalHandTiles implements GroupedHandTiles {
    public final List<Shuntu> ss;
    public final List<RankKoutu> rks;
    public final List<CharKoutu> cks;
    public final Option<RankToitu> rt; // todo remove?
    public final Option<CharToitu> ct; // todo remove?

    @Override
    public List<Hand> judge() {
        AllTiles all = new AllTiles(
                ss.flatMap(s -> s.tiles.map(x -> (Tile) x)).appendAll(rks.flatMap(rk -> rk.tiles)).appendAll(cks.flatMap(ck -> ck.tiles))
        );
        return NormalHandTilesMatchers.applyAll(this).appendAll(AllTilesMatchers.applyAll(all));
    }
}
