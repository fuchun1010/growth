package com.tank.renew.my20210404;

import lombok.val;
import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("多叉树测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultiNodeTreeTest {

  @Test
  @DisplayName("添加节点")
  void addNode() {

    TreeNode<String> node_a = new TreeNode<>();
    node_a.setData("a".toUpperCase());

    this.treeDef.addNode(null, node_a);

    val node_b = new TreeNode<String>();
    node_b.setData("b".toUpperCase());
    this.treeDef.addNode(node_a, node_b);

    val node_c = new LeafNode<String>();
    node_c.setData("c".toLowerCase());
    this.treeDef.addNode(node_b, node_c);

    val node_d = new LeafNode<String>();
    node_d.setData("d".toLowerCase());
    this.treeDef.addNode(node_b, node_d);

    val node_m = new TreeNode<String>();
    node_m.setData("m".toUpperCase());
    this.treeDef.addNode(node_a, node_m);

    Assertions.assertEquals(5, this.treeDef.size());

  }

  @Test
  @DisplayName("非递归打印测试")
  void print() {
    TreeNode<String> node_a = new TreeNode<>();
    node_a.setData("a".toUpperCase());

    this.treeDef.addNode(null, node_a);

    val node_b = new TreeNode<String>();
    node_b.setData("b".toUpperCase());
    this.treeDef.addNode(node_a, node_b);

    val node_c = new LeafNode<String>();
    node_c.setData("c".toUpperCase());
    this.treeDef.addNode(node_b, node_c);

    val node_d = new LeafNode<String>();
    node_d.setData("d".toUpperCase());
    this.treeDef.addNode(node_b, node_d);

    val node_m = new TreeNode<String>();
    node_m.setData("m".toUpperCase());
    this.treeDef.addNode(node_a, node_m);
    Assertions.assertNotNull(this.treeDef);
    this.treeDef.print();

  }

  @BeforeEach
  void initialize() {
    this.treeDef = new MultiNodeTree<>();
  }

  private TreeDef<String, AbTreeNode<String>> treeDef;
}