package com.tank.stream.udf;

import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Timestamp;

/**
 * @author tank198435163.com
 */
public class DateTimeToMillionSecondsUdf extends ScalarFunction {

  public long eval(final Timestamp timestamp) {
    return timestamp.getTime();
  }

}
