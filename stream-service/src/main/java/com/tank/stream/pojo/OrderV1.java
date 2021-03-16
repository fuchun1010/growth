package com.tank.stream.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.flink.calcite.shaded.com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


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

  private Timestamp created;

  private Timestamp updated;

  private Integer payment;

  @JsonIgnore
  private Integer isDuplicated = 0;


}
