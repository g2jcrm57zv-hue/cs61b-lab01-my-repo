package game2048rendering;

/**
 * 代表 2048 棋盘上一个带有数字的方块图像。
 * @author P. N. Hilfinger.
 */
public class Tile {

    /**
     * 创建一个新的方块，数值为 VALUE，位置在 (x, y)。
     * 这个构造函数是私有的，所以所有的方块都是通过
     * 工厂方法 create 创建的。
     */
    private Tile(int value, int x, int y) {
        this._value = value;
        this._x = x;
        this._y = y;
        this._next = null;
        this._merged  = false;
    }

    /** 返回这个方块是否已经被合并过。 */
    public boolean wasMerged() {
        return _merged;
    }

    /** 设置合并状态。 */
    void setMerged(boolean merged) {
        this._merged = merged;
    }

    /** 返回我当前的 y 坐标。 */
    int y() {
        return _y;
    }

    /** 返回我当前的 x 坐标。 */
    int x() {
        return _x;
    }

    /** 返回提供给我构造函数的数值。 */
    public int value() {
        return _value;
    }

    /**
     * 返回我的下一个状态。在我被移动或合并之前，
     * 我就是我自己的后继者。
     */
    Tile next() {
        return _next == null ? this : _next;
    }

    /** 当我被移动或合并时，设置我的下一个状态。 */
    void setNext(Tile otherTile) {
        _next = otherTile;
    }

    /** 在 (x, y) 位置返回一个新的方块，数值为 VALUE。 */
    public static Tile create(int value, int x, int y) {
        return new Tile(value, x, y);
    }

    /**
     * 返回我和我的后继方块之间在行或列上的距离
     * （如果我没有后继者，则为 0）。
     */
    int distToNext() {
        if (_next == null) {
            return 0;
        } else {
            return Math.max(Math.abs(_y - _next.y()),
                    Math.abs(_x - _next.x()));
        }
    }

    @Override
    public String toString() {
        return String.format("Tile %d at position (%d, %d)", value(), x(), y());
    }

    /** 我的数值。 */
    private final int _value;

    /** 我在棋盘上的最后位置。 */
    private final int _x;
    private final int _y;

    /** 我是否已经合并。 */
    private boolean _merged;

    /** 后继方块：我要移动到或者合并成的那个方块。 */
    private Tile _next;
}