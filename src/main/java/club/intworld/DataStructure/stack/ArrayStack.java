package club.intworld.DataStructure.stack;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArrayStack {
    private String[] items;     //存储栈数据的数组
    private int count;          //栈内元素个数
    private int n;              //栈容量

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(String item) {
        if (this.count == n) {
            log.warn("栈已满，无法压入数据！");
            return false;
        }
        this.count ++;
        this.items[count-1] = item;
        return true;
    }

    public String pop() {
        if (this.count == 0) {
            log.warn("栈已空，没有数据可以提取！");
            return null;
        }
        this.count --;
        return this.items[count];
    }
}
