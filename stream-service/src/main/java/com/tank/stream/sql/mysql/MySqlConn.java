package com.tank.stream.sql.mysql;

import io.vavr.CheckedFunction1;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.function.Consumer;

public class MySqlConn {

  @SneakyThrows
  public <T> void query(@NonNull final String sql,
                        @NonNull final CheckedFunction1<ResultSet, T> fun,
                        @NonNull final Consumer<T> consumer) {
    try (val conn = this.createConn()) {
      val pst = conn.prepareStatement(sql);
      val rs = pst.executeQuery();
      while (rs.next()) {
        T data = fun.apply(rs);
        consumer.accept(data);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static MySqlConn instance() {
    return sqlConn;
  }

  private MySqlConn() {

  }

  private static final MySqlConn sqlConn = new MySqlConn();

  @SneakyThrows
  private Connection createConn() {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root", "123");
  }
}
