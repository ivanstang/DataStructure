package club.intworld.DataStructure.list;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

//@Component
@Slf4j
public class SingleLinkedListTest implements CommandLineRunner {
    @Override
    public void run(String... args)  {
        testDeleteFromEnd();
    }

    private void testDelete() {
        SingleLinkedList list = createList(new int[]{10, 5, 6});
        list.deleteNode(2);
        list.printList();
    }

    private void testMoveNode() {
        SingleLinkedList list = createList(new int[]{10, 66, 55, 44});

        list.printList();
        list.moveNode(3,2);
        list.printList();
    }

    private void testLoopDetect() {
        Node head = new Node();
        head.data = 10;
        SingleLinkedList list = new SingleLinkedList(head);

        Node node = new Node();
        node.data = 66;
        list.addNode(node);

        Node node1 = new Node();
        node1.data = 55;
        list.addNode(node1);

        Node node2 = new Node();
        node2.data = 44;
//        node2.next = head;
        list.addNode(node2);

        if (list.hasLoop()) {
            log.info("the list has loop");
        } else {
            log.info("the list has not loop");
        }
    }

    private void testCombine() {
        long start = System.currentTimeMillis();
        SingleLinkedList list1 = createList(new int[]{0, 10, 20, 30, 40, 50});
        SingleLinkedList list2 = createList(new int[]{15, 20, 25, 31});
        for (int i = 0; i < 10000; i++) {
            Node node = new Node();
            node.data = new Random().nextInt(10000);
            list1.addNode(node);

//            Node node2 = new Node(new Random().nextInt(10000));
//            list2.addNode(node2);
        }
        long end = System.currentTimeMillis();
        log.info("执行了 {} 毫秒", end - start);
//        start = System.currentTimeMillis();
//        SingleLinkedList list3 = list1.combineOrderedList2(list2);
//        end = System.currentTimeMillis();
//        log.info("执行了 {} 毫秒", end - start);
        //list3.printList();
    }

    private void testCenterNode() {
        SingleLinkedList list = createList(new int[]{0, 60, 20, 40, 50});
        Node node = list.findCenterNode();
        log.info("链表的中间结点是：{}",node.data);
    }

    private void testDeleteFromEnd() {
        SingleLinkedList list = createList(new int[]{0, 60, 20, 40, 50});
        list.deleteIndexFromEnd(6);
        list.printList();
    }

    private void testSort() {
        SingleLinkedList list = createList(new int[]{0, 60, 20, 15, 40, 50});
        list.sortByAsc();
        list.printList();
    }

    private SingleLinkedList createList(int[] data) {
        if (data == null) {
            return null;
        }
        SingleLinkedList list = null;

        for (int i = 0; i < data.length; i++) {
            Node node = new Node();
            node.data = data[i];
            if (i == 0) {
                list = new SingleLinkedList(node);
            } else {
                list.addNode(node);
            }
        }
        return list;
    }
}
