package mahjong.matcher;

import javaslang.Function1;
import javaslang.collection.List;
import javaslang.control.Option;
import mahjong.Hand;
import mahjong.NormalHandTiles;
import mahjong.Rank;
import mahjong.Shuntu;

import static javaslang.control.Option.when;
import static mahjong.Hand.IKKITUUKAN;

public class IkkituukanMatcher {
    static public Function1<NormalHandTiles, Option<Hand>> matcher = tiles -> when(
            new NormalHandTilesMatcher(tiles).isMatch(
                    (ss, rks, cks, rt, ct) ->
                            ss.map(Shuntu::asRunks).containsAll(
                                    List.of(
                                            List.range(1, 4).map(Rank::new),
                                            List.range(4, 7).map(Rank::new),
                                            List.range(7, 10).map(Rank::new)
                                    )
                            ) && ss.map(Shuntu::asColor).distinct().size() == 1
            ),
            () -> IKKITUUKAN
    );
}
