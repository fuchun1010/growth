package com.tank.renew.my20210326;

import lombok.NonNull;

import java.util.Optional;

public interface LfuDef<I, T> {

  Optional<T> findAny(@NonNull final I input);

}
