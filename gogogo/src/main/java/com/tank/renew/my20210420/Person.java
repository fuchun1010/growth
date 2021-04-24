package com.tank.renew.my20210420;

import cn.hutool.core.util.StrUtil;
import lombok.*;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@AllArgsConstructor
@NonNull
public class Person {

  public String sayHello(@NonNull final String word) {
    return StrUtil.format("hello,{}", word);
  }

  @SneakyThrows(IllegalAccessException.class)
  public int incrementSalary(@NonNull Integer extra) {
    if (extra.compareTo(0) <= 0) {
      throw new IllegalAccessException("increment salay not less than zero");
    }
    this.salary += extra;
    return this.salary;
  }

  private Integer salary;

  private String name;

  private String birth;


}
