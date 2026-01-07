import jh61b.utils.Reflection;
import org.apache.hc.core5.annotation.Internal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    void testRemoveFirst() {
        Deque61B<Integer> testList = new ArrayDeque61B<>();
        testList.addFirst(1);
        testList.addLast(2);
        testList.addLast(3);
        testList.addLast(4);
        testList.addLast(5); // 列表此时应为 [1, 2, 3, 4, 5]

        // 1. 检查返回值是否是删除的那个元素
        Integer removed = testList.removeFirst();
        assertWithMessage("removeFirst() 应该返回被移除的那个元素 (1)")
                .that(removed).isEqualTo(1);

        // 2. 检查删除后的 size 是否正确
        assertWithMessage("执行 removeFirst() 后，size 应该减少为 4")
                .that(testList.size()).isEqualTo(4);

        // 3. 检查现在的第一个元素是否变成了 2
        assertWithMessage("移除 1 后，逻辑索引 0 处的元素应该是 2")
                .that(testList.get(0)).isEqualTo(2);

        // 4. 利用 toList 一次性检查列表元素顺序
        assertWithMessage("移除 1 后，列表剩余元素顺序应为 [2, 3, 4, 5]")
                .that(testList.toList()).containsExactly(2, 3, 4, 5).inOrder();
    }

    @Test
    void testRemoveLast() {
        Deque61B<Integer> testList = new ArrayDeque61B<>();
        testList.addFirst(1);
        testList.addLast(2);
        testList.addLast(3);
        testList.addLast(4);
        testList.addLast(5); // 列表此时应为 [1, 2, 3, 4, 5]

        // 1. 检查返回值是否是删除的那个末尾元素 (5)
        Integer removed = testList.removeLast();
        assertWithMessage("removeLast() 应该返回被移除的那个末尾元素 (5)")
                .that(removed).isEqualTo(5);

        // 2. 检查删除后的 size 是否正确
        assertWithMessage("执行 removeLast() 后，size 应该减少为 4")
                .that(testList.size()).isEqualTo(4);

        // 3. 检查现在的最后一个元素（索引 3）是否变成了 4
        // 注意：原来的索引 4 是 5，现在的索引 3 应该是 4
        assertWithMessage("移除 5 后，现在的最后一个元素（逻辑索引 3）应该是 4")
                .that(testList.get(testList.size() - 1)).isEqualTo(4);

        // 4. 利用 toList 一次性检查列表元素顺序
        assertWithMessage("移除 5 后，列表剩余元素顺序应为 [1, 2, 3, 4]")
                .that(testList.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

}
