package gh2;

import deque.Deque61B;
import deque.LinkedListDeque61B;

// 注意：在您完成 Deque61B 的实现之前，此文件将无法编译
public class GuitarString {
    /** 常量。请勿更改。如果您好奇的话，关键字 final
     * 意味着这些值在运行时不能被更改。我们将会在周五的讲座中
     * 讨论这个以及其他话题。 */
    private static final int SR = 44100;      // Sampling Rate (采样率)
    private static final double DECAY = .996; // energy decay factor (能量衰减因子)

    /* 用于存储声音数据的缓冲区。 */
    private Deque61B<Double> buffer;

    /* 创建一个给定频率的吉他弦。 */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque61B<>(); // 使用链表实现

        // 初始化,以0填充全部缓冲区.
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* 通过将缓冲区替换为白噪声来拨动吉他弦。 */
    public void pluck() {
        //       确保您的随机数彼此不同。这并不意味着您需要检查数字
        //       是否彼此不相同（即不需要去重）。这意味着您应该重复调用
        //       Math.random() - 0.5 为每个数组索引生成新的随机数。
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            buffer.removeLast();
            buffer.addFirst(Math.random() - 0.5);
        }

    }

    /* 通过执行一次 Karplus-Strong 算法迭代，将模拟推进一步。
     */
    public void tic() {
        Double v1 = buffer.get(0);
        Double v2 = buffer.get(1);
        Double newDouble = ((v1 + v2) / 2) * DECAY;

        buffer.removeFirst();
        buffer.addLast(newDouble);
    }

    /* 返回缓冲区前端的 double 值。 */
    public double sample() {
        // TODO: 返回正确的内容。
        return buffer.get(0);
    }
}