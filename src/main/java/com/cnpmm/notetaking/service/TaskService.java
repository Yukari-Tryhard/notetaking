package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Task;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.TaskRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public ServiceResponse addTaskByUser(Task task, Integer userId){
        try{
            User user = userRepository.findById(userId).orElse(null);
            if (user != null){
                task.setUser(user);
                taskRepository.save(task);
                return new ServiceResponse(200, "save task sucessfully");
            }
            return new ServiceResponse(409, "user id "+user.getId() +" not exists");
        }
        catch (Exception ex){
            return new ServiceResponse(500,ex.getMessage());
        }
    }

    public Collection<Task> findAllTaskByUser(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return taskRepository.findAllByUser(userId);
        }
        return  null;
    }

    public ServiceResponse updateTask(Task task){
        try{
            Task existTask = taskRepository.findById(task.getTaskId()).orElse(null);
            if (existTask != null){
                taskRepository.UpdateTask(task.getTaskId(),task.getTitle(), task.getContent(), task.getStartDate(), task.getEndDate(), task.isDone());
                return new ServiceResponse(200, "updated task has id " + task.getTaskId());
            }
            return new ServiceResponse(409, "task has id " + task.getTaskId() + " is not exist");
        }catch (Exception ex){
            return new ServiceResponse(500, ex.getMessage());
        }
    }

    public Collection<Task> findAllTaskRecentlyByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return taskRepository.findAllTaskRecentlyByUser(userId);
        }
        return  null;
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }
}
