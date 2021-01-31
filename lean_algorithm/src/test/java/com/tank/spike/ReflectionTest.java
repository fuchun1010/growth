package com.tank.spike;

import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author tank198435163.com
 */
class ReflectionTest {

  @Test
  @DisplayName("测试修改method的accessor")
  void testDrive() {
    val driver = new Driver();
    driver.drive();
    val driveMd = ReflectUtil.getMethodByName(driver.getClass(), "drive");
    Assertions.assertNotNull(driveMd);
    val checkMd = ReflectUtil.getMethodByNameIgnoreCase(driver.getClass(), "checkCar");
    Assertions.assertNotNull(checkMd);
    checkMd.setAccessible(true);
  }


  private static class Driver {


    @SneakyThrows(IllegalAccessException.class)
    public void drive() {
      if (checkCar()) {
        System.out.println("car is driving");
      } else {
        throw new IllegalAccessException("illegal access");
      }
    }


    private boolean checkCar() {
      return true;
    }

  }
}
