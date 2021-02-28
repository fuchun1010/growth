package com.tank.spike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSample {

  /**
   * orderNo : 16023947192247545
   * totalPrice : 8470
   * channel : 1
   * transactionNo : 4200000764202010118698746516
   * isAdd : true
   * createDateTime : 2020-10-11 13:38:39
   * regionCode : region_1002
   * areaCode : area_njpszx
   * from : oms
   * pieceCode : piece_0102500007
   * brand : 1
   * storeCode : store_770152
   */

  private String orderNo;
  private int totalPrice;
  private String channel;
  private String transactionNo;
  private boolean isAdd;
  private String createDateTime;
  private String regionCode;
  private String areaCode;
  private String from;
  private String pieceCode;
  private String brand;
  private String storeCode;


}
