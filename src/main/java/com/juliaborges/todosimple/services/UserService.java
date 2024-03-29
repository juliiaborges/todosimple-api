package com.juliaborges.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliaborges.todosimple.models.User;
import com.juliaborges.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
         Optional<User> user = this.userRepository.findById(id);//Optional -> Não recebe Null e sim um Vazio. This -> diz repeito a qual classe é 
         return user.orElseThrow(()-> new RuntimeException( //->orElseThrow (Eu retorno o usuário se ele estiver preenchido ) ->RuntimeException (ele não para e se não estiver preenchido faça...)
            "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
         ) ); 
    }
    @Transactional //Utilizar sempre que fizermos uma inserção -> COnseguimos ter controle melhor do que está acontecendo na aplicação (Atomicidade)
    public User create(User obj){
      obj.setId(null);
      obj = this.userRepository.save(obj);
      return obj;
    }

    public User update(User obj){
       User newObj = findById(obj.getId());
       newObj.setPassword(obj.getPassword());
       return this.userRepository.save(newObj);
    }

    public void delete(Long id){
      findById(id);
      try{
        this.userRepository.deleteById(id);
      } catch (Exception e){
        throw new RuntimeException("Não é possível excluir, pois há tarefas relacionadas!");
      }
    }

    }
   

