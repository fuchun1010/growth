package com.tank.renew.my20210412;

import com.alibaba.testable.core.annotation.MockDiagnose;
import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.matcher.InvokeVerifier;
import com.alibaba.testable.core.model.LogLevel;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@EnablePrivateAccess
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloTest {

  @MockDiagnose(LogLevel.VERBOSE)
  public static class Mock {
    @MockMethod(targetClass = Person.class, targetMethod = "obtainAge")
    private int obtainAge() {
      return 22;
    }
  }

  @Test
  void runHello() {
    val person = new Person();
    val result = this.hello.sayHello(person);
    Assertions.assertEquals("hello:22", result);
    InvokeVerifier.verify("obtainAge").withTimes(1);
  }

  @BeforeEach
  void init() {
    this.hello = new Hello();
  }

  private Hello hello = null;


}