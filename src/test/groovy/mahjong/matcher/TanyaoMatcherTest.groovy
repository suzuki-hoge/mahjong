package mahjong.matcher

import spock.lang.Specification

import static javaslang.control.Option.none
import static javaslang.control.Option.some
import static mahjong.CharTile.WHITE
import static mahjong.Color.*
import static mahjong.Hand.TANYAO
import static mahjong.TestHelper.$
import static mahjong.TestHelper.all

class TanyaoMatcherTest extends Specification {
    def test() {
        expect:
        TanyaoMatcher.matcher.apply(tiles) == exp

        where:
        tiles || exp
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(8, M), $(9, M)],
                [$(5, P), $(5, P), $(5, P)],
                [$(2, S), $(2, S)]
        )     || none()
        all(
                [$(2, M), $(2, M), $(2, M)],
                [$(3, M), $(3, M), $(3, M)],
                [$(4, M), $(4, M), $(4, M)],
                [$(5, P), $(5, P), $(5, P)],
                [WHITE, WHITE]
        )     || none()
        all(
                [$(2, M), $(2, M), $(2, M)],
                [$(3, M), $(3, M), $(3, M)],
                [$(4, M), $(4, M), $(4, M)],
                [$(5, P), $(5, P), $(5, P)],
                [$(2, S), $(2, S)]
        )     || some(TANYAO)
    }
}
