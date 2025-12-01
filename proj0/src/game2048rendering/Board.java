package game2048rendering;

import java.util.Arrays;
import java.util.Formatter;

/**
 * @author hug
 */
public class Board {
    /** 棋盘的当前内容。 */
    private final Tile[][] _values;
    /** 当前棋盘视作“北”（上方）的那一边（视角）。 */
    private Side _viewPerspective;

    public Board(int size) {
        _values = new Tile[size][size];
        _viewPerspective = Side.NORTH;
    }

    /** 切换棋盘的视角，使得棋盘的表现就像 S 边是北（上方）一样。 */
    public void setViewingPerspective(Side s) {
        _viewPerspective = s;
    }

    /**
     * 创建一个棋盘，其中 RAWVALUES 保存了棋盘上方块的值
     * （0 表示 null），并将当前视角设置为北。
     */
    public Board(int[][] rawValues) {
        int size = rawValues.length;
        _values = new Tile[size][size];
        _viewPerspective = Side.NORTH;
        for (int x = 0; x < size; x += 1) {
            for (int y = 0; y < size; y += 1) {
                int value = rawValues[size - 1 - y][x];
                Tile tile;
                if (value == 0) {
                    tile = null;
                } else {
                    tile = Tile.create(value, x, y);
                }
                _values[x][y] = tile;
            }
        }
    }

    /** 返回棋盘的大小。 */
    public int size() {
        return _values.length;
    }

    /**
     * 当棋盘方向调整为 SIDE 在顶部（离你最远）时，
     * 返回位于 (x, y) 的当前 Tile。
     */
    private Tile vtile(int x, int y, Side side) {
        return _values[side.x(x, y, size())][side.y(x, y, size())];
    }

    /**
     * 返回位于 (x, y) 的当前 Tile，其中 0 <= x < size(),
     * 0 <= y < size()。如果那里没有方块，则返回 null。
     */
    public Tile tile(int x, int y) {
        return vtile(x, y, _viewPerspective);
    }

    /** 清空棋盘使其为空，并重置分数。 */
    public void clear() {
        for (Tile[] column : _values) {
            Arrays.fill(column, null);
        }
    }

    /** 将方块 T 添加到棋盘上。 */
    public void addTile(Tile t) {
        _values[t.x()][t.y()] = t;
    }


    /**
     * 将方块 TILE 放置在列 x，行 y 的位置，其中 x 和 y 是
     * 相对于当前 viewPerspective（视角）的坐标。
     *
     * (0, 0) 是左下角。
     *
     * 如果这次移动导致了合并，将方块的 merged 状态设为 true。
     * */
    public void move(int x, int y, Tile tile) {
        int px = _viewPerspective.x(x, y, size());
        int py = _viewPerspective.y(x, y, size());

        Tile tile1 = vtile(x, y, _viewPerspective);
        _values[tile.x()][tile.y()] = null;

        // 移动或合并方块。重要的是要在旧方块上调用 setNext，
        // 以便它们可以通过动画移动到位置上
        Tile next;
        if (tile1 == null) {
            next = Tile.create(tile.value(), px, py);
        } else {
            if (tile.value() != tile1.value()) {
                throw new IllegalArgumentException("Tried to merge two unequal tiles: " + tile + " and " + tile1);
            }
            next = Tile.create(2 * tile.value(), px, py);
            tile1.setNext(next);
        }
        tile.setMerged(tile1 != null);
        next.setMerged(tile.wasMerged());
        tile.setNext(next);
        _values[px][py] = next;
    }

    /** 将棋盘上每个方块的所有 merged 布尔值重置为 false。 */
    public void resetMerged() {
        for (int x = 0; x < size(); x += 1) {
            for (int y = 0; y < size(); y += 1) {
                if (_values[x][y] != null){
                    _values[x][y].setMerged(false);
                }
            }
        }
    }

    /** 以字符串形式返回棋盘，用于调试。 */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        return out.toString();
    }
}