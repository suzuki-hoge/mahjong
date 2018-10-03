package mahjong.matcher;

import javaslang.collection.List;
import javaslang.control.Option;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mahjong.*;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class NormalHandTilesMatcher {
    private final NormalHandTiles tiles;

    public boolean isMatch(Cond<List<Shuntu>, List<RankKoutu>, List<CharKoutu>, Option<RankToitu>, Option<CharToitu>> p) {
        return p.test(tiles.ss, tiles.rks, tiles.cks, tiles.rt, tiles.ct);
    }
}
