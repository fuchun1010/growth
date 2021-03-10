package com.tank.stream.udf;

import com.tank.stream.pojo.WordV2;
import lombok.NonNull;
import lombok.val;
import org.apache.flink.table.functions.AggregateFunction;

/**
 * @author tank198435163.com
 */
public class RowsCntUdf extends AggregateFunction<Integer, WordV2> {

  public void accumulate(@NonNull final WordV2 accumulator, @NonNull final Integer cnt) {
    val value = accumulator.getCnt() + 1;
    accumulator.setCnt(value);
  }


  @Override
  public Integer getValue(WordV2 accumulator) {
    return accumulator.getCnt();
  }

  @Override
  public WordV2 createAccumulator() {
    return new WordV2();
  }
}
