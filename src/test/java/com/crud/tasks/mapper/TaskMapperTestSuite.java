package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "TaskDto", "TaskDtoContent");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(1L, (long)task.getId());
        Assert.assertEquals("TaskDto", task.getTitle());
        Assert.assertEquals("TaskDtoContent", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(1L, (long)task.getId());
        Assert.assertEquals("TaskTitle", task.getTitle());
        Assert.assertEquals("TaskContent", task.getContent());
    }

    @Test
    public void testMToTaskDtoList() {
        //Given
        Task task = new Task(1L, "TaskTitle", "TaskContent");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, taskDtos.size());
        Assert.assertEquals(1L, (long)taskDtos.get(0).getId());
        Assert.assertEquals("TaskTitle", taskDtos.get(0).getTitle());
        Assert.assertEquals("TaskContent", taskDtos.get(0).getContent());
    }

}