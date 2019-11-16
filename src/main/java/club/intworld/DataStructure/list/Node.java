package club.intworld.DataStructure.list;

import java.io.Serializable;

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
}
