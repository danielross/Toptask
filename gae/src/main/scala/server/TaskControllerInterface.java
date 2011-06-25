package server;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import model.TaskModel;

public interface TaskControllerInterface {
    @Put
    void create(TaskModel task);
    
    @Get
    TaskContainer getAllTasks();
}
