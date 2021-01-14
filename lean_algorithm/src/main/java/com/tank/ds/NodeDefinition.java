package com.tank.ds;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Setter
@Getter
public class NodeDefinition<T> {

  public NodeDefinition(final T data) {
    this(null, data, null);
  }

  public NodeDefinition(NodeDefinition<T> left, final T data, NodeDefinition<T> right) {
    this.left = left;
    this.data = data;
    this.right = right;
  }

  public T data;
  public NodeDefinition<T> left;
  public NodeDefinition<T> right;

}
