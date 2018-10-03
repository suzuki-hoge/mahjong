package mahjong

import javaslang.collection.List
import spock.lang.Specification
import spock.lang.Unroll

import static mahjong.CharTile.*
import static mahjong.Color.M
import static mahjong.Color.P
import static mahjong.Hand.*
import static mahjong.TestHelper.$
import static mahjong.TestHelper.all

@Unroll
class HandTilesTest extends Specification {
    def '#label: [#tiles] makes #exp'() {
        expect:
        new HandTiles(tiles.values).judge() == List.ofAll(exp)

        where:
        label                | tiles || exp
        '基本形（刻子不正）'          |
                all(
                        [$(1, P), $(2, P), $(3, P)],
                        [$(4, P), $(5, P), $(6, P)],
                        [RED, RED, RED],
                        [WHITE, WHITE],
                        [GREEN],
                        [$(4, M), $(4, M)],
                )                    ||
                [
                ]
        '七対子（七対子のみ）'         |
                all(
                        [$(2, M), $(2, M)],
                        [$(3, M), $(3, M)],
                        [$(4, M), $(4, M)],
                        [$(5, M), $(5, M)],
                        [$(6, M), $(6, M)],
                        [$(7, P), $(7, P)],
                        [WHITE, WHITE],
                )                    ||
                [
                        TITOITU
                ]
        '七対子（タンヤオあり）'        |
                all(
                        [$(2, M), $(2, M)],
                        [$(3, M), $(3, M)],
                        [$(4, M), $(4, M)],
                        [$(5, M), $(5, M)],
                        [$(6, M), $(6, M)],
                        [$(7, P), $(7, P)],
                        [$(8, P), $(8, P)],
                )                    ||
                [
                        TANYAO, TITOITU
                ]
        '七対子（混一あり）'          |
                all(
                        [$(2, M), $(2, M)],
                        [$(3, M), $(3, M)],
                        [$(4, M), $(4, M)],
                        [$(5, M), $(5, M)],
                        [$(6, M), $(6, M)],
                        [WHITE, WHITE],
                        [RED, RED],
                )                    ||
                [
                        HONITU, TITOITU
                ]
        '基本形（タンヤオ一, 一盃口）'     |
                all(
                        [$(2, P), $(3, P), $(4, P)],
                        [$(2, P), $(3, P), $(4, P)],
                        [$(8, M), $(8, M), $(8, M)],
                        [$(8, M), $(8, M), $(8, M)],
                        [$(4, M), $(4, M)],
                )                    ||
                [
                        IPEIKO, TANYAO
                ]
        '基本形（一気通貫, 混一）' |
                all(
                        [$(1, P), $(2, P), $(3, P)],
                        [$(4, P), $(5, P), $(6, P)],
                        [$(7, P), $(8, P), $(9, P)],
                        [WHITE, WHITE, WHITE],
                        [RED, RED],
                )                    ||
                [
                        IKKITUUKAN, HONITU
                ]
    }
}
