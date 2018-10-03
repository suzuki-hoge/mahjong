package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class RankToitu {
    private final List<RankTile> tiles;

    public Color asColor() {
        return tiles.map(RankTile::asColor).head();
    }
}
