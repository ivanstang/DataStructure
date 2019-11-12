package club.intworld.DataStructure.LinkedList;

import java.io.Serializable;

public class Node<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    public E data;
    public Node<E> next;

    public Node() {
        this.next = null;
    }
}
