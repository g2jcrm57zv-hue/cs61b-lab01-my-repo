import deque.Deque61B;
import org.junit.Test;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void testMax() {
        Comparator<Integer> c = Comparator.naturalOrder();

        MaxArrayDeque61B<Integer> maxArray = new MaxArrayDeque61B<>(c);

        maxArray.addFirst(10);
        maxArray.addLast(2);
        maxArray.addLast(4);
        maxArray.addLast(87);
        maxArray.addLast(13);
        maxArray.addLast(32);

        assertEquals((Integer) 87, maxArray.max());
    }
}
