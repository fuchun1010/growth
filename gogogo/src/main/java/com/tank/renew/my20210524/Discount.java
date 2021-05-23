package com.tank.renew.my20210524;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class Discount {

  private String sku;

  private int threshold;

  private int subValue;

  private boolean enabled = true;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("sku", sku)
            .add("threshold", threshold)
            .add("subValue", subValue)
            .toString();
  }
}
