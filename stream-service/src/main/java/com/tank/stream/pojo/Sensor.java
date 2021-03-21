package com.tank.stream.pojo;

import com.google.common.base.MoreObjects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


/**
 * @author tank198435163.com
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
public class Sensor {

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(Sensor.class)
            .add("id", id)
            .add("timestamp", timestamp)
            .add("temperature", temperature == null ? 0L : this.temperature.doubleValue()).toString();
  }

  private String id;

  private Long timestamp;

  private BigDecimal temperature;
}
