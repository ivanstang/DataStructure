package club.intworld.DataStructure.queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoopArrayQueue {
    String[] items;
    int n;              //队列长度
    int head;           //队列头指针
    int tail;           //队列尾指针

    public LoopArrayQueue(int n) {
        this.n = n;
        this.head = 0;
        this.tail = 0;
        this.items = new String[n];
    }

    public boolean enqueue(String item) {
        if ((head-tail == 1) || (head-tail == 1-n)) {
            log.warn("队列已满，无法入队！");
            return false;
        }
        items[tail] = item;
        if (tail == n-1) {
            tail = 0;
        } else {
            tail ++;
        }
        return true;
    }

    public String dequeue() {
        if (head == tail) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        String item = items[head];
        if (head == n-1) {
            head = 0;
        } else {
            head ++;
        }
        return item;
    }
}
