package server;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import model.TaskModel;
import model.User;

public class TaskController extends ServerResource implements
	TaskControllerInterface {

    public TaskController() {}

    @Override
    public void create (TaskModel task) {
    	ObjectifyService.register(TaskModel.class);
	    Objectify ofy = ObjectifyService.begin();

    	TaskModel tp = new TaskModel();
	    tp.setName(task.getName());
    	tp.setCategory(task.getCategory());
	    tp.setDescription(task.getDescription());
    	tp.setOwner(task.getOwner());
        tp.setPriorityStart(task.getPriorityStart());
        tp.setPriorityEnd(task.getPriorityEnd());
        tp.setDueDate(task.getDueDate());
    	ofy.put(tp);
    }

    @Override
    public TaskContainer getAllTasks() {
        TaskContainer content = null;
    	List<TaskModel> tasks = new ArrayList<TaskModel>();
	    ObjectifyService.register(TaskModel.class);
    	Objectify ofy = ObjectifyService.begin();

	    Query<TaskModel> q = ofy.query(TaskModel.class);

    	for (TaskModel u : q)
	        tasks.add(u);

    	content = new TaskContainer();
        content.setTask_list(tasks);
	
	    return content;
    }
}
