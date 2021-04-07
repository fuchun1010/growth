package com.tank.stream.my20210407;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import static java.lang.Long.parseLong;

/**
 * @author tank198435163.com
 */
public class TopStream {

  @SneakyThrows
  public void process() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.getCheckpointConfig().setCheckpointTimeout(60000);
    env.getCheckpointConfig().setCheckpointInterval(12000);
    env.setParallelism(1);

    val resource = TopStream.class.getClassLoader().getResource("user-behaver.csv");

    Preconditions.checkArgument(resource != null, "resource not exists");

    val stream = env.readTextFile(resource.getPath(), "UTF-8")
            .filter(StrUtil::isNotBlank)
            .map((MapFunction<String, UserBehave>) line -> {
              val fields = line.split(",");
              return new UserBehave(parseLong(fields[0]), parseLong(fields[1]), parseLong(fields[2]), fields[3], parseLong(fields[4]));
            })
            .returns(Types.POJO(UserBehave.class))
            .uid("userBehave")
            .filter((FilterFunction<UserBehave>) value -> "pv".equalsIgnoreCase(value.getOp()))
            .uid("pv");


    stream.print("only print");


    env.execute("topN");
  }
}
