package com.tank.renew.my20210425;

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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Item {

  // 商品名称
  private String name;

  // 渠道
  private Integer channel;

  //温区
  private String repository;
}
