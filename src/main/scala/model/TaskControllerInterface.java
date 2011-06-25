package net.danross.toptask.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import net.danross.toptask.model.Task;

public interface TaskControllerInterface {
    @Put
    void create(Task user);
    
    @Get
    TaskContainer getAllTasks();
}

