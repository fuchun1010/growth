package com.tank.renew.my20210527;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class PreTask {

  private int taskId;
  private int preTaskId;
}
