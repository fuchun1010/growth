package com.tank.stream.my20210407;

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
public class ItemViewCount {

  private Long itemId;

  private Long counter;

  private Long timestamp;

  @Override
  public String toString() {
    return "ItemViewCount{" +
            "itemId=" + itemId +
            ", counter=" + counter +
            ", timestamp=" + timestamp +
            '}';
  }
}
