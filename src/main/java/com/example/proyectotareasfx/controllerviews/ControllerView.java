package com.example.proyectotareasfx.controllerviews;

import com.example.proyectotareasfx.controller.TaskController;

public abstract class ControllerView {
    protected TaskController taskController;

    public void setTaskController(TaskController taskController) {
        this.taskController=taskController;
        cargaInicial();
    }

    public abstract void cargaInicial();


}
