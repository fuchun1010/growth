package com.tank.renew.my20210429;

import com.tank.renew.my20210404.LeafNode;
import com.tank.renew.my20210404.TreeNode;
import lombok.val;
import org.junit.jupiter.api.*;

@DisplayName("获取树的深度")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeDepthTest {

  @Test
  @DisplayName("深度计算")
  void depth() {
    val root = new TreeNode<Integer>();
    root.setData(1);
    val left = new LeafNode<Integer>();
    left.setData(2);
    root.getChildren().add(left);

    val right = new TreeNode<Integer>();
    right.setData(3);

    val rightLeft = new LeafNode<Integer>();
    rightLeft.setData(4);

    right.getChildren().add(rightLeft);

    val rr = new LeafNode<Integer>();
    rr.setData(5);

    right.getChildren().add(rr);

    root.getChildren().add(right);

    val depth = this.treeDepth.depth(root);
    Assertions.assertTrue(depth > 0);
    Assertions.assertEquals(depth, 3);
  }


  @BeforeEach
  void initialize() {
    this.treeDepth = new TreeDepth();
  }

  private TreeDepth treeDepth;
}