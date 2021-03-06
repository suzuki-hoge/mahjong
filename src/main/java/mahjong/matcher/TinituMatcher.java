package mahjong.matcher;

import javaslang.Function1;
import javaslang.control.Option;
import mahjong.AllTiles;
import mahjong.Hand;
import mahjong.Tile;

import static javaslang.control.Option.when;
import static mahjong.Hand.TINITU;

public class TinituMatcher {
    static public Function1<AllTiles, Option<Hand>> matcher = tiles -> when(
            new AllTilesMatcher(tiles).uniqueColor() && new AllTilesMatcher(tiles).no(Tile::isJihai),
            () -> TINITU
    );
}
