package com.tank.renew.my20210419;

import cn.hutool.core.util.StrUtil;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;
import lombok.var;

/**
 * @author tank198435163.com
 */
public class BigDataAddImpl implements BigDataAdd {

  @Override
  public String add(@NonNull String first, @NonNull String second) {
    val isEmptyStr = Stream.of(first, second)
            .map(StrUtil::isEmptyIfStr)
            .reduce(Boolean::logicalAnd);

    if (isEmptyStr) {
      throw new IllegalArgumentException("first or second exists white space string");
    }

    int firstLen = first.length();
    int secLen = second.length();
    var maxLength = Math.max(firstLen, secLen);
    maxLength += 1;
    int[] result = new int[maxLength];
    int[] tmpFirst = new int[maxLength];
    int[] tmpSecond = new int[maxLength];

    var ind = 0;
    for (int index = firstLen - 1; index >= 0; index--) {
      tmpFirst[ind++] = Integer.parseInt(String.valueOf(first.charAt(index)));
    }
    ind = 0;
    for (int index = secLen - 1; index >= 0; index--) {
      tmpSecond[ind++] = Integer.parseInt(String.valueOf(second.charAt(index)));
    }

    for (int index = 0; index < result.length; index++) {
      val tmp = tmpFirst[index] + tmpSecond[index];
      if (tmp < 10) {
        result[index] += tmp;
      } else {
        result[index] += (tmp - 10);
        var couter = index + 1;
        do {
          result[couter] += 1;
        } while (result[couter++] >= 10);
      }
    }

    val sb = new StringBuilder();
    for (int i = maxLength - 1; i >= 0; i--) {
      sb.append(result[i]);
    }


    return sb.toString().charAt(0) == '0' ? sb.toString().substring(1) : sb.toString();
  }
}
