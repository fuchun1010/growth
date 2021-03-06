package com.tank.stream.sql.mysql;

import lombok.val;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * @author tank198435163.com
 */
public class MySqlDialogSource extends RichSourceFunction<UserEvent> {

  @Override
  public void open(Configuration parameters) throws Exception {
    this.mySqlConn = MySqlConn.instance();
  }

  @Override
  public void run(SourceContext<UserEvent> ctx) {
    this.mySqlConn.query("select * from tab_users", rs -> {
      val username = rs.getString("uname");
      val id = rs.getLong("uid");
      val gender = rs.getInt("gender");
      val job = rs.getString("job");
      val event = new UserEvent();
      event.setUsername(username);
      event.setId(id);
      event.setJob(job);
      event.setGender(gender);
      return event;
    }, ctx::collect);
  }

  @Override
  public void cancel() {

  }

  private MySqlConn mySqlConn;


}
