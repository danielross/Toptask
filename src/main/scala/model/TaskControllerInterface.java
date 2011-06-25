package net.danross.toptask.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import net.danross.toptask.model.TaskModel;

public interface TaskControllerInterface {
    @Put
    void create(TaskModel user);
    
    @Get
    TaskContainer getAllTasks();
}

