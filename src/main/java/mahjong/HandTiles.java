package mahjong;

import javaslang.collection.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class HandTiles {
    private final List<Tile> tiles;

    public List<Hand> judge() {
        return GroupedHandTilesFactory.grouping(tiles)
                .map(GroupedHandTiles::judge)
                .sortBy(Hand::fans)
                .headOption()
                .getOrElse(List.empty());
    }
}
