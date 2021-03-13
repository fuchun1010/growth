package com.tank.stream.pojo;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.*;

import java.beans.Transient;
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
