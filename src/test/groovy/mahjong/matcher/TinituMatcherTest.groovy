package mahjong.matcher

import mahjong.matcher.TinituMatcher
import spock.lang.Specification

import static javaslang.control.Option.none
import static javaslang.control.Option.some
import static mahjong.CharTile.WHITE
import static mahjong.Color.M
import static mahjong.Hand.TINITU
import static mahjong.TestHelper.$
import static mahjong.TestHelper.all

class TinituMatcherTest extends Specification {
    def test() {
        expect:
        TinituMatcher.matcher.apply(tiles) == exp

        where:
        tiles || exp
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [WHITE, WHITE, WHITE],
                [$(5, M), $(5, M)],
        )     || none()
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [$(8, M), $(8, M), $(8, M)],
                [WHITE, WHITE]
        )     || none()
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [$(8, M), $(8, M), $(8, M)],
                [$(5, M), $(5, M)],
        )     || some(TINITU)
    }
}
