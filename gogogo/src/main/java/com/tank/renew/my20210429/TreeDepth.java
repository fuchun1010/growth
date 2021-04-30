package com.tank.renew.my20210429;

import com.tank.renew.my20210404.AbTreeNode;
import com.tank.renew.my20210404.LeafNode;
import com.tank.renew.my20210404.TreeNode;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class TreeDepth {

  /**
   * 获取树的深度
   *
   * @return
   */
  public int depth(@NonNull final TreeNode<Integer> node) {
    currentDepth++;

    for (AbTreeNode<Integer> child : node.getChildren()) {
      currentDepth++;
      if (child instanceof LeafNode) {
        if (currentDepth > maxDepth) {
          maxDepth = currentDepth;
        }
        currentDepth = 0;
      } else {
        this.depth(((TreeNode<Integer>) child));
      }
    }


    return maxDepth;
  }


  private int maxDepth = 0;

  private int currentDepth = 0;
}
