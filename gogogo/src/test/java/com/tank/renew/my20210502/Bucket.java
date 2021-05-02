package com.tank.renew.my20210502;

import com.google.common.collect.Sets;
import lombok.*;
import lombok.experimental.Accessors;

import java.beans.Transient;
import java.util.Collection;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Bucket {

  @Transient
  public void add(@NonNull final Integer data) {
    this.collections.add(data);
  }

  private Integer min;

  private Integer max;

  private Collection<Integer> collections = Sets.newHashSet();
}
