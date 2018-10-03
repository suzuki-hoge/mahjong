package mahjong

import spock.lang.Specification

import static mahjong.CharTile.GREEN
import static mahjong.CharTile.WHITE
import static mahjong.Color.M
import static mahjong.Hand.*
import static mahjong.TestHelper.$
import static mahjong.TestHelper.normal

class NormalHandTilesTest extends Specification {
    def test() {
        expect:
        new NormalHandTiles(tiles.ss, tiles.rks, tiles.cks, tiles.rt, tiles.ct).judge() == exp

        where:
        tiles || exp
        normal(
                [],
                [[$(1, M), $(1, M), $(1, M)], [$(5, M), $(5, M), $(5, M)], [$(9, M), $(9, M), $(9, M)]],
                [[WHITE, WHITE, WHITE]],
                [],
                [GREEN, GREEN]
        )     || $(HONITU)
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(1, M), $(2, M), $(3, M)]],
                [[$(5, M), $(5, M), $(5, M)], [$(9, M), $(9, M), $(9, M)]],
                [],
                [],
                [GREEN, GREEN]
        )     || $(IPEIKO, TINITU)
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, M), $(5, M), $(6, M)], [$(7, M), $(8, M), $(9, M)]],
                [[$(9, M), $(9, M), $(9, M)]],
                [],
                [],
                [GREEN, GREEN]
        )     || $(IKKITUUKAN, TINITU)
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, M), $(5, M), $(6, M)], [$(7, M), $(8, M), $(9, M)], [$(7, M), $(8, M), $(9, M)]],
                [],
                [],
                [],
                [GREEN, GREEN]
        )     || $(IPEIKO, IKKITUUKAN, TINITU)
    }
}
