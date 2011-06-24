package server;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import model.Task;
import model.User;

public class TaskController extends ServerResource implements
	TaskControllerInterface {

    public TaskController() {}

    @Override
    public void create (Task task) {
    	ObjectifyService.register(Task.class);
	    Objectify ofy = ObjectifyService.begin();

    	Task tp = new Task();
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
    	List<Task> tasks = new ArrayList<Task>();
	    ObjectifyService.register(Task.class);
    	Objectify ofy = ObjectifyService.begin();

	    Query<Task> q = ofy.query(Task.class);

    	for (Task u : q)
	        tasks.add(u);

    	content = new TaskContainer();
        content.setTask_list(tasks);
	
	    return content;
    }
}

