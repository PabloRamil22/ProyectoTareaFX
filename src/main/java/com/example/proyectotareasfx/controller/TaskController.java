package com.example.proyectotareasfx.controller;

import models.Task;
import models.User;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    public User userLogged;

    public boolean login(String username,String password){

        User user=new User();
        userLogged=user.login(username,password);
        if(userLogged!=null){
            return true;
        }else {
            return false;
        }

    }
    public boolean createUser(String username,String pass,int rol){
        User user=new User();
        return user.insertar("(username,password,idrol) values (?,?,?)",username,pass,rol);
    }

    public boolean editPassword(String username,String password){
        User user=new User();
        return user.actualizar("password=? where username=?",password,username);
    }

    public boolean createTask(String title, String description, LocalDate deadLine){
        Task task=new Task();
        return task.insertar("(title, description, deadline, iduser) values (?,?,?,?)", title, description, deadLine, userLogged.getIduser());
    }

    public List<Task> getAllTaskByUser(){
        Task task= new Task();
        return task.getAllByUser(userLogged.getIduser());
    }
    public List<Task> getALlTask(){
        Task task=new Task();
        return task.getAllTask();
    }
    public boolean tareaCompleta(int idtask){
        Task task=new Task();
        return task.actualizar("status=1 where idtask=?", idtask);
    }
    public boolean eliminarTarea(int idtask){
       return new Task().borrar("idtask=?",idtask);
    }
}
