package com.tank.renew.my20210523;

/**
 * 门店售卖的商品有26种，为`abcdefghijklmnopqrstuvwxyz`，a价格为1，b价格为2，c价格为3...x的价格为24，y的价格为25，z的价格为26。
 * <p>
 * 店员可以配置多种优惠活动，活动的结构为 `[商品组合,优惠收益,价格门槛]`，比如：
 * 1. ['abc', 2, 0] 代表 商品a、b、c组合购买时，总体价格减少2元，无价格门槛
 * 2. ['x', 10, 23] 代表 商品x达到门槛23元时，价格减少10元
 * <p>
 * ### 输入
 * ```json
 * carts = "xyyz"
 * priceOffs = [
 * ["x", 4, 0],
 * ["y", 5, 0],
 * ["xyz", 6, 0],
 * ]
 * ```
 * ### 输出
 * `11`
 * ### 说明
 * 匹配到最优活动组合 `["y", 5, 0]`和 `["xyz", 6, 0]`
 */

import com.google.common.collect.Lists;
import lombok.NonNull;

import java.util.List;

/**
 * @author tank198435163.com
 */
public class DefaultMaxDiscount implements MaxDiscount {

  @Override
  public int discount(@NonNull List<String> goods,
                      @NonNull int[] c,
                      @NonNull int[] values) {

    if (goods.isEmpty()) {
      return 0;
    }

    if (values.length == 0) {
      return 0;
    }


    int lastIndex = goods.size() - 1;

    //最后1个不参加活动
    List<String> leftGoods = Lists.newArrayList();
    for (int i = 0; i < lastIndex; i++) {
      leftGoods.add(goods.get(i));
    }

    String lastGood = goods.get(lastIndex);
    System.out.println("lastGood = " + lastGood);

    int[] remainingValues = new int[values.length - 1];
    System.arraycopy(values, 0, remainingValues, 0, remainingValues.length);

    //TODO 缺乏果品判断，70行有点不对

    System.out.println("=================");
    //最后一个活动不参加
    int left = this.discount(goods, c, remainingValues);
    int index = lastIndex > values.length ? 0 : values.length - 1;
    //参加所有活动,2个果品参加活动 + 剩余1个参加的值
    int right = this.discount(leftGoods, c, remainingValues) + values[index];
    System.out.println("left = " + left + ", right = " + right);
    System.out.println("=================");
    return Math.max(left, right);
  }
}
