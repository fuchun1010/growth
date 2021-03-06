package com.tank.stream.cdc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import lombok.val;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * @author tank198435163.com
 */
public class TableCdcStream {

  public void processSqlEvent(Consumer<Map<String, Object>> dataProcessorOpt) {
    val configuration = this.initDebeziumCfg();

    val engine = EmbeddedEngine.create()
            .using(configuration)
            .notifying(record -> {
              if (Objects.isNull(record)) {
                return;
              }
              Struct value = (Struct) record.value();
              try {
                Map<String, Object> valueMap = parseStruct(value);
                if (CollUtil.isEmpty(valueMap) || valueMap.containsKey("databaseName")) {
                  return;
                }
//                System.out.println(JSONUtil.toJsonStr(valueMap));
                dataProcessorOpt.accept(valueMap);
              } catch (Exception e) {
                throw e;
              }
            }).build();

//    val thread = new Thread(engine);
//    thread.start();
    Executors.newFixedThreadPool(1).execute(engine);

  }

  /**
   * init embed debezium configuration
   *
   * @return
   */
  private Configuration initDebeziumCfg() {

    return Configuration.create()
            .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
            .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
            .with("offset.storage.file.filename", this.assignFileLocation("offset.dat"))
            .with("offset.flush.interval.ms", 60000)
            .with("name", "demo")
            .with("database.hostname", "localhost")
            .with("database.port", 3307)
            .with("database.user", "root")
            .with("database.password", "123")
            .with("server.id", 1000)
            .with("database.server.name", "localhost-demo")
            .with("database.dbname", "demo")
            .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
            .with("database.history.file.filename", this.assignFileLocation("history.dat"))
            .with("database.history.skip.unparseable.ddl", false)
            .with("database.include.list", "demo")
            //.with("format", "debezium-json")
            .build();

  }

  private String assignFileLocation(String fileName) {
    return StrUtil.format("{}/{}/{}", System.getProperty("user.dir"), "data", fileName);
  }

  private Map<String, Object> parseStruct(Struct struct) {
    Map<String, Object> structMap = Maps.newHashMap();
    if (struct == null) {
      return structMap;
    }
    Schema schema = struct.schema();
    for (Field field : schema.fields()) {
      String fieldName = field.name();
      Object object = struct.get(field);
      if (field.schema().type().equals(Schema.Type.STRUCT)) {
        Struct subStruct = struct.getStruct(fieldName);
        if (subStruct != null) {
          object = parseStruct(subStruct);
        }
      }
      structMap.put(fieldName, object);
    }
    return structMap;
  }

}
