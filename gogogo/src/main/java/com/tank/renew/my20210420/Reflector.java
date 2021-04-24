package com.tank.renew.my20210420;

import lombok.NonNull;

import java.lang.reflect.Constructor;

/**
 * @author tank198435163.com
 */
public class Reflector {

  public Reflector(@NonNull final Class<?> clazz) {
    this.type = clazz;
  }

  private Class<?> type;

  private Constructor<?> defaultConstructor;
}
