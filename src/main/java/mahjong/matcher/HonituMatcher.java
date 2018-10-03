package mahjong.matcher;

import javaslang.Function1;
import javaslang.control.Option;
import mahjong.AllTiles;
import mahjong.Hand;
import mahjong.Tile;

import static javaslang.control.Option.when;
import static mahjong.Hand.HONITU;

public class HonituMatcher {
    static public Function1<AllTiles, Option<Hand>> matcher = tiles -> when(
            new AllTilesMatcher(tiles).uniqueColor() && new AllTilesMatcher(tiles).any(Tile::isJihai),
            () -> HONITU
    );
}
