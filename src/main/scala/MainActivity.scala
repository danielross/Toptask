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

import net.danross.toptask.model.{TaskModel, TaskController}
import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar

import scala.collection.mutable.PriorityQueue

class MainActivity extends Activity
{

    lazy val insertButton = findViewById (R.id.insertbutton).asInstanceOf[Button]
    lazy val refreshButton = findViewById (R.id.refreshbutton).asInstanceOf[Button]    
    lazy val main_spinner = findViewById (R.id.main_spinner).asInstanceOf[Spinner]

    lazy val taskList = findViewById (R.id.tasklist).asInstanceOf[ListView]

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

          refreshButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    val refreshIntent = new Intent (view.getContext (), classOf[MainActivity])
                        startActivityForResult (refreshIntent, 0)

                }
            })
          
         // Fake tasks for soutenance purpose
        val task1 = new Task ("Find a job", 4, 5, None, "I need to find a job because I have almost finished my studies!")

        val task2 = new Task ("Implement Toptask", 1, 4, Some (new GregorianCalendar (2011, Calendar.JUNE, 30).getTime), "Toptask is a prioritized todo list application")

        val cal3 = new GregorianCalendar (2011, Calendar.MAY, 24, 20, 0).getTime
        val task3 = new Task ("Cook supper", 0, 4, Some (cal3))

           val task4 = new Task ("Go shopping", 3, 5, None, "Buy lots of alcohol for the party!")

        val task5 = new Task ("Collect Ben from the airport", 0, 5, Some (new GregorianCalendar (2011, Calendar.AUGUST, 30).getTime), "Flying into CGD1")

        val cal6 = new GregorianCalendar (2011, Calendar.OCTOBER, 24, 20, 0).getTime
        val task6 = new Task ("Bundesliga Erste Wettkampf", 0, 5, Some (cal6))

           val task7 = new Task ("Pay speeding fine", 3, 3, Some (new GregorianCalendar (2011, Calendar.JUNE, 23).getTime), "I did not really speed this is an example ;)")

        val cal8 = new GregorianCalendar (2012, Calendar.FEBRUARY, 25, 20, 0).getTime
        val task8 = new Task ("Buy present for Mum", 0, 4, Some (cal8))

           val task9 = new Task ("Fix my computer", 1, 5, None, "Or buy a new one :)")

        val task10 = new Task ("Reply to job offers", 4, 5, Some (new GregorianCalendar (2011, Calendar.JULY, 10).getTime), "Determine what I want to do with my life!")

        val cal11 = new GregorianCalendar (2011, Calendar.NOVEMBER, 5, 20, 0).getTime
        val task11 = new Task ("Bundesliga Zweite Wettkampf", 0, 5, Some (cal3))


        val pq = new PriorityQueue[Task]
        pq += task1
        pq += task2
        pq += task3
        pq += task4
        pq += task5
        pq += task6
        pq += task7
        pq += task8
        pq += task9
        pq += task10
        pq += task11


        var list1 = new ToptaskList ("Family", new PriorityQueue[Task])
        var list2 = new ToptaskList ("Work", pq)

        var listItem = new java.util.ArrayList[java.util.HashMap[String,String]]

        val taskA = pq.dequeue
        val taskB = pq.dequeue
        val taskC = pq.dequeue
        val taskD = pq.dequeue
        val taskE = pq.dequeue
        val taskF = pq.dequeue
        val taskG = pq.dequeue
        val taskH = pq.dequeue
        val taskI = pq.dequeue
        val taskJ = pq.dequeue
        val taskK = pq.dequeue




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

        map = new java.util.HashMap[String,String]
        map.put ("name", taskD.getName)
        map.put ("description", taskD.getDescription)
        map.put ("img", Category.get("leisure"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskE.getName)
        map.put ("description", taskE.getDescription)
        map.put ("img", Category.get("leisure"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskF.getName)
        map.put ("description", taskF.getDescription)
        map.put ("img", Category.get("other"))
        listItem.add (map)

         map = new java.util.HashMap[String,String]
        map.put ("name", taskG.getName)
        map.put ("description", taskG.getDescription)
        map.put ("img", Category.get("work"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskH.getName)
        map.put ("description", taskH.getDescription)
        map.put ("img", Category.get("work"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskI.getName)
        map.put ("description", taskI.getDescription)
        map.put ("img", Category.get("leisure"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskJ.getName)
        map.put ("description", taskJ.getDescription)
        map.put ("img", Category.get("leisure"))
        listItem.add (map)

        map = new java.util.HashMap[String,String]
        map.put ("name", taskK.getName)
        map.put ("description", taskK.getDescription)
        map.put ("img", Category.get("other"))
        listItem.add (map)
     
        val list = new TaskController
        var lists:java.util.List[TaskModel] = null
        
        try {
            lists = list.getAllTasks()
        } catch  {
            case e:Exception => e.printStackTrace()
        }
        if (lists != null) {
            for (i<- 0 to lists.size) {
                    if (lists.get(i) != null) {
                        map = new java.util.HashMap[String,String]
                        map.put ("name", lists.get(i).getName)
                        map.put ("description", lists.get(i).getDescription)
                        map.put ("img", Category.get(lists.get(i).getCategory))
                        listItem.add (map)
                }
            }
        }

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
