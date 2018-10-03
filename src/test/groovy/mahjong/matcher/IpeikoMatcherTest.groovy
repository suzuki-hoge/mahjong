package mahjong.matcher

import spock.lang.Specification

import static javaslang.control.Option.none
import static javaslang.control.Option.some
import static mahjong.CharTile.*
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.Hand.IPEIKO
import static mahjong.TestHelper.$
import static mahjong.TestHelper.normal

class IpeikoMatcherTest extends Specification {
    def test() {
        expect:
        IpeikoMatcher.matcher.apply(tiles) == exp

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
                [[$(1, M), $(2, M), $(3, M)], [$(1, P), $(2, P), $(3, P)]],
                [],
                [[WHITE, WHITE, WHITE], [RED, RED, RED]],
                [],
                [GREEN, GREEN]
        )     || none()
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(1, M), $(2, M), $(3, M)]],
                [],
                [[WHITE, WHITE, WHITE], [RED, RED, RED]],
                [],
                [GREEN, GREEN]
        )     || some(IPEIKO)
        normal(
                [[$(1, M), $(2, M), $(3, M)], [$(1, M), $(2, M), $(3, M)], [$(1, M), $(2, M), $(3, M)]],
                [],
                [[WHITE, WHITE, WHITE]],
                [],
                [GREEN, GREEN]
        )     || some(IPEIKO)
    }
}
