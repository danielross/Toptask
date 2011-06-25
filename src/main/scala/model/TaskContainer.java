package net.danross.toptask.model;

import java.util.ArrayList;
import java.util.List;

import net.danross.toptask.model.Task;

public class TaskContainer
{
    public List<Task> task_list;

    public List<Task> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<Task> task_list) {
        this.task_list = task_list;
    }

    public TaskContainer()
    {
	    task_list = new ArrayList<Task>();
    }

    public TaskContainer(List<Task> task_list)
    {
        this.task_list = task_list;
    }

}

