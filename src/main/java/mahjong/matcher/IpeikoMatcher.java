package mahjong.matcher;

import javaslang.Function1;
import javaslang.control.Option;
import mahjong.Hand;
import mahjong.NormalHandTiles;

import static javaslang.control.Option.when;
import static mahjong.Hand.IPEIKO;

public class IpeikoMatcher {
    static public Function1<NormalHandTiles, Option<Hand>> matcher = tiles -> when(
            new NormalHandTilesMatcher(tiles).isMatch((ss, rks, cks, rt, ct) -> ss.size() >= ss.distinct().size() + 1),
            () -> IPEIKO
    );
}
