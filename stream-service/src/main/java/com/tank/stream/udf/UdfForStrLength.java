package com.tank.stream.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class UdfForStrLength extends ScalarFunction {

  public int eval(final String str) {
    return Objects.isNull(str) ? 0 : str.length();
  }


}
