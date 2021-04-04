package com.tank.renew.my20210404;

/**
 * @param <T>
 * @param <I>
 * @author tank198435163.com
 */
public interface TreeDef<T, I extends AbTreeNode<T>> {

  /**
   * add node to parentNode
   *
   * @param parentNode
   * @param node
   */
  void addNode(final I parentNode, final I node);

  void print();

  int size();

}
