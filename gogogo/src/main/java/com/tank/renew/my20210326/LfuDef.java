package com.tank.renew.my20210326;

import java.util.Optional;

public interface LfuDef<T> {

  Optional<T> findAny();
  
}
