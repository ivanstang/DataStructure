package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SingleLinkedListTest implements CommandLineRunner {
    @Override
    public void run(String... args)  {
        testLoopDetect();
    }

    private void testDelete() {
        Node head = new Node();
        head.data = 10;
        SingleLinkedList list = new SingleLinkedList(head);
        Node node1 = new Node();
        node1.data = 5;
        list.addNode(node1);
        Node node2 = new Node();
        node2.data = 6;
        list.addNode(node2);
        list.deleteNode(2);
        list.printList();
    }

    private void testMoveNode() {
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
        list.addNode(node2);

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
        //node2.next = node1;
        list.addNode(node2);

        if (list.hasLoop()) {
            log.info("the list has loop");
        } else {
            log.info("the list has not loop");
        }
    }
}
