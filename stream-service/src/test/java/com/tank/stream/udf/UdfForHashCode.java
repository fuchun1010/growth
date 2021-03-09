package com.tank.stream.udf;

import lombok.NonNull;
import org.apache.flink.table.functions.ScalarFunction;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class UdfForHashCode extends ScalarFunction {

  /**
   * eval name is fixed for udf about scalar function
   *
   * @param value
   * @return
   */
  public int eval(@NonNull final String value) {
    return Objects.hash(value);
  }
}
