package com.tank.stream;

import cn.hutool.json.JSONUtil;
import com.tank.stream.common.PropsBuilder;
import com.tank.stream.pojo.Person;
import lombok.val;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KafkaStreamProcessorTest {

  @Test
  @DisplayName("kafka流式处理")
  void processStream() {
    val processor = KafkaStreamProcessor.instance();
    val propsBuilder = new PropsBuilder();
    Assertions.assertNotNull(propsBuilder);
    propsBuilder.addProps("topic", "person")
            .addProps("group.id", "person-consumer")
            .addProps("bootstrap.servers", "localhost:9092");
    processor.<Person>processStream("kafka-flink-person-consumer",
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