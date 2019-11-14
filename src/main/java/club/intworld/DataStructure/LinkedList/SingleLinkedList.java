package club.intworld.DataStructure.LinkedList;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class SingleLinkedList implements Serializable {
    private static final long serialVersionUID = 1L;

    public int size;
    public Node head;

    public SingleLinkedList(Node head) {
        this.head = head;
        this.size = 1;
    }

    public SingleLinkedList deepCopy() throws IOException, ClassNotFoundException {
        //序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (SingleLinkedList) ois.readObject();
    }

    /* 打印链表内容 */
    public void printList() {
        log.info("该链表内共有{}个结点", size);

        Node temp = head;
        int i = 1;
        while (temp != null) {
            log.info("第{}个结点，值为{}",i,temp.data);
            temp = temp.next;
            i++;
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

    /* 在链表头部增加结点 */
    public void addNodeAtFirst(Node node) {
        Node temp = head;
        node.next = temp;
        this.head = node;
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

    /* 转置（倒排）链表 */
    public SingleLinkedList reverse() {
        Node prev = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        this.head = prev;
        return this;
    }
}
