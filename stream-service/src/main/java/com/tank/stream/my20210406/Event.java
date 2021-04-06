package com.tank.stream.my20210406;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.java.typeutils.TypeExtractor;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(Event.class)
            .add("id", id)
            .add("name", name)
            .add("price", price)
            .toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Event event = (Event) obj;
    return getId() == event.getId();
  }

  @Override
  public int hashCode() {
    int h;
    return (h = Objects.hashCode(getId())) ^ (h >>> 16);
  }

  public static TypeSerializer<Event> createTypeSerializer() {
    return TypeExtractor.createTypeInfo(Event.class).createSerializer(new ExecutionConfig());
  }

  private int id;

  private String name;

  private double price;
}
