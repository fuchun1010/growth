package com.tank.renew.my20210322;

import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

@DisplayName("泛型数组测试")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArrayDefTest {

  @Test
  @DisplayName("数组更新")
  void update() {
    IntStream.rangeClosed(1, 6).boxed().forEach(this.arrayDef::add);
    this.arrayDef.update(0, 9);
    Integer value = this.arrayDef.obtain(0);
    Assertions.assertEquals(value.intValue(), 9);
    this.arrayDef.print();
  }

  @Test
  @DisplayName("添加数组元素")
  void add() {
    Assertions.assertNotNull(this.arrayDef);
    IntStream.rangeClosed(1, 6).boxed().forEach(this.arrayDef::add);
    Assertions.assertEquals(this.arrayDef.size(), 6);
    this.arrayDef.print();
  }

  @Test
  @DisplayName("删除元素")
  void delete() {
    IntStream.rangeClosed(1, 6).boxed().forEach(this.arrayDef::add);
    Assertions.assertEquals(this.arrayDef.size(), 6);
    this.arrayDef.delete(1);
    Assertions.assertEquals(this.arrayDef.size(), 5);
    this.arrayDef.print();
    this.arrayDef.delete(5);
    Assertions.assertEquals(this.arrayDef.size(), 4);
    this.arrayDef.print();
  }

  @Test
  @DisplayName("测试获取元素")
  void obtain() {
    IntStream.rangeClosed(1, 6).boxed().forEach(this.arrayDef::add);
    Integer obtain = this.arrayDef.obtain(0);
    Assertions.assertEquals(obtain.intValue(), 1);
  }

  @Test
  @DisplayName("数组长度")
  void size() {
    IntStream.rangeClosed(1, 6).boxed().forEach(this.arrayDef::add);
    Assertions.assertEquals(this.arrayDef.size(), 6);
  }

  @BeforeEach
  void init() {
    this.arrayDef = new ArrayDef<>(Integer.class);
  }

  private ArrayDef<Integer> arrayDef;
}