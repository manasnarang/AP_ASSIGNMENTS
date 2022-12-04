package org.example;

import java.util.Objects;

public class Node{
    private Node left;
    private Node right;
    private long value;
    private int height;
    public Node(long value) {
        this.value = value;
        this.left=null;
        this.right=null;
        this.height=1;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getRight() {
        return this.right;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getLeft() {
        return this.left;
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value && Objects.equals(left, node.left) && Objects.equals(right, node.right);
    }
}
