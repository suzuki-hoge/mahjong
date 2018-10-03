package mahjong;

import javaslang.collection.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Hand {
    TANYAO(1), HONITU(3), TINITU(6), IPEIKO(1), IKKITUUKAN(2), TITOITU(2);

    private final int fan;

    public static int fans(List<Hand> hands) {
        return hands.sum().intValue();
    }
}
