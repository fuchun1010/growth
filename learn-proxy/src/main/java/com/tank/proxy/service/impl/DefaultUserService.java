package com.tank.proxy.service.impl;

import com.tank.proxy.service.UserService;

/**
 * @author tank198435163.com
 */
public class DefaultUserService implements UserService {

  @Override
  public void sayWelcome() {
    System.out.println("this is default say welcome");
  }
}
