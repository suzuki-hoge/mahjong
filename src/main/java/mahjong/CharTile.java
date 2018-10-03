package mahjong;

public enum CharTile implements Tile {
    E, S, W, N, WHITE, GREEN, RED;

    @Override
    public boolean isJihai() {
        return true;
    }

    @Override
    public boolean isKazuhai() {
        return false;
    }

    @Override
    public boolean isYaotyuhai() {
        return true;
    }
}
