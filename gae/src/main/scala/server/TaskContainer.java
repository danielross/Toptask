package server;

import java.util.ArrayList;
import java.util.List;

import model.TaskModel;

public class TaskContainer
{
    public List<TaskModel> task_list;

    public List<TaskModel> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<TaskModel> task_list) {
        this.task_list = task_list;
    }

    public TaskContainer()
    {
	    task_list = new ArrayList<TaskModel>();
    }

    public TaskContainer(List<TaskModel> task_list)
    {
        this.task_list = task_list;
    }

}

