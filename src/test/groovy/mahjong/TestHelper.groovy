package mahjong

import javaslang.collection.List
import javaslang.control.Option

import java.util.List as JList

class TestHelper {
    static RankTile $(int rank, Color color) {
        new RankTile(new Rank(rank), color)
    }

    static <T> List<T> $(T... ts) {
        List.ofAll ts.toList()
    }

    static AllTiles all(JList<Tile>... tss) {
        new AllTiles(List.ofAll(tss.flatten() as JList<Tile>))
    }

    static NormalHandTiles normal(JList<JList<RankTile>> jjss, JList<JList<RankTile>> jjrks, JList<JList<CharTile>> jjcks, JList<RankTile> jrt, JList<CharTile> jct) {
        new NormalHandTiles(
                js(jjss).map { jss -> new Shuntu(js(jss)) },
                js(jjrks).map { jrks -> new RankKoutu(js(jrks)) },
                js(jjcks).map { jcks -> new CharKoutu(js(jcks)) },
                Option.when(!jrt.empty, { new RankToitu(js(jrt)) }),
                Option.when(!jct.empty, { new CharToitu(js(jct)) })
        )
    }

    private static <T> List<T> js(JList<T> t) {
        List.ofAll(t)
    }

    static Shuntu s(RankTile... tiles) {
        new Shuntu(List.ofAll(tiles.toList()))
    }

    static RankKoutu rk(RankTile... tiles) {
        new RankKoutu(List.ofAll(tiles.toList()))
    }

    static CharKoutu ck(CharTile... tiles) {
        new CharKoutu(List.ofAll(tiles.toList()))
    }
}
