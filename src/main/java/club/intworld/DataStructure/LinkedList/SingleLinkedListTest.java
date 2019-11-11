package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SingleLinkedListTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SingleLinkedList list = createList();
        isHuiWen(list);
    }

    private SingleLinkedList createList() {
        SingleLinkedList list = new SingleLinkedList();
        Node node1 = new Node();
        node1.data = "l";
        list.addNode(node1);

        Node node2 = new Node();
        node2.data = "e";
        list.addNode(node2);

        Node node3 = new Node();
        node3.data = "v";
        list.addNode(node3);

        Node node4 = new Node();
        node4.data = "e";
        list.addNode(node4);

        Node node5 = new Node();
        node5.data = "l";
        list.addNode(node5);

        return list;
    }

    private void isHuiWen(SingleLinkedList list) {
        if (list.size < 2) {
            log.info("链表的长度过小，无法判断是否是回文！");
        }
        int s = 1;
        int f = 1;
        int times = (list.size-1)/2;

        Node prev = list.head;
        Node slow = prev.next;     //到达第一个结点
        Node next = slow.next;
        for (int i = 0; i < 1; i++) {
            slow.next = prev;
            prev = slow;
            slow = next;
            next = slow.next;
        }

        for (int i = 0; i < 1; i++) {
            slow.next = next;
            next = slow;
            slow = prev;


        }
        prev.next = slow;
        list.printList();
    }
}
