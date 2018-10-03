package mahjong.matcher;

import javaslang.collection.List;
import mahjong.AllTiles;
import mahjong.Hand;

public class AllTilesMatchers {

    public static List<Hand> applyAll(AllTiles tiles) {
        return List.of(
                TanyaoMatcher.matcher,
                HonituMatcher.matcher,
                TinituMatcher.matcher
        ).flatMap(matcher -> matcher.apply(tiles));
    }
}
