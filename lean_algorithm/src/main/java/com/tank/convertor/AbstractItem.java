package com.tank.convertor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public abstract class AbstractItem {

  private Integer id;

  private String desc;

  private Integer parentId;

}
