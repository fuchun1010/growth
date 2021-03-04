package com.tank.proxy.service;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyForImpl implements MethodInterceptor {

  @Override
  public Object intercept(Object instance, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    before(instance);
    return methodProxy.invokeSuper(instance, objects);
  }

  private void before(Object instance) {
    System.out.println(instance.getClass().getName());
  }

}
