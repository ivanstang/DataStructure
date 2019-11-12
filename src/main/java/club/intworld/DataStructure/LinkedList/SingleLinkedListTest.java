package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SingleLinkedListTest implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SingleLinkedList list = createList();
        list.printList();
        if(isHuiWen(list)) {
            log.info("是回文");
        } else {
            log.info("不是回文");
        }
    }

    private SingleLinkedList createList() {
        Node node1 = new Node();
        node1.data = "l";
        SingleLinkedList list = new SingleLinkedList(node1);

        Node node2 = new Node();
        node2.data = "e";
        list.addNode(node2);

        Node node3 = new Node();
        node3.data = "v";
        list.addNode(node3);

        Node node4 = new Node();
        node4.data = "a";
        list.addNode(node4);

        Node node5 = new Node();
        node5.data = "l";
        list.addNode(node5);

        return list;
    }

    private boolean isHuiWen(SingleLinkedList list) throws IOException, ClassNotFoundException {
        if (list == null) {
            log.info("链表不存在！");
            return false;
        }
        if (list.size == 1 ) {
            return true;
        }

        //对链表做深度复制和反转
        SingleLinkedList reverse = list.deepCopy().reverse();

        //计算需要比较对次数
        int times = list.size/2;

        //比较两个链表的数据
        Node head1 = list.head;
        Node head2 = reverse.head;
        for (int i = 0; i < times; i++) {
            if (!head1.data.equals(head2.data)) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }
}
