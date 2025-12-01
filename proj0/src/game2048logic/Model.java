package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;


/**
 * 2048 游戏的状态。
 * @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** 当前棋盘上的内容。 */
    private final Board board;
    /** 当前分数。 */
    private int score;

    /* 坐标系统：棋盘的列 x，行 y（其中 x = 0, y = 0 是棋盘的左下角）
     * 对应于 board.tile(x, y)。请小心！
     */

    /** 最大的方块数值。 */
    public static final int MAX_PIECE = 2048;

    /**
     * 创建一个新的 2048 游戏，棋盘大小为 SIZE，没有任何方块，
     * 分数为 0。
     */
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /**
     * 创建一个新的 2048 游戏，其中 RAWVALUES 包含了方块的值
     * (如果是 null 则为 0)。VALUES 使用 (x, y) 索引，其中 (0, 0) 对应
     * 左下角。用于测试目的。
     */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /**
     * 返回位于 (x, y) 的当前 Tile 对象，其中 0 <= x < size(),
     * 0 <= y < size()。如果那里没有方块，则返回 null。
     * 用于测试。
     */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** 返回棋盘一边的方格数量（即棋盘大小）。 */
    public int size() {
        return board.size();
    }

    /** 返回当前分数。 */
    public int score() {
        return score;
    }


    /** 将棋盘清空并将分数重置为 0。 */
    public void clear() {
        score = 0;
        board.clear();
    }

    /**
     * 将 TILE 添加到棋盘上。当前位置必须没有 Tile。
     */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /**
     * 当且仅当游戏结束时返回 true（即没有可移动的步数，或者
     * 棋盘上已经有了值为 2048 的方块）。
     */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** 返回这个 Model 的 board 对象。 */
    public Board getBoard() {
        return board;
    }

    /**
     * 如果棋盘上至少有一个位置是空的，则返回 true。
     * 空位置存储为 null。
     * */
    public boolean emptySpaceExists() {
        // TODO: 任务 2. 填写此函数。
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                if (board.tile(x, y) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果任意一个方块的值等于最大有效值（2048），则返回 true。
     * 最大有效值由 this.MAX_PIECE 给出。注意：
     * 给定一个 Tile 对象 t，我们通过 t.value() 获取它的值。
     */
    public boolean maxTileExists() {
        // TODO: 任务 3. 填写此函数。
        for (int x = 0; x < board.size(); x++) {
            for (int y = 0; y < board.size(); y++) {
                if (board.tile(x, y) != null && board.tile(x, y).value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果棋盘上存在任何有效的移动，则返回 true。
     * 存在有效移动的情况有两种：
     * 1. 棋盘上至少有一个空位。
     * 2. 有两个相邻的方块具有相同的值。
     */
    public boolean atLeastOneMoveExists() {
        // TODO: 填写此函数。
        return false;
    }

    /**
     * 将位于位置 (x, y) 的方块尽可能向上移动。
     *
     * 倾斜（移动）规则：
     * 1. 如果两个方块在移动方向上相邻且具有相同的值，它们将合并为一个
     *    原来数值两倍的方块，并且新的数值会加到 score 实例变量中。
     * 2. 一个由合并产生的方块在同一次倾斜中不会再次合并。因此每次移动，
     *    每个方块最多只会参与一次合并（也可能零次）。
     * 3. 当移动方向上有三个相邻的方块具有相同的值时，移动方向最前面的
     *    两个方块合并，而后面的那个方块不合并。
     */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile currTile = board.tile(x, y);
        int myValue = currTile.value();
        int targetY = y;

        // TODO: 任务 5, 6, 和 10. 填写此函数。
    }

    /**
     * 处理棋盘第 x 列的倾斜移动逻辑，将该列中的每个方块尽可能向上移动。
     * 视角（perspective）已经被设置好了，
     * 所以我们只是把这一列的方块“向上”倾斜。
     * */
    public void tiltColumn(int x) {
        // TODO: 任务 7. 填写此函数。
    }

    public void tilt(Side side) {
        // TODO: 任务 8 和 9. 填写此函数。
    }

    /**
     * 将棋盘的每一列都朝向 SIDE 方向倾斜。
     */
    public void tiltWrapper(Side side) {
        board.resetMerged();
        tilt(side);
    }


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
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}