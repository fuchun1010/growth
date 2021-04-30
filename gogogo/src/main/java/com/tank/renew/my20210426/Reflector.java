package com.tank.renew.my20210426;

import io.vavr.collection.Stream;
import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class Reflector<T> {

  public Reflector(@NonNull final Class<T> clazz) {
    this.type = clazz;
    this.allMethods = this.obtainAllMethods();
    this.defaultConstructor = this.obtainDefaultConstructor();
  }

  private Method[] obtainAllMethods() {
    return Stream.of(this.type.getDeclaredMethods())
            .filter(method -> !method.isBridge())
            .toList()
            .toJavaArray(value -> new Method[0]);
  }

  private Constructor<T> obtainDefaultConstructor() {
    return (Constructor<T>) Stream.of(this.type.getDeclaredConstructors())
            .filter(constructor -> constructor.getParameterCount() == 0)
            .toList().head();
  }

  private final Class<T> type;

  private Method[] allMethods;

  private Constructor<T> defaultConstructor;
}
