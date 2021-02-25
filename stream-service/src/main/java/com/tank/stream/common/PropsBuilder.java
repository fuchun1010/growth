package com.tank.stream.common;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import io.vavr.control.Option;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.Properties;

/**
 * @author tank198435163.com
 */
public class PropsBuilder {

  public PropsBuilder addProps(@NonNull final String key, @NonNull final Object value) {
    val isExisted = PROPS.containsKey(key);
    if (isExisted) {
      throw new IllegalArgumentException(StrUtil.format("key:[{}] existed", key));
    }
    PROPS.putIfAbsent(key, value);
    return this;
  }

  public Properties toProps() {
    val properties = new Properties();
    for (Map.Entry<String, Object> entry : this.PROPS.entrySet()) {
      properties.put(entry.getKey(), entry.getValue());
    }
    PROPS.clear();
    return properties;
  }

  public Option<Object> value(@NonNull final String key) {
    return Option.some(PROPS.get(key));
  }

  public int size() {
    return PROPS.size();
  }

  public final Map<String, Object> PROPS = Maps.newConcurrentMap();


}
