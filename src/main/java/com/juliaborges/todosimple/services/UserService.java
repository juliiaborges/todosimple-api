package com.juliaborges.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliaborges.todosimple.models.User;
import com.juliaborges.todosimple.repositories.TaskRepository;
import com.juliaborges.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
         Optional<User> user = this.userRepository.findById(id);
         return user.orElseThrow(()-> new RuntimeException(
            "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
         ) ); 
    }
    @Transactional
    public User create(User obj){
      obj.setId(null);
      obj = this.userRepository.save(obj);
      this.taskRepository.saveAll(obj.getTasks());
      return obj;

    }

    }
   

