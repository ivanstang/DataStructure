package club.intworld.DataStructure.LinkedList;

import java.io.Serializable;

public class Node<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    E data;
    Node<E> next;

    Node() {
        this.next = null;
    }

    Node(E data) {
        this.next = null;
        this.data = data;
    }
}
