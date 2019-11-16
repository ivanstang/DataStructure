package club.intworld.DataStructure.queue;

import club.intworld.DataStructure.list.Node;
import club.intworld.DataStructure.list.SingleLinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinkedQueue<T> {
    int n;
    Node<T> tail;
    Node<T> head;

    public LinkedQueue(int n) {
        this.n = n;
        this.head = null;
        this.tail = null;
    }

    public boolean enqueue(T item) {
        if (head == null) {
            log.warn("队列已满，无法入队！");
            return false;
        }
        if (head == null) {
            head = new Node<T>(item);
            tail = head;
        } else {
            Node<T> node = new Node<T>(item);
            tail.next = node;
            tail = node;
        }
        return true;
    }

    public T dequeue() {
        if (head == null) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        T item = head.data;
        head = head.next;
        return item;
    }
}
