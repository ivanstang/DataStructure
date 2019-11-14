package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import java.util.Random;

@Component
@Slf4j
public class SingleLinkedListLruTest implements CommandLineRunner {
    int used = 0;
    SingleLinkedList lru;
    int volume;

    @Override
    public void run(String... args) throws Exception {
        //设置单链表缓存的容量
        volume = 20;

        for (int i = 0; i < 20; i++) {
            int data = new Random().nextInt(100);
            addToList(data);
            log.info("数据 {} 已添加到缓存！", data);
        }
        int data = 101;
        addToList(data);
        log.info("新数据 {} 已添加到缓存！", data);
        lru.printList();
//        createListForLru(20,19);
//        int data = 10;
//        int index = searchList(data);
//        latest = 2;
//        addToList(110);
//        addToList(111);
    }

//    private void createListForLru(int listSize, int usedSize) {
//        Node head = new Node();
//        head.data = new Random().nextInt(100);
//        lru = new SingleLinkedList(head);
//        used = 1;
//        latest = 1;
//
//        //LRU 链表共20个结点
//        for (int i = 1; i < listSize; i++) {
//            Node node = new Node();
//            if (used < usedSize) {
//                node.data = new Random().nextInt(100);
//                used++;
//                latest++;
//            }
//            lru.addNode(node);
//        }
//        lru.printList();
//    }

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
        if (result == -1) {             //未在链表内找到数据
            if (used < volume) {      //链表未满, O(1)
//                for (int i = 0; i < lru.size; i++) {
//                    if (head.data == null) {
//                        log.info("the latest node is at {}", latest);
//                        log.info("didn't find data {} in cache, add the data at the end of the list!", data);
//                        head.data = data;
//                        used++;
//                        latest = i+1;
//                        break;
//                    }
//                    head = head.next;
//                }
                lru.addNodeAtFirst(node);
                log.info("add the data {} at the first!", data);
            } else {                //链表已满, O(n)
                int j = 0;
                for (int i = 1; i < volume; i++) {
                    head = head.next;
                    j++;
                }
                log.info("remove the latest data in the cache: position {}, data {}", j, head.next.data);
                head.next = null;
                lru.addNodeAtFirst(node);
                log.info("add the data {} at the first!", data);
            }
        } else {
            log.info("to do");
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
