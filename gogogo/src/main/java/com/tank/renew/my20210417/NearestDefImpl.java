package com.tank.renew.my20210417;

import com.google.common.base.Preconditions;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
public class NearestDefImpl implements NearestDef {

  @Override
  public int[] nearestArray(@NonNull int[] arr) {
    Preconditions.checkArgument(arr.length > 0, "not allowed empty array");
    val index = this.findIndex(arr);
    if (index == 0) {
      return new int[]{};
    }
    val result = exchangeHead(arr, index);
    reverse(result, index);
    return result;
  }

  private void reverse(int[] changedArr, int index) {
    for (int i = index, j = changedArr.length - 1; i < j; i++, j--) {
      var tmp = changedArr[index];
      changedArr[index] = changedArr[j];
      changedArr[j] = tmp;
    }
  }

  private int[] exchangeHead(int[] arr, int index) {
    val copy = Arrays.copyOf(arr, arr.length);
    int head = copy[index - 1];

    for (int i = copy.length - 1; i > 0; i--) {
      if (copy[i] > head) {
        copy[index - 1] = copy[i];
        copy[i] = head;
        break;
      }
    }
    return copy;
  }

  private int findIndex(@NonNull int[] arr) {
    val len = arr.length;
    for (int index = len - 1; index > 0; index--) {
      val c = arr[index];
      val pre = arr[index - 1];
      if (c > pre) {
        return index;
      }
    }

    return 0;
  }
}
