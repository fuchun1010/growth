package com.tank.stream;

import cn.hutool.json.JSONUtil;
import com.tank.stream.common.PropsBuilder;
import com.tank.stream.kafka.KafkaStreamProcessor;
import com.tank.stream.pojo.Person;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;

import java.io.IOException;

/**
 * @author tank198435163.com
 */
public class App {
  public static void main(final String[] args) {
    val processor = KafkaStreamProcessor.instance();
    val propsBuilder = new PropsBuilder();
    propsBuilder.addProps("group.id", "demo").addProps("bootstrap.servers", "localhost:9092");
    processor.<Person>processStream("person",
            "kafka-flink-person-consumer",
            propsBuilder,
            new PersonSchema(),
            stream -> stream.map((MapFunction<Person, String>) Person::getName)
                    .returns(Types.STRING)
                    .print());
  }

  private static class PersonSchema implements DeserializationSchema<Person> {

    @Override
    public Person deserialize(byte[] message) throws IOException {
      val jsonStr = new String(message);
      return JSONUtil.toBean(jsonStr, Person.class);
    }

    @Override
    public boolean isEndOfStream(Person nextElement) {
      return false;
    }

    @Override
    public TypeInformation<Person> getProducedType() {
      return Types.POJO(Person.class);
    }
  }
}
