package com.tank.renew.my20210523;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class Goods {

  private String sku;

  private int c;

  private int value;
}
