package com.tank.stream.kafka;

import org.junit.jupiter.api.*;

/**
 * @author tank198435163.com
 */
@DisplayName("测试kafka的ddl")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KafkaTopicCreatorTest {

  @Test
  @DisplayName("创建topic")
  void testCreateTopic() {
    Assertions.assertNotNull(this.creator);
    this.creator.createTopic();
  }

  @BeforeEach
  void init() {
    this.creator = new KafkaTopicCreator();
  }

  KafkaTopicCreator creator;

}