package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Element;
import java.util.Random;

@Component
@Slf4j
public class LruTest implements CommandLineRunner {
    int used = 0;
    SingleLinkedList lru;
    int latest = 0;

    @Override
    public void run(String... args) throws Exception {
        createListForLru(20,19);
        int data = 10;
        int index = searchList(data);
        latest = 2;
        addToList(110);
        addToList(111);
    }

    private void createListForLru(int listSize, int usedSize) {
        Node head = new Node();
        head.data = new Random().nextInt(100);
        lru = new SingleLinkedList(head);
        used = 1;
        latest = 1;

        //LRU 链表共20个结点
        for (int i = 1; i < listSize; i++) {
            Node node = new Node();
            if (used < usedSize) {
                node.data = new Random().nextInt(100);
                used++;
                latest++;
            }
            lru.addNode(node);
        }
        lru.printList();
    }

    private void addToList(int data) {
        int result = searchList(data);
        Node head = lru.head;
        if (result == -1) {             //未在链表内找到数据
            if (used < lru.size) {      //链表未满, O(n)
                for (int i = 0; i < lru.size; i++) {
                    if (head.data == null) {
                        log.info("the latest node is at {}", latest);
                        log.info("didn't find data {} in cache, add the data at the end of the list!", data);
                        head.data = data;
                        used++;
                        latest = i+1;
                        break;
                    }
                    head = head.next;
                }
                log.info("the latest node is at {}", latest);
            } else {
                for (int i = 1; i < latest-1; i++) {
                    head = head.next;
                }
                log.info("the latest node is at {}", latest);
                log.info("didn't find data {} in cache, and the cache is full, insert it at position {}", data, latest-1);
                head.data = data;
                latest--;
                log.info("the latest node is at {}", latest);
            }
        }
        log.info("the updated list is:");
        lru.printList();
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
