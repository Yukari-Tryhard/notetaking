package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Tag;
import com.cnpmm.notetaking.model.Task;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.TaskRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    public Task addTaskByUser(Task task, Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            task.setUser(user);
            return taskRepository.save(task);
        }
        return null;
    }

    public Collection<Task> findAllTaskByUser(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return taskRepository.findAllByUser(userId);
        }
        return  null;
    }

    public String updateTask(Task task){
        try{
            Task existTask = taskRepository.findById(task.getTaskId()).orElse(null);
            if (existTask != null){
                taskRepository.UpdateTask(task.getTaskId(),task.getTitle(), task.getContent(), task.getStartDate(), task.getEndDate());
                return "updated task has id " + task.getTaskId();
            }
            return "task has id " + task.getTaskId() + " is not exist";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public Collection<Task> findAllTaskRecentlyByUser(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return taskRepository.findAllTaskRecentlyByUser(userId);
        }
        return  null;
    }
}
