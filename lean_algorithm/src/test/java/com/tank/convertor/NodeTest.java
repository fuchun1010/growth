package com.tank.convertor;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NodeTest {

  @Test
  void addItem() {
    Assertions.assertNotNull(this.tree);
    Arrays.asList(this.r1, this.r2, this.r3, this.r4, this.r5, this.r6).forEach(this.tree::addItem);
    val result = this.tree.find(this.r2.getId());
    Assertions.assertTrue(result.isDefined());
  }

  @Test
  void findNode() {
    Assertions.assertNotNull(this.tree);
    val result = this.tree.findInsertNode(this.r1);
    Assertions.assertFalse(result.isDefined());
  }

  @BeforeEach
  void initialize() {
    this.r1 = new Leaf();
    this.r1.setDesc("渠道");
    this.r1.setId(1);
    this.r1.setParentId(0);

    this.r2 = new Leaf();
    this.r2.setDesc("自营");
    this.r2.setId(2);
    this.r2.setParentId(1);

    this.r3 = new Leaf();
    this.r3.setDesc("有赞");
    this.r3.setId(3);
    this.r3.setParentId(2);

    this.r4 = new Leaf();
    this.r4.setDesc("pos");
    this.r4.setId(4);
    this.r4.setParentId(2);

    this.r5 = new Leaf();
    this.r5.setDesc("美团");
    this.r5.setId(5);
    this.r5.setParentId(1);

    this.r6 = new Leaf();
    this.r6.setId(6);
    this.r6.setDesc("有赞1");
    this.r6.setParentId(3);

    this.tree = new Node<>();
  }


  private Leaf r1, r2, r3, r4, r5, r6;

  /**
   * id  channel  desc  parent
   * 1   1000     渠道        0
   * 2   1001     自营        1
   * 3   1002     有赞        2
   * 4   1003     pos         2
   * 5   1004     美团        1
   */

  private Node<AbstractItem> tree;
}