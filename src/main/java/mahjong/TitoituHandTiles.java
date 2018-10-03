package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import mahjong.matcher.AllTilesMatchers;

import static mahjong.Hand.TITOITU;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class TitoituHandTiles implements GroupedHandTiles {
    private final AllTiles tiles;

    @Override
    public List<Hand> judge() {
        return AllTilesMatchers.applyAll(tiles).append(TITOITU);
    }
}
