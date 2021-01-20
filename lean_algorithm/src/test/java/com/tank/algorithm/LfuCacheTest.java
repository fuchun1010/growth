package com.tank.algorithm;

import cn.hutool.cache.impl.LFUCache;
import com.google.common.collect.Lists;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LfuCacheTest {

  @RepeatedTest(20)
  void obtain() {
    val result = this.lfuCache.obtain("s0001", Member::getId);
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.isDefined());
    Assertions.assertEquals("jack", result.getOrElseTry(Member::new).getName());
  }

  @BeforeAll
  void post() {
    this.lfuCache = new LfuCache<>(1 << 4);
    val m1 = new Member();
    m1.setGender(1);
    m1.setId("s0001");
    m1.setName("jack");

    val m2 = new Member();
    m2.setGender(1);
    m2.setId("s0002");
    m2.setName("jhon");

    this.members.add(m1);
    this.members.add(m2);

    this.lfuCache.initialize(this.members, Member::getId);
  }

  private LfuCache<String, Member> lfuCache = null;

  private final List<Member> members = Lists.newArrayList();

  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = false, of = {"id"})
  private static class Member {
    String id;
    String name;
    int gender;
  }

}