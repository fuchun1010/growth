package com.tank.renew.my20210527;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

/**
 * @author tank198435163.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("最大任务收益")
class TaskProfitTest {
  /*
   * 1 ---> 0
   * 2 ---> 0
   * 3 ---->0
   * 4 ----->1
   * 5 ---->0
   * 6 ---->2
   * 7 --->3
   * 8---->5
   */
  @Test
  @DisplayName("最大任务价值")
  void maxProfit() {
    val tasks = this.initTasks();
    val taskMap = Maps.<Integer, Task>newHashMap();
    val taskTable = Lists.<PreTask>newArrayList();
    var preTaskId = 0;

    for (Task task : tasks) {
      taskMap.put(task.getTaskId(), task);
    }

    for (Task task : tasks) {
      if (taskTable.isEmpty()) {
        val preTask = new PreTask().setTaskId(task.getTaskId()).setPreTaskId(0);
        taskTable.add(preTask);
      } else {
        for (val tt : taskTable) {
          val target = taskMap.get(tt.getTaskId());
          if (target.getEnd() <= task.getStart()) {
            preTaskId = Math.max(preTaskId, tt.getTaskId());
          }
        }

        val result = Stream.ofAll(taskTable).map(PreTask::getTaskId).toSet();
        if (!result.contains(task.getTaskId())) {
          val preTask = new PreTask().setTaskId(task.getTaskId()).setPreTaskId(preTaskId);
          taskTable.add(preTask);

        }
        preTaskId = 0;
      }
    }

    System.out.println("");
  }

  private List<Task> initTasks() {
    val task1 = new Task().setTaskId(1).setStart(1).setEnd(4).setValue(5);
    val task2 = new Task().setTaskId(2).setStart(3).setEnd(5).setValue(1);
    val task3 = new Task().setTaskId(3).setStart(0).setEnd(6).setValue(8);
    val task4 = new Task().setTaskId(4).setStart(4).setEnd(7).setValue(4);
    val task5 = new Task().setTaskId(5).setStart(3).setEnd(8).setValue(6);
    val task6 = new Task().setTaskId(6).setStart(5).setEnd(9).setValue(3);
    val task7 = new Task().setTaskId(7).setStart(6).setEnd(10).setValue(2);
    val task8 = new Task().setTaskId(8).setStart(8).setEnd(11).setValue(4);
    return Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8);
  }
}