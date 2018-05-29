package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTest {

    @Autowired
    DbService dbService;

    @Autowired
    private TaskRepository repository;

    @Test
    public void getAllTasks() {

        //Given
        Task taskTest = new Task(1L, "test", "content");
        Task taskTest2 = new Task(2L, "2test", "2content");
        repository.save(taskTest);
        repository.save(taskTest2);

        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        Assert.assertEquals(2,result.size());

        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void getTaskById() {
        //Given
        Task taskTest = new Task(1L, "test", "content");
        Task taskTest2 = new Task(2L, "2test", "2content");
        Task taskTest3 = new Task(3L, "3test", "3content");
        repository.save(taskTest);
        repository.save(taskTest2);
        repository.save(taskTest3);
        //When
        Optional<Task> result = dbService.getTaskById(1L);
        //Then
        Assert.assertFalse(result.isPresent());
        //Clean Up
        repository.deleteAll();
    }

    @Test
    public void saveTask() {
        //Given
        Task taskTest = new Task(1L, "test", "content");
        //When & Then
        Assert.assertNotNull(repository.save(taskTest));
        //CleanUp
        repository.deleteAll();
    }

    @Test
    public void deleteTask() {
        //Given
        Task taskTest = new Task(1L, "test", "content");
        repository.save(taskTest);
        //When
        repository.deleteAll();
        //Then
        Assert.assertFalse(repository.findById(taskTest.getId()).isPresent());
    }
}