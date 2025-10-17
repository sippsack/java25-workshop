package pattern.matching.list.fp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {
    @Test
    void testFPLinkedList() {
        LinkedList<Integer> list = LinkedList.of(1, 2, 3);
        assertThat(list).asString().contains("[1] -> [2] -> [3] -> NIL");
        assertThat(LinkedList.head(list)).isEqualTo(1);
        assertThat(LinkedList.tail(list)).asString().contains("[2] -> [3] -> NIL");
        assertThat(LinkedList.contains(3, list)).isTrue();
        assertThat(LinkedList.contains(5, list)).isFalse();
    }

}