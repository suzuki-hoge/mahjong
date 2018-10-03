package mahjong;

import javaslang.collection.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class CharToitu {
    private final List<CharTile> tiles;
}
