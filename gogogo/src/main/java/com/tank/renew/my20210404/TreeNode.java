package com.tank.renew.my20210404;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @param <T>
 * @author tank198435163.com
 */
@Getter
@Setter
public class TreeNode<T> extends AbTreeNode<T> {

  private List<AbTreeNode<T>> children = Lists.newArrayList();
}
