package club.intworld.DataStructure.queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayQueue {
    String[] items;
    int n;
    int count;

    public ArrayQueue(int n) {
        this.n = n;
        this.count = 0;
        this.items = new String[n];
    }

    public boolean enqueue(String item) {
        if (this.count >= this.n) {
            log.warn("队列已满，无法入队！");
            return false;
        }
        this.items[count] = item;
        this.count ++;
        return true;
    }

    public String dequeue() {
        if (this.count == 0) {
            log.warn("队列已空，无数据可以出队");
            return null;
        }
        String item = this.items[0];
        this.count --;
        if (count >= 0) {
            System.arraycopy(this.items, 1, this.items, 0, count);
        }
        return item;
    }
}
