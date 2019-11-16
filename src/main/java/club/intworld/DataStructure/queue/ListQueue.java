package club.intworld.DataStructure.queue;

import club.intworld.DataStructure.list.Node;
import club.intworld.DataStructure.list.SingleLinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListQueue<T> {
    SingleLinkedList<T> list;
    int n;
    int count;
    Node tail;

    public ListQueue(int n) {
        this.n = n;
        this.count = 0;
        this.list = new SingleLinkedList();
    }

    public boolean enqueue(String item) {
        if (count == n) {
            log.warn("队列已满，无法入队！");
            return false;
        }
        list.addNode(new Node(item));
        count ++;
        return true;
    }

    public T dequeue() {
        if (this.count == 0) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        T item = list.head.data;
        list.head = list.head.next;
        count --;
        return item;
    }
}
