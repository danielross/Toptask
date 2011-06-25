package net.danross.toptask;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.danross.toptask.model.TaskModel;
import net.danross.toptask.model.TaskController;

import org.restlet.ext.jackson.JacksonConverter;
import org.restlet.engine.Engine;

public class GetAllTaskActivity extends Activity {
    private List<TaskModel> lists = null;
    private List<String> listsName = null;
    private ListView listLists;

    public void onCreate(Bundle savedInstanceState) {
        Engine.getInstance().getRegisteredConverters().clear();
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_users);

        listLists = (ListView) findViewById(R.id.listLists);

        getTasks();

    }

    final void getTasks() {

        TaskController list = new TaskController();
        listsName = new ArrayList<String>();
        try {
            lists = list.getAllTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lists != null) {
            for (TaskModel u : lists) {
                if (u != null)
                    listsName.add(u.getName() + " " + u.getCategory());
            }

            listLists.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_list_item_1, listsName));

            listLists.setTextFilterEnabled(true);

        }
    }
}

