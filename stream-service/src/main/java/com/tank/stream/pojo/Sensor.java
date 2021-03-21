package com.tank.stream.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static org.apache.flink.shaded.guava18.com.google.common.base.Objects.toStringHelper;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
public class Sensor {

  @Override
  public String toString() {
    return toStringHelper(Sensor.class).add("id", id).add("timestamp", timestamp)
            .add("temperature", temperature == null ? 0L : this.temperature.doubleValue()).toString();
  }

  private String id;

  private Long timestamp;

  private BigDecimal temperature;
}
