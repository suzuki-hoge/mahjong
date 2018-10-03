package mahjong.matcher

import spock.lang.Specification

import static javaslang.control.Option.none
import static javaslang.control.Option.some
import static mahjong.CharTile.*
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.Hand.IKKITUUKAN
import static mahjong.TestHelper.$
import static mahjong.TestHelper.normal

class IkkituukanMatcherTest extends Specification {
    def test() {
        expect:
        IkkituukanMatcher.matcher.apply(tiles) == exp

        where:
        tiles || exp
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, M), $(5, M), $(6, M)]],
                [],
                [[WHITE, WHITE, WHITE], [RED, RED, RED]],
                [],
                [GREEN, GREEN]
        )     || none()
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, P), $(5, P), $(6, P)], [$(7, M), $(8, M), $(9, M)]],
                [],
                [[WHITE, WHITE, WHITE]],
                [],
                [GREEN, GREEN]
        )     || none()
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, M), $(5, M), $(6, M)], [$(7, M), $(8, M), $(9, M)]],
                [],
                [[WHITE, WHITE, WHITE]],
                [],
                [GREEN, GREEN]
        )     || some(IKKITUUKAN)
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(4, M), $(5, M), $(6, M)], [$(7, M), $(8, M), $(9, M)], [$(2, M), $(3, M), $(4, M)]],
                [],
                [],
                [],
                [GREEN, GREEN]
        )     || some(IKKITUUKAN)
    }
}
