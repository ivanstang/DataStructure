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

    public void addNode(T data) {
        Node temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
        return;
    }

    public Node addNodeAsHead(T data) {
        Node node = new Node(data);
        node.next = this;
        return node;
    }

    public boolean hasLoop() {
        Set<Node> set = new HashSet<Node>();
        Node temp = this;
        while (temp != null) {
            if (set.contains(temp)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void print() {
        Node temp = this;
        int i = 1;
        while (temp != null) {
            log.info("结点{}：{}", i, temp.data);
            temp = temp.next;
            i ++;
        }
    }
}
