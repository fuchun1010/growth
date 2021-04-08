package com.tank.renew.my20210409;

import com.google.common.base.Preconditions;
import lombok.val;
import lombok.var;

/**
 * note implements: fast determine exists or not
 *
 * @author tank198435163.com
 */
public class BooleanArray {

  public BooleanArray(int size) {
    this.size = size;
    val len = this.tableSize(this.size >> 3 + 1);
    this.bytes = new byte[len];
  }

  /**
   * @param index
   * @return
   */
  public boolean hit(int index) {
    Preconditions.checkArgument(index < this.size, "index is over size");
    Preconditions.checkArgument(index >= 0, "index not allowed less than zero");
    val i = index >> 3;
    //余数
    val mod = index & ((1 << 3) - 1);
    return (this.bytes[i] & (1 << mod)) != 0;
  }

  /**
   * byte[1] => [0,0,0,0,0,0,0,0]
   * 17 / 5 => byte[3]
   * 17 %5 => 2 byte[1]的第2位
   *
   * @param index
   * @param value
   */
  public void assign(int index, boolean value) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    val i = index >> 3;
    //余数，mod取值范围为0~7
    val mod = index & ((1 << 3) - 1);
    if (value) {
      //将mod位设为1
      this.bytes[i] = (byte) (1 << mod | this.bytes[i]);
    } else {
      //将mod位设为0
      this.bytes[i] = (byte) (~(1 << mod) & this.bytes[i]);
    }
  }

  private int tableSize(int capacity) {
    var result = capacity - 1;
    result |= result >>> 1;
    result |= result >>> 2;
    result |= result >>> 4;
    result |= result >>> 8;
    result |= result >>> 16;
    return result < 0 ? 1 : result >= (1 << 30) ? 1 << 30 : result + 1;
  }

  private final byte[] bytes;


  private final int size;
}
