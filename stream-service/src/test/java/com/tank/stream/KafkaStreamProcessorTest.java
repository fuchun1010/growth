package com.tank.stream;

import cn.hutool.json.JSONUtil;
import com.tank.stream.pojo.Person;
import lombok.val;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KafkaStreamProcessorTest {

  @Test
  @DisplayName("kafka流式处理")
  void processStream() {

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