package mahjong

import spock.lang.Specification
import spock.lang.Unroll

import static javaslang.control.Option.none
import static mahjong.CharTile.*
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.TestHelper.*

@Unroll
class GroupedHandTilesFactoryTest extends Specification {
    def "#label: [#tiles] makes #exp.size() patterns."() {
        expect:
        GroupedHandTilesFactory.grouping(tiles.values).sort() == exp.sort()

        where:
        label                                                                   | tiles || exp
        '七対子（七対子のみ）'                                                            |
                all(
                        [$(2, M), $(2, M)],
                        [$(3, M), $(3, M)],
                        [$(4, M), $(4, M)],
                        [$(5, M), $(5, M)],
                        [$(6, M), $(6, M)],
                        [$(7, P), $(7, P)],
                        [WHITE, WHITE],
                )                                                                       ||
                $(
                        new TitoituHandTiles(all([$(2, M), $(2, M)], [$(3, M), $(3, M)], [$(4, M), $(4, M)], [$(5, M), $(5, M)], [$(6, M), $(6, M)], [$(7, P), $(7, P)], [WHITE, WHITE]))
                )
        '七対子（二盃口あり）'                                                            |
                all(
                        [$(1, M), $(1, M)],
                        [$(2, M), $(2, M)],
                        [$(3, M), $(3, M)],
                        [$(7, P), $(7, P)],
                        [$(8, P), $(8, P)],
                        [$(9, P), $(9, P)],
                        [WHITE, WHITE],
                )                                                                       ||
                $(
                        new TitoituHandTiles(all([$(1, M), $(1, M)], [$(2, M), $(2, M)], [$(3, M), $(3, M)], [$(7, P), $(7, P)], [$(8, P), $(8, P)], [$(9, P), $(9, P)], [WHITE, WHITE])),
                        new NormalHandTiles($(s($(1, M), $(2, M), $(3, M)), s($(1, M), $(2, M), $(3, M)), s($(7, P), $(8, P), $(9, P)), s($(7, P), $(8, P), $(9, P))), $(), $(), none(), none())
                )
        '基本形（順子不正）'                                                             |
                all(
                        [$(1, P), $(2, P), $(3, P)],
                        [$(1, P), $(4, P), $(7, P)],
                        [RED, RED, RED],
                        [WHITE, WHITE, WHITE],
                        [$(4, M), $(4, M)],
                )                                                                       ||
                $(
                )
        '基本形（刻子不正）'                                                             |
                all(
                        [$(1, P), $(2, P), $(3, P)],
                        [$(4, P), $(5, P), $(6, P)],
                        [RED, RED, RED],
                        [WHITE, WHITE],
                        [GREEN],
                        [$(4, M), $(4, M)],
                )                                                                       ||
                $(
                )
        '基本形（順子と刻子）'                                                            |
                all(
                        [$(1, P), $(2, P), $(3, P)],
                        [$(4, P), $(5, P), $(6, P)],
                        [RED, RED, RED],
                        [WHITE, WHITE, WHITE],
                        [$(9, P), $(9, P)],
                )                                                                       ||
                $(
                        new NormalHandTiles($(s($(1, P), $(2, P), $(3, P)), s($(4, P), $(5, P), $(6, P))), $(), $(ck(WHITE, WHITE, WHITE), ck(RED, RED, RED)), none(), none())
                )
        '基本形（[1, 1], [2, 3, 4], [2, 3, 4] and [1, 2, 3], [1, 2, 3], [4, 4] の形）' |
                all(
                        [$(1, M), $(2, M), $(3, M), $(4, M)],
                        [$(1, M), $(2, M), $(3, M), $(4, M)],
                        [$(9, P), $(9, P), $(9, P)],
                        [RED, RED, RED],
                )                                                                       ||
                $(
                        new NormalHandTiles($(s($(1, M), $(2, M), $(3, M)), s($(1, M), $(2, M), $(3, M))), $(rk($(9, P), $(9, P), $(9, P))), $(ck(RED, RED, RED)), none(), none()),
                        new NormalHandTiles($(s($(2, M), $(3, M), $(4, M)), s($(2, M), $(3, M), $(4, M))), $(rk($(9, P), $(9, P), $(9, P))), $(ck(RED, RED, RED)), none(), none()),
                )
        '基本形（[1, 1], [1, 2, 3], [4, 4, 4] and [1, 1, 1], [2, 3, 4], [4, 4] の形）' |
                all(
                        [$(1, M), $(1, M), $(1, M)],
                        [$(2, M), $(3, M), $(4, M)],
                        [RED, RED, RED],
                        [WHITE, WHITE, WHITE],
                        [$(4, M), $(4, M)],
                )                                                                       ||
                $(
                        new NormalHandTiles($(s($(2, M), $(3, M), $(4, M))), $(rk($(1, M), $(1, M), $(1, M))), $(ck(WHITE, WHITE, WHITE), ck(RED, RED, RED)), none(), none()),
                        new NormalHandTiles($(s($(1, M), $(2, M), $(3, M))), $(rk($(4, M), $(4, M), $(4, M))), $(ck(WHITE, WHITE, WHITE), ck(RED, RED, RED)), none(), none())
                )
    }
}
