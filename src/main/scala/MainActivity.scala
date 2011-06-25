package net.danross.toptask

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.{AdapterView, ListView, SimpleAdapter, Button, Spinner, ArrayAdapter}
import android.widget.AdapterView.OnItemClickListener

import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine

import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar

import scala.collection.mutable.PriorityQueue

class MainActivity extends Activity
{

    lazy val insertButton = findViewById (R.id.insertbutton).asInstanceOf[Button]
    lazy val main_spinner = findViewById (R.id.main_spinner).asInstanceOf[Spinner]

    lazy val taskList:ListView = findViewById (R.id.tasklist).asInstanceOf[ListView]

    override def onCreate (savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())

        super.onCreate (savedInstanceState)
        setContentView (R.layout.main)

        val main_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.filtercategories, android.R.layout.simple_spinner_item)
            main_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)
        main_spinner.setAdapter (main_adapter)        

        taskList.setOnItemClickListener (new OnItemClickListener {
                override def onItemClick (parent:AdapterView[_], view: View, position: Int, id: Long) {
                    val viewIntent = new Intent (view.getContext (), classOf[ViewTaskActivity])
                        startActivityForResult (viewIntent, 0)
                }
            })

        insertButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    val insertIntent = new Intent (view.getContext (), classOf[EditTaskActivity])
                        startActivityForResult (insertIntent, 0)

                }
            })

        val task1 = new Task ("Find a job", 4, 5, None, "I need to find a job because I have almost finished my studies!")

        val task2 = new Task ("Implement Toptask", 1, 4, Some (new GregorianCalendar (2011, Calendar.JUNE, 30).getTime), "Toptask is a prioritized todo list application")

        val cal3 = new GregorianCalendar (2011, Calendar.MAY, 24, 20, 0).getTime
        val task3 = new Task ("Cook supper", 0, 4, Some (cal3))

        val pq = new PriorityQueue[Task]
        pq += task1
        pq += task2
        pq += task3

        var list1 = new ToptaskList ("Family", new PriorityQueue[Task])
        var list2 = new ToptaskList ("Work", pq)

        var listItem = new java.util.ArrayList[java.util.HashMap[String,String]]

        val taskA = pq.dequeue
        val taskB = pq.dequeue
        val taskC = pq.dequeue

        var map = new java.util.HashMap[String,String]
        map.put ("name", taskA.getName)
        map.put ("description", taskA.getDescription)
        map.put ("img", Category.get("home"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskB.getName)
        map.put ("description", taskB.getDescription)
        map.put ("img", Category.get("work"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskC.getName)
        map.put ("description", taskC.getDescription)
        map.put ("img", Category.get("family"))
        listItem.add (map)

        val mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.listview, Array ("img", "name", "description"), Array (R.id.img, R.id.name, R.id.description))

        taskList.setAdapter (mSchedule)

    }
}

object Category
{
    val map = new java.util.HashMap[String,String]
    map.put ("home" , String.valueOf (R.drawable.blue_icon))
    map.put ("family" , String.valueOf (R.drawable.pink_icon))
    map.put ("work" , String.valueOf (R.drawable.gray_icon))
    map.put ("leisure" , String.valueOf (R.drawable.green_icon))
    map.put ("other" , String.valueOf (R.drawable.orange_icon))

    def get (cat: String) = map.get(cat)
}
