package net.danross.toptask.model;

import java.util.List;

import org.restlet.resource.ClientResource;

import android.util.Log;

public class TaskController {

    public final ClientResource cr = new ClientResource(
            EngineConfiguration.gae_path + "/rest/task");


    public TaskController() {
        EngineConfiguration.getInstance();        
    }

    public void create (TaskModel task) throws Exception {
        final TaskControllerInterface tci = cr.wrap(TaskControllerInterface.class);
        try {
            tci.create(task);
            Log.i("TaskrController", "Creation success !");
        } catch (Exception e) {
            Log.i("TaskController", "Creation failed: " + e);
            throw e;
        }
    }


    public List<TaskModel> getAllTasks() {
        final TaskControllerInterface tci = cr.wrap(TaskControllerInterface.class);
        TaskContainer content = tci.getAllTasks();
        return content.getTask_list();
    }
}
