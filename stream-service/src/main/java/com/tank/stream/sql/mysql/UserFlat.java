package com.tank.stream.sql.mysql;

import lombok.val;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;

public class UserFlat extends RichMapFunction<UserEvent, Long> {


  @Override
  public void open(Configuration parameters) throws Exception {
    super.open(parameters);
    val descriptor = new ValueStateDescriptor<Long>("id", Types.LONG);
    this.valueState = this.getRuntimeContext().getState(descriptor);
  }

  @Override
  public Long map(UserEvent value) throws Exception {

    this.valueState.update(value.getId());

    return value.getId();
  }


  private ValueState<Long> valueState;


}
