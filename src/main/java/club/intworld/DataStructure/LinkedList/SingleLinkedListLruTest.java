package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import java.util.Random;

//@Component
@Slf4j
public class SingleLinkedListLruTest implements CommandLineRunner {
    private int used = 0;
    private SingleLinkedList lru;
    private int volume;

    @Override
    public void run(String... args) {
        //设置单链表缓存的容量
        volume = 20;

        Node head = new Node();
        head.data = 10;
        lru = new SingleLinkedList(head);
        used = 1;

        for (int i = 0; i < volume/2; i++) {
            int data = new Random().nextInt(10000);
            addToList(data);
            log.info("数据 {} 已添加到缓存！", data);
        }
        int data1 = 10;
        addToList(data1);
        for (int i = 0; i < volume/2-1; i++) {
            int data = new Random().nextInt(10000);
            addToList(data);
            log.info("数据 {} 已添加到缓存！", data);
        }

        lru.printList();

        addToList(data1);

        lru.printList();

        //testDelete();
        //testMoveNode();
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

    private void addToList(int data) {
        Node node = new Node();
        node.data = data;
        if (lru == null) {
            lru = new SingleLinkedList(node);
            used = 1;
            return;
        }
        Node head = lru.head;
        int result = searchList(data);
        if (result == -1) {                 //未在链表内找到数据
            if (used < volume) {            //链表未满, 缓存使用量增加 O(1)
                used++;
            } else {                        //链表已满, 删除最后一个结点, O(n)
                lru.deleteNode(volume);
            }
            lru.addNodeAtFirst(node);
            log.info("将数据 {} 添加到链表头!", data);
        } else {                            //在链表内找到数据, 将找到的结点移至链表头部
            lru.moveNode(result,1);
            log.info("将数据为 {} 的结点从位置 {} 移动到链表头!", data, result);
        }
    }

    private int searchList(int data) {
        int index = 1;
        Node temp = lru.head;
        while (temp != null) {
            if (temp.data != null && temp.data.equals(data)) {
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }
}
