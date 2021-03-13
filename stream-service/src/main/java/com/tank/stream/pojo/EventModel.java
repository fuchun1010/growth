package com.tank.stream.pojo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.vavr.collection.Stream;
import lombok.*;
import strman.Strman;

import java.beans.Transient;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
public class EventModel {

  @Transient
  public void addField(@NonNull final Field field) {
    this.fields.add(field);
  }

  @Transient
  @SneakyThrows({Exception.class})
  public <T> T transformTo(@NonNull final Class<T> clazz) {
    T instance = clazz.newInstance();
    val setters = Maps.<String, Method>newHashMap();
    Stream.of(instance.getClass().getDeclaredMethods())
            .filter(method -> method.getName().startsWith("set"))
            .forEach(method -> {
              val name = method.getName().substring(3);
              setters.putIfAbsent(Strman.toCamelCase(name), method);
            });

    for (Field field : Stream.ofAll(this.fields).
            filter(field -> setters.containsKey(field.getName()))) {

      val method = setters.get(field.getName());

      val parameter = method.getParameters()[0];

      if (parameter.getType() == int.class) {
        method.invoke(instance, Integer.parseInt(field.getValue()));
      }

      if (parameter.getType() == String.class) {
        method.invoke(instance, field.getValue());
      }

      if (parameter.getType() == DateTime.class) {
        method.invoke(instance, DateUtil.parse(field.getValue()));
      }

    }

    return instance;
  }


  @Getter
  @Setter
  @EqualsAndHashCode(of = {"name"})
  @AllArgsConstructor
  public static class Field {
    /**
     * name :
     * value : 1
     */

    @Transient
    public boolean isEmptyName() {
      return StrUtil.isEmpty(this.name);
    }

    private String name;
    private String value;
  }

  private String table;
  private String op;
  private Set<Field> fields = Sets.newHashSet();
}
