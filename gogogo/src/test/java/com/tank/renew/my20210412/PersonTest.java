package com.tank.renew.my20210412;

import com.alibaba.testable.core.annotation.MockDiagnose;
import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.matcher.InvokeVerifier;
import com.alibaba.testable.core.model.LogLevel;
import lombok.val;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonTest {

  @MockDiagnose(LogLevel.VERBOSE)
  static class Mock {

    @MockMethod(targetMethod = "list", targetClass = Hello.class)
    public List<String> all() {
      return Collections.singletonList("m");
    }
  }

  @Test
  @DisplayName("mock hello method")
  void testList() {
    val list = this.person.list(this.hello);
    Assertions.assertEquals(1, list.size());
    InvokeVerifier.verify("all").withTimes(1);
  }


  @BeforeEach
  void initialize() {
    this.person = new Person();
    this.hello = new Hello();
  }

  private Person person;

  private Hello hello;
}
