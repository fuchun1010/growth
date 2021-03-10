package com.tank.stream.pojo;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Word {

  private String str;

  private int cnt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Word word = (Word) o;
    return Objects.equal(str, word.str);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(str);
  }
}
