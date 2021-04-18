package com.tank.renew.my20210418;

import cn.hutool.core.util.NumberUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Deque;

/**
 * @author tank198435163.com
 */
public class DeletedNumImpl implements DeletedNum {

  @Override
  public int remainingMin(@NonNull String num, int k) {
    Preconditions.checkArgument(NumberUtil.isNumber(num), "num must be number");
    Preconditions.checkArgument(k > 0, "k must greater than 0");
    Preconditions.checkArgument(num.length() > k, "k must less than num length");
    var counter = k;
    for (int index = 0; index < num.length(); index++) {
      val ch = num.charAt(index);
      val data = Integer.parseInt(String.valueOf(ch));
      if (counter > 0) {
        if (this.result.isEmpty()) {
          result.push(data);
          continue;
        }
        counter--;
        val target = result.pop();
        if (target < data) {
          result.push(target);
        }
      } else {
        result.addLast(data);
      }
    }

    if (result.isEmpty()) {
      return 0;
    }

    val sb = new StringBuilder();

    while (!result.isEmpty()) {
      sb.append(result.pop());
    }

    return Integer.parseInt(sb.toString());
  }

  private final Deque<Integer> result = Queues.newArrayDeque();
}
