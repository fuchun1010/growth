package com.tank.stream.order;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author tank198435163.com
 */
public class DbEventCapture {

  public <T> void handleTableEvent(@NonNull final Function<Map<String, Object>, T> transformFun,
                                   @NonNull final Consumer<T> consumerFun,
                                   @NonNull final String... listenedTables
  ) {

    val configuration = this.initOrderTableConfig(listenedTables);
    val task = EmbeddedEngine.create()
            .using(configuration)
            .notifying(record -> {
              final Struct struct = ((Struct) record.value());
              final Map<String, Object> result = this.parseStruct(struct);
              if (CollUtil.isEmpty(result) || result.containsKey("databaseName")) {
                return;
              }
              T model = transformFun.apply(result);
              consumerFun.accept(model);
            })
            .build();
    executors.execute(task);
  }

  public static DbEventCapture instance() {
    return orderEventCapture;
  }

  private DbEventCapture() {

  }

  private Configuration initOrderTableConfig(@NonNull final String... tables) {
    val cfgBuilder = Configuration.create()
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
            .with("format", "debezium-json");

    val strJoin = new StringJoiner(Stream.of(tables).toList().toCharSeq());
    strJoin.add(",");

    cfgBuilder.with("table.include.list", strJoin.toString());

    return cfgBuilder.build();
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

  private final ExecutorService executors = Executors.newFixedThreadPool(1);

  private final static DbEventCapture orderEventCapture = new DbEventCapture();
}
