package com.tank.stream.my20210407;

/**
 * implements Note
 * wrapper for customer behave
 */

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserBehave {

  private Long userId;

  private Long itemId;

  private Long categoryId;

  private String op;

  private Long timestamp;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(UserBehave.class)
            .add("userId", userId)
            .add("itemId", itemId)
            .add("categoryId", categoryId)
            .add("op", op)
            .add("timestamp", timestamp)
            .toString();
  }


}
