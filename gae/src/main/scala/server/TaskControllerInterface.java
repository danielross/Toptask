package server;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import model.Task;

public interface TaskControllerInterface {
    @Put
    void create(Task user);
    
    @Get
    TaskContainer getAllTasks();
}

