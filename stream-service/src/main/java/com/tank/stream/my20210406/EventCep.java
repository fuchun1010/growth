package com.tank.stream.my20210406;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author tank198435163.com
 */
public class EventCep {

  @SneakyThrows
  public static void main(String[] args) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.setParallelism(1);
    DataStream<Event> input = env.fromElements(
            new Event(1, "barfoo", 1.0),
            new Event(2, "start", 2.0),
            new Event(3, "foobar", 3.0),
            new Event(5, "middle", 5.0),
            new Event(42, "42", 42.0),
            new Event(8, "end", 1.0));

    Pattern<Event, ?> pattern =
            Pattern.<Event>begin("start")
                    .where(
                            new SimpleCondition<Event>() {

                              @Override
                              public boolean filter(Event value) throws Exception {
                                return value.getName().equals("start");
                              }
                            })
                    .followedByAny("middle")
                    .where(
                            new SimpleCondition<Event>() {

                              @Override
                              public boolean filter(Event value) throws Exception {
                                return value.getName().equals("middle");
                              }
                            })
                    .followedByAny("end")
                    .where(
                            new SimpleCondition<Event>() {

                              @Override
                              public boolean filter(Event value) throws Exception {
                                return value.getName().equals("end");
                              }
                            });


    val result = CEP.pattern(input, pattern)
            .inProcessingTime()
            .flatSelect(
                    (p, o) -> {
                      val builder = new StringBuilder();
                      builder.append(p.get("start").get(0).getId())
                              .append(",")
                              .append(p.get("middle").get(0).getId())
                              .append(",")
                              .append(p.get("end").get(0).getId());

                      o.collect(builder.toString());
                    },
                    Types.STRING);

    result.print();
    env.execute("event cep");
  }
}
