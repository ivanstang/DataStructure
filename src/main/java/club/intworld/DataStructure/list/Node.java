package club.intworld.DataStructure.list;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class Node<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public T data;
    public Node<T> next;

    public Node() {
        this.next = null;
    }

    public Node(T data) {
        this.next = null;
        this.data = data;
    }

    /* 在链表尾添加结点 */
    public void addData(T data) {
        Node temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
        return;
    }

    /* 在链表头添加结点 */
    public void addDataAsHead(T data) {
        Node node = new Node(this.data);
        node.next = this.next;
        this.data = data;
        this.next = node;
    }

    /* 检测链表内是否有循环 */
    public boolean hasLoop() {
        Set<Node> set = new HashSet<Node>();
        Node temp = this;
        int i = 0;
        while (temp != null) {
            if (set.contains(temp)) {
                log.warn("链表内有循环，在第{}个结点",i);
                return true;
            }
            i ++;
            set.add(temp);
            temp = temp.next;
        }
        log.info("链表内无循环");
        return false;
    }

    /* 打印链表内容 */
    public void print() {
        log.info("");
        log.info("开始输出链表内容");
        Node temp = this;
        int i = 1;
        while (temp != null) {
            log.info("结点{}：{}", i, temp.data);
            temp = temp.next;
            i ++;
        }
        log.info("");
    }

    /* 在链表指定位置删除结点 */
    public boolean deleteNodeAtIndex(int index) {
        //删除头结点
        if (index == 1) {
            if (this.next != null) {
                this.data = this.next.data;
                this.next = this.next.next;
                return true;
            } else {
                log.warn("链表仅剩最后1个结点，不能删除");
                return false;
            }
        }

        //删除其他结点
        Node temp = this;
        for (int i = 0; i < index-2; i++) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            return true;
        }  else {
            log.info("指定的位置不存在结点");
            return false;
        }
    }

    /* 在链表指定位置插入数据 */
    public boolean insertDataAt(int index, T data) {
        //在链表头插入数据
        if (index == 1) {
            this.addDataAsHead(data);
            return true;
        }
        Node temp = this;
        for (int i = 0; i < index-2; i++) {
            if (temp.next == null) {
                log.warn("指定的位置错误");
                return false;
            }
            temp = temp.next;
        }
        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
        return true;
    }

    /* 反转（倒排）链表 */
    public Node reverse() {
        Node prev = null;
        Node next;
        Node temp = this;
        while (temp != null) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }

    /* 反转（倒排）链表 */
    public Node reverse(int m, int n) {
        if (m == n) {
            return this;
        }

        if (m > n) {
            int temp = n;
            n = m;
            m = temp;
        }

        Node prev = null;
        Node next;
        Node start = this;
        Node begin = start;
        Node temp = start;
        Node end = null;
        Node end1 = null;
        for (int i = 0; i < n; i++) {
            if (i < m-1) {
                temp = begin.next;
                if (i < m-2) {
                    begin = begin.next;
                }
            }
            if (i >= m-1) {
                if (i == m-1) {
                    end1 = temp;
                }
                next = temp.next;
                end = temp.next;
                temp.next = prev;
                prev = temp;
                temp = next;
            }
        }

        if (m == 1) {
            start = prev;
        } else {
            begin.next = prev;
        }
        if (end != null) {
            end1.next = end;
        }

        return start;
    }
}
