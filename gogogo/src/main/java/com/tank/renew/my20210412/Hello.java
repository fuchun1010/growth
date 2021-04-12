package com.tank.renew.my20210412;

import java.util.Arrays;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class Hello {

  public String sayHello(Person person) {
    return "hello:" + person.obtainAge();
  }

  public List<String> list() {
    return Arrays.asList("a", "b", "c");
  }

}
