package com.tank.stream.sql.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent implements Serializable {

  @Override
  public String toString() {
    return "UserEvent{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", job='" + job + '\'' +
            ", gender=" + gender +
            '}';
  }

  private Long id;

  private String username;

  private String job;

  private int gender;

}
