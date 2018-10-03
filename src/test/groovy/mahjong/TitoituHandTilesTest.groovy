package mahjong

import spock.lang.Specification

import static TestHelper.$
import static mahjong.CharTile.WHITE
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.Hand.*
import static mahjong.TestHelper.all

class TitoituHandTilesTest extends Specification {
    def test() {
        expect:
        new TitoituHandTiles(tiles).judge() == exp

        where:
        tiles || exp
        all(
                [$(2, M), $(2, M)],
                [$(3, M), $(3, M)],
                [$(4, M), $(4, M)],
                [$(5, M), $(5, M)],
                [$(6, M), $(6, M)],
                [$(7, P), $(7, P)],
                [$(7, M), $(7, M)],
        )     || $(TANYAO, TITOITU)
        all(
                [$(1, M), $(1, M)],
                [$(2, M), $(2, M)],
                [$(3, M), $(3, M)],
                [$(4, M), $(4, M)],
                [$(5, M), $(5, M)],
                [$(6, M), $(6, M)],
                [$(7, P), $(7, P)]
        )     || $(TITOITU)
        all(
                [$(1, M), $(1, M)],
                [$(2, M), $(2, M)],
                [$(3, M), $(3, M)],
                [$(4, M), $(4, M)],
                [$(5, M), $(5, M)],
                [$(6, M), $(6, M)],
                [WHITE, WHITE]
        )     || $(HONITU, TITOITU)
        all(
                [$(1, M), $(1, M)],
                [$(2, M), $(2, M)],
                [$(3, M), $(3, M)],
                [$(4, M), $(4, M)],
                [$(5, M), $(5, M)],
                [$(6, M), $(6, M)],
                [$(7, M), $(7, M)]
        )     || $(TINITU, TITOITU)
        all(
                [$(2, M), $(2, M)],
                [$(3, M), $(3, M)],
                [$(4, M), $(4, M)],
                [$(5, M), $(5, M)],
                [$(6, M), $(6, M)],
                [$(7, M), $(7, M)],
                [$(8, M), $(8, M)]
        )     || $(TANYAO, TINITU, TITOITU)
    }
}
