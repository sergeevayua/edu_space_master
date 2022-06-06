package ru.edu.space.app.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Tree<T> {
    private List<Tree<T>> children = new ArrayList<>();
    private Tree<T> parent = null;
    private T data;

    public Tree(T data) {
        this.data = data;
    }

    public Tree(T data, Tree<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void addChild(Tree<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        Tree<T> newChild = new Tree<>(data);
        this.addChild(newChild);
    }

    public void removeChildren() {
        this.children = new ArrayList<>();
    }

    public void addChildren(List<Tree<T>> children) {
        for(Tree<T> t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    private void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tree<?> tree = (Tree<?>) object;
        return Objects.equals(children, tree.children) &&
                Objects.equals(data, tree.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, data);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tree{");
        sb.append("children=").append(children);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
