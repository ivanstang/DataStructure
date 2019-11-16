package club.intworld.DataStructure.stack;

import club.intworld.DataStructure.list.Node;
import club.intworld.DataStructure.list.SingleLinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListStack {
    SingleLinkedList list;
    int count;
    int n;

    public ListStack(int n) {
        this.list = new SingleLinkedList();
        this.count = 0;
        this.n = 0;
    }

    public boolean push(String item) {
        if (this.count >= this.n) {
            log.warn("栈已满，无法压入数据！");
            return false;
        }
        Node node = new Node(item);
        this.list.addNodeAtFirst(node);
        this.count ++;
        return true;
    }

    public String pop() {
        if (this.count == 0) {
            log.warn("栈已空，没有数据可以提取！");
            return null;
        }
        Node node = this.list.head;
        this.list.head = this.list.head.next;
        return (String) node.data;
    }
}
