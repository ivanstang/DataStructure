package club.intworld.DataStructure.queue;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Object;

@Slf4j
public class ArrayQueue<T> {
    T[] items;
    int n;              //队列长度
    int head;           //队列头指针
    int tail;           //队列尾指针

    public ArrayQueue(int n) {
        this.n = n;
        this.head = 0;
        this.tail = 0;
        this.items = (T[]) new Object[n];
    }

    public boolean enqueue(T item) {
        if (tail == n) {
            if (head == 0) {
                log.warn("队列已满，无法入队！");
                return false;
            }
            //队列前部有空余空间，做数据搬移
            for (int i = head; i < n; i++) {
                items[i-head] = items[i];
            }
            tail = n - head;
            head = 0;
        }
        items[tail] = item;
        tail ++;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        T item = items[head];
        head ++;
        return item;
    }
}
