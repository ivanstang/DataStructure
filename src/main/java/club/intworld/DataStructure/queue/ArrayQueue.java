package club.intworld.DataStructure.queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayQueue {
    String[] items;
    int n;              //队列长度
    int head;           //队列头指针
    int tail;           //队列尾指针

    public ArrayQueue(int n) {
        this.n = n;
        this.head = 0;
        this.tail = 0;
        this.items = new String[n];
    }

    public boolean enqueue(String item) {
        if (this.tail == this.n) {
            if (this.head == 0) {
                log.warn("队列已满，无法入队！");
                return false;
            }
            //队列前部有空余空间，做数据搬移
            for (int i = head; i < n; i++) {
                this.items[i-head] = this.items[i];
            }
            this.tail = n - head;
            this.head = 0;
        }
        this.items[tail] = item;
        this.tail ++;
        return true;
    }

    public String dequeue() {
        if (this.head == this.tail) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        String item = this.items[head];
        this.head ++;
        return item;
    }
}
