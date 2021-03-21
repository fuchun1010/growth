package com.tank.stream;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class ReducerV1 {

  @SneakyThrows({Exception.class})
  public void sum(@NonNull final Integer start, @NonNull final Integer end) {
    if (end.compareTo(start) <= 0) {
      throw new IllegalArgumentException("start must less than end");
    }

    val list = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    val env = ExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(Runtime.getRuntime().availableProcessors());
    env.fromElements(list)
            .flatMap((FlatMapFunction<List<Integer>, Integer>) (value, out) -> value.forEach(out::collect))
            .returns(Types.INT)
            .reduce((ReduceFunction<Integer>) Integer::sum)
            .print();

    env.execute(this.getClass().getSimpleName());
  }

}
