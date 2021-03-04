package com.tank.proxy;

import com.tank.proxy.service.ProxyForImpl;
import com.tank.proxy.service.UserService;
import com.tank.proxy.service.impl.DefaultUserService;
import lombok.val;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author tank198435163.com
 */
public class MainAboutProxy {
  public static void main(String[] args) {
    val enhancer = new Enhancer();
    enhancer.setSuperclass(DefaultUserService.class);
    enhancer.setCallback(new ProxyForImpl());
    val userService = ((UserService) enhancer.create());
    userService.sayWelcome();

  }
}
