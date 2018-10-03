package mahjong.matcher;

import javaslang.collection.List;
import mahjong.Hand;
import mahjong.NormalHandTiles;

public class NormalHandTilesMatchers {
    public static List<Hand> applyAll(NormalHandTiles tiles) {
        return List.of(
                IpeikoMatcher.matcher,
                IkkituukanMatcher.matcher
        ).flatMap(matcher -> matcher.apply(tiles));
    }
}
