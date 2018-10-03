package mahjong.matcher

import mahjong.matcher.HonituMatcher
import spock.lang.Specification

import static javaslang.control.Option.none
import static javaslang.control.Option.some
import static mahjong.CharTile.RED
import static mahjong.CharTile.WHITE
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.Hand.HONITU
import static mahjong.TestHelper.$
import static mahjong.TestHelper.all

class HonituMatcherTest extends Specification {
    def test() {
        expect:
        HonituMatcher.matcher.apply(tiles) == exp

        where:
        tiles || exp
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [WHITE, WHITE, WHITE],
                [$(5, P), $(5, P)],
        )     || none()
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [WHITE, WHITE, WHITE],
                [$(5, M), $(5, M)],
        )     || some(HONITU)
        all(
                [$(1, M), $(2, M), $(3, M)],
                [$(4, M), $(5, M), $(6, M)],
                [$(7, M), $(7, M), $(7, M)],
                [WHITE, WHITE, WHITE],
                [RED, RED]
        )     || some(HONITU)
    }
}
