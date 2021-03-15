package com.tank.stream.pojo;

import cn.hutool.core.date.DateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.flink.calcite.shaded.com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false, of = "orderNo")
public class OrderV1 {

  private Long pid;

  private String orderNo;

  private String orgCode;

  private String currentStatus;

  private DateTime created;

  private DateTime updated;

  private Integer payment;

  @JsonIgnore
  private Integer isDuplicated = 0;


}
