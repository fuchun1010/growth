package com.tank.stream.util;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.tank.stream.pojo.EventModel;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.function.Function;

import static strman.Strman.toCamelCase;

/**
 * @author tank198435163.com
 */
public class ModelParser {

  public EventModel toEventModel(@NonNull final Map<String, Object> params) {
    Preconditions.checkArgument(CollUtil.isNotEmpty(params), "key not allowed empty");
    val eventModel = new EventModel();

    val table = Try.of(() -> params.get("source"))
            .filter(source -> source instanceof Map)
            .map(source -> (Map<String, Object>) source)
            .filter(source -> source.containsKey("table"))
            .map(source -> source.get("table").toString())
            .getOrElse("-");

    eventModel.setTable(table);
    eventModel.setOp(params.containsKey("op") ? params.get("op").toString() : "-");

    val resultMap = functions.get(eventModel.getOp().toLowerCase()).apply(params);

    for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
      val fieldName = entry.getKey();
      val value = entry.getValue().toString();

      val field = new EventModel.Field(toCamelCase(fieldName), value);
      eventModel.addField(field);
    }


    return eventModel;
  }

  public static ModelParser instance() {
    return modelParser;
  }

  private ModelParser() {
    functions.putIfAbsent("u", this.updatedFun);
    functions.putIfAbsent("c", this.createdFun);
    functions.put("d", this.deletedFun);
  }


  private static final Map<String, Function<Map<String, Object>, Map<String, Object>>> functions = Maps.newConcurrentMap();


  @SuppressWarnings("unchecked")
  private Function<Map<String, Object>, Map<String, Object>> createdFun = source -> source.containsKey("after") ? (Map<String, Object>) source.get("after") : Maps.<String, Object>newHashMap();

  @SuppressWarnings("unchecked")
  private Function<Map<String, Object>, Map<String, Object>> updatedFun = source -> source.containsKey("after") ? (Map<String, Object>) source.get("after") : Maps.<String, Object>newHashMap();

  @SuppressWarnings("unchecked")
  private Function<Map<String, Object>, Map<String, Object>> deletedFun = source -> source.containsKey("before") ? (Map<String, Object>) source.get("before") : Maps.<String, Object>newHashMap();

  private final static ModelParser modelParser = new ModelParser();
}
