package com.tank.renew.my20210404;

import lombok.NonNull;
import lombok.val;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @param <I>
 * @author tank198435163.com
 */
public class MultiNodeTree<T, I extends AbTreeNode<T>> implements TreeDef<T, I> {


  @Override
  public void addNode(final I parentNode, @NonNull final I node) {
    this.counter.incrementAndGet();
    if (Objects.isNull(root)) {
      this.root = new TreeNode<>();
      this.root.setData(node.getData());
      this.next = this.root;
      return;
    }

    val target = this.enquire(this.next, parentNode);
    if (Objects.nonNull(target) && (target instanceof TreeNode)) {
      TreeNode<T> treeNode = ((TreeNode<T>) target);
      treeNode.getChildren().add(node);
    }
    this.next = this.root;

  }

  @Override
  public void print() {
    this.printData(this.next);
    for (AbTreeNode<T> child : this.next.getChildren()) {
      this.stack.push(child);
      while (!stack.isEmpty()) {
        val node = this.stack.pop();
        this.printData(node);
        if (node instanceof TreeNode) {
          TreeNode<T> target = ((TreeNode<T>) node);
          target.getChildren().forEach(this.stack::push);
        }
      }
    }
  }

  @Override
  public int size() {
    return counter.get();
  }

  private void printData(@NonNull final AbTreeNode node) {
    val data = node.getData();
    if (Objects.nonNull(data)) {
      System.out.println("data = " + data);
    }
  }

  private AbTreeNode<T> enquire(@NonNull TreeNode<T> startNode, @NonNull I parentNode) {
    if (startNode == parentNode || startNode.getData().equals(parentNode.getData())) {
      return startNode;
    }

    for (AbTreeNode<T> node : startNode.getChildren()) {
      if (node.getData().equals(parentNode.getData())) {
        return node;
      } else {
        if (node instanceof TreeNode) {
          final TreeNode<T> tmpNode = ((TreeNode<T>) node);
          val result = this.enquire(tmpNode, parentNode);
          if (Objects.nonNull(result)) {
            return result;
          }
        }
      }
    }

    return null;
  }

  private final AtomicInteger counter = new AtomicInteger(0);

  private TreeNode<T> root;

  private TreeNode<T> next;

  private final ArrayDeque<AbTreeNode<T>> stack = new ArrayDeque<>();
}
