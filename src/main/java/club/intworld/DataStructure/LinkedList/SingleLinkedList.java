package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleLinkedList {
    public int size;
    public Node head;

    public SingleLinkedList() {
        this.head = new Node();
        this.size = 0;
    }

    /* 打印链表内容 */
    public void printList() {
        log.info("该链表内共有{}个结点", size);

        Node temp = head;
        int i = 0;
        while (temp.next != null) {
            temp = temp.next;
            i++;
            log.info("第{}个结点，值为{}",i,temp.data);
        }
    }

    /* 在链表尾部增加结点 */
    public void addNode(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        this.size++;
    }

    /* 在链表指定位置插入结点 */
    public void insertNode(Node node, int index) {
        if (index > size || index < 1) {
            log.info("该链表内共有{}个结点", size);
            log.info("输入的位置已超出当前链表的范围");
            return;
        }
        Node temp = head;
        int i = 0;
        while (temp.next != null && i < (index-1)) {
            temp = temp.next;
            i++;
        }
        node.next = temp.next;
        temp.next = node;
        this.size++;
    }
}
