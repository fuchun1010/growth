package com.tank.renew.my20210527;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.val;
import lombok.var;

import java.util.List;

public class TaskProfit {


  public List<PreTask> toSelectableTaskList(@NonNull final List<Task> tasks) {
    if (tasks.isEmpty()) {
      return Lists.<PreTask>newArrayList();
    }
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

    return taskTable;
  }
}
