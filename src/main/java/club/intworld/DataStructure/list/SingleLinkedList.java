package club.intworld.DataStructure.list;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SingleLinkedList implements Serializable {
    private static final long serialVersionUID = 1L;

    int size;
    public Node head;

    public SingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    SingleLinkedList(Node head) {
        this.head = head;
        this.size = 1;
    }

    SingleLinkedList deepCopy() throws IOException, ClassNotFoundException {
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
    void printList() {
        if (this.hasLoop()) {
            log.warn("该链表中有死循环，无法进行操作！");
            return;
        }

        log.info("该链表内共有{}个结点", size);

        Node temp = head;
        int i = 1;
        while (temp != null) {
            log.info("第{}个结点，值为{}",i,temp.data);
            temp = temp.next;
            i++;
        }
    }

    /* 在链表尾部增加结点
    *  时间复杂度O(n2)
    */
    void addNode(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        this.size++;
    }

    /* 在链表头部增加结点
    *  时间复杂度O(1)
    */
    public void addNodeAtFirst(Node node) {
        if (this.head == null) {
            this.head = node;
            return;
        }
        node.next = head;
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

    /* 在链表指定位置删除结点 */
    void deleteNode(int index) {
        if (index > size || index < 1) {
            log.info("该链表内共有{}个结点", size);
            log.info("输入的位置 {} 已超出当前链表的范围",index);
            return;
        }
        if (size == 1) {
            log.info("该链表内仅剩下一个结点了，不能被删除！");
            return;
        }
        Node temp = head;
        Node prev = null;
        for (int i = 1; i < index; i++) {
            prev = temp;
            temp = temp.next;
        }
        if (prev == null) {     //删除的是头结点
            this.head = temp.next;
        } else {
            prev.next = temp.next;
        }
        this.size--;
    }

    /* 删除链表倒数第n个结点 */
    void deleteIndexFromEnd(int indexFromEnd) {
        this.deleteNode(this.size-indexFromEnd+1);
    }

    /* 查找指定位置的结点
    *  返回两个Node：
    *       第1个是指定位置之前的结点
    *       第2个是指定位置的结点
    *       时间复杂度O(n)
    */
    private Node[] findNode(int index) {
        Node temp = this.head;
        Node prev = null;
        for (int i = 1; i < index; i++) {
            prev = temp;
            temp = temp.next;
        }
        return new Node[]{prev,temp};
    }

    /* 查找链表的中间结点 */
    Node findCenterNode() {
        Node temp = this.head;
        for (int i = 0; i < this.size/2; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /* 移动指定结点至指定位置 */
    void moveNode(int from, int to) {
        if (from == to) {
            return;
        }
        if (from <= 0 || to <= 0 || from > this.size || to > this.size) {
            log.info("参数超出当前链表的范围");
            return;
        }
        Node[] fromNodes = findNode(from);
        Node[] toNodes = findNode(to);

        if (from < to) {
            //移走
            if (fromNodes[0] == null) {
                this.head = fromNodes[1].next;
            } else {
                fromNodes[0].next = fromNodes[1].next;
            }

            //插入
            fromNodes[1].next = toNodes[1].next;
            toNodes[1].next = fromNodes[1];
        } else {
            //移走
            fromNodes[0].next = fromNodes[1].next;

            //插入
            if (toNodes[0] == null) {
                this.head = fromNodes[1];
            } else {
                toNodes[0].next = fromNodes[1];
            }
            fromNodes[1].next = toNodes[1];
        }
    }

    /* 转置（倒排）链表 */
    void reverse() {
        Node prev = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        this.head = prev;
        List<Integer> list = new ArrayList<>();
        list.size();
    }

    /* 检查链表中是否有环 */
    boolean hasLoop() {
        Node temp = head;
        int i = 1;
        while (temp != null) {
            if (i > size) {
                return true;
            }
            temp = temp.next;
            i++;
        }
        return false;
    }

    /* 按照从小到大排序链表
    *  时间复杂度O(n2)
    */
    void sortByAsc() {
        if (this.size < 2) {
            return;
        }
        int k = 0;
        Node temp1 = this.head;
        while (temp1 != null) {
            Node temp2 = temp1.next;
            while (temp2 != null) {
                if ((int) temp1.data > (int) temp2.data) {
                    int temp = (int) temp1.data;
                    temp1.data = temp2.data;
                    temp2.data = temp;
                }
                temp2 = temp2.next;
                k ++;
            }
            temp1 = temp1.next;
        }
        log.info("执行了 {} 次", k);
    }

    /* 合并两个有序链表
    *  通过新建链表来实现
    *  时间复杂度O(m+n)
    * */
    SingleLinkedList combineOrderedList(SingleLinkedList list) {
        Node head = new Node();
        SingleLinkedList combined = new SingleLinkedList(head);
        Node temp1 = this.head;
        Node temp2 = list.head;

        while (temp1 != null && temp2 != null) {
            if ((int)temp1.data <= (int)temp2.data) {
                Node node = new Node();
                node.data = temp1.data;
                combined.addNode(node);
                temp1 = temp1.next;
            } else {
                Node node = new Node();
                node.data = temp2.data;
                combined.addNode(node);
                temp2 = temp2.next;
            }
        }

        while (temp1 != null) {
            Node node = new Node();
            node.data = temp1.data;
            combined.addNode(node);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            Node node = new Node();
            node.data = temp2.data;
            combined.addNode(node);
            temp2 = temp2.next;
        }

        combined.head = combined.head.next;

        return combined;
    }

    /* 合并两个有序链表
     *  通过简单合并链表，并重新排序来实现
     *  时间复杂度O(n2)
     * */
    SingleLinkedList combineOrderedList2(SingleLinkedList list) {
        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = list.head;
        this.size = this.size + list.size;
        this.sortByAsc();
        return this;
    }
}
