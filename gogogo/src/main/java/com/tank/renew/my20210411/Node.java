package com.tank.renew.my20210411;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @param <Integer>
 * @author tank198435163.com
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"data"})
public class Node<Integer> {

  public Node() {
    super();
  }

  public Node(@NonNull final Integer data) {
    this.data = data;
  }

  private Node<Integer> next;

  private Integer data;

}
