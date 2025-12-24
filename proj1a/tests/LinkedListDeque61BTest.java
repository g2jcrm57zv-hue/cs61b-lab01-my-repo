import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
        }
//
//     Below, you'll write your own tests for LinkedListDeque61B.
//     在这里要写你自己的单元测试。
    @Test
    public void sizeAndIsEmptyTest() {
        // 1. 空列表测试
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("报错原因：列表中应无元素但有元素！").that(lld1.isEmpty()).isTrue();
        assertWithMessage("报错原因：列表大小应可变！").that(lld1.size()).isEqualTo(0);

        // 2. 非空列表测试
        lld1.addFirst(1);
        assertWithMessage("报错原因：列表中应有元素但无元素！").that(lld1.isEmpty()).isFalse();
        assertWithMessage("报错原因：列表大小应可变！").that(lld1.size()).isEqualTo(1);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        // 1. 有元素时测试
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);

        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，lld1.get(0)为1！")
                .that(lld1.get(0))
                .isEqualTo(1);

        // 2. 非法元素测试
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，索引数非法！")
                .that(lld1.get(5))
                .isNull();
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，索引数非法！")
                .that(lld1.get(-5))
                .isNull();
    }

    @Test
    public void getRecursive() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        // 1. 有元素时测试
        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);

        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，lld1.get(0)为1！")
                .that(lld1.getRecursive(0))
                .isEqualTo(1);

        // 2. 非法元素测试
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，索引数非法！")
                .that(lld1.getRecursive(5))
                .isNull();
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，索引数非法！")
                .that(lld1.getRecursive(-5))
                .isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        // 1. 空列表测试
        assertThat(lld1.removeFirst()).isNull();

        lld1.addFirst(4);
        lld1.addFirst(3);
        lld1.addFirst(2);
        lld1.addFirst(1);
        // 2. 列表有元素时，测试删除第一个元素是否能正确并返回。
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，删除并且返回1！")
                .that(lld1.removeFirst())
                .isEqualTo(1);

        // 3. 列表有元素时，测试删除第一个元素正确地删除了。
        assertWithMessage("错误！\n期望：链表[1, 2, 3, 4]中，删除并且返回1！")
                .that(lld1.toList())
                .containsExactly(2, 3, 4)
                .inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        // 1. 空列表测试
        assertThat(lld1.removeLast()).isNull();

        // 2. 填充数据
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        // 状态: [1, 2, 3]

        // 3. 测试删除最后一个
        assertWithMessage("错误！removeLast 应该返回被删除的最后一个元素 3")
                .that(lld1.removeLast())
                .isEqualTo(3);

        // 4. 测试剩余状态
        assertWithMessage("错误！删除 3 后，列表剩余元素不对")
                .that(lld1.toList())
                .containsExactly(1, 2)
                .inOrder();
    }

}