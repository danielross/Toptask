package persist

import com.googlecode.objectify.{Key, ObjectifyService}
import com.googlecode.objectify.helper._
import com.googlecode.objectify.annotation.{Parent, Cached, Unindexed}
import javax.persistence.{Id}

import java.util.Date
import java.text.SimpleDateFormat;

import net.liftweb.json._
import net.liftweb.json.JsonDSL._

@Cached
class User
{
    @Id var id: String = _
    var email: String = _
    @Unindexed var password: String = _

    def addTask (task: Task) = {
        task.setOwner (this)
        val ofy = ObjectifyService.begin
        ofy.put (task):Key[Task]
    }

    def addTasks (tasks: List[Task]) = {
        for (task <- tasks) {
            task.setOwner (this)
        }
        val ofy = ObjectifyService.begin
        ofy.put (tasks):Key[List[Task]]
    }

    def listAllTasks = {
        val ofy = ObjectifyService.begin
        ofy.query (classOf[Task]).filter ("owner", this).order ("-pStart").list
    }

    def listAllTasksByCategory (cat: String) = {
        val ofy = ObjectifyService.begin
        ofy.query (classOf[Task]).filter ("owner", this).filter ("category", cat).order ("-pStart").list
    }

    def save = {
        val ofy = ObjectifyService.begin
        ofy.put (this):Key[User]
    }

    override def toString = {
        this.email
    }

    def toJSON = {
        val tasks = this.listAllTasks.asInstanceOf[List[Task]]
        val json =  ("userID" -> this.id) 
                    ("tasks" -> tasks.map { w =>
                          (("taskID" -> w.id) ~
                            ("name" -> w.name) ~
                            ("category" -> w.category) ~
                            ("description" -> w.description) ~
                            ("start-priority" -> w.pStart) ~
                            ("end-priority" -> w.pEnd) ~
                            ("due-date" -> w.due.map (_.toString)))})           
        compact (render (json))  
    }
}

@Cached
class Task
{
    @Id var id: String = _
    @Parent var owner: Key[User] = _
    var name: String = _
    var category: String = _    
    var pStart: Int = _ 
    var pEnd: Int = _
    var due: Option[Date] = _ //Does notlike anything other than String/Int
    @Unindexed var description: String = _

    def setOwner (owner: User) = {
        this.owner = new Key (classOf[User], owner.id)
    }

    def formatDate = {
        val formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.format(this.due)
      }

    def toJSON = {
        val json =  ("name" -> this.name) ~
                    ("category" -> this.category) ~
                    ("description" -> this.description) ~
                    ("start-priority" -> this.pStart) ~
                    ("end-priority" -> this.pEnd) ~
                    ("due-date" -> this.due.map (_.toString))
        compact (render (json))  
    }

}


object Dao extends DAOBase 
{
    def register = {
        ObjectifyService.register (classOf[User])
        ObjectifyService.register (classOf[Task])
    }

    // For testing purposes and hopefully soutenance one day!
    def initDanRoss = {
        val ofy = ObjectifyService.begin
        val danross = new User
        danross.email = "daniel.je.ross@gmail.com"
        danross.password = "danielross" // This is not my real password!
        
        val task1 = new Task
        val task2 = new Task
        val task3 = new Task
        val task4 = new Task
        val task5 = new Task
        val task6 = new Task
        val task7 = new Task
        val task8 = new Task
        
        task1.name = "task1"
        task2.name = "task2"
        task3.name = "task3"
        task4.name = "task4"
        task5.name = "task5"
        task6.name = "task6"
        task7.name = "task7"
        task8.name = "task8"

        task1.category = "home"
        task2.category = "home"
        task3.category = "other"
        task4.category = "leisure"
        task5.category = "work"
        task6.category = "leisure"
        task7.category = "family"
        task8.category = "work"

        task1.description = "This is task1"
        task2.description = "This is task2"
        task3.description = "This is task3"
        task4.description = "This is task4"
        task5.description = "This is task5"
        task6.description = "This is task6"
        task7.description = "This is task7"
        task8.description = "This is task8"

        task1.pStart = 1
        task2.pStart = 0
        task3.pStart = 2
        task4.pStart = 5
        task5.pStart = 3
        task6.pStart = 4
        task7.pStart = 2
        task8.pStart = 2

        task1.pEnd = 3
        task2.pEnd = 3
        task3.pEnd = 4
        task4.pEnd = 5
        task5.pEnd = 3
        task6.pEnd = 4
        task7.pEnd = 5
        task8.pEnd = 4

        task1.due = None
        task2.due = None
        task3.due = None
        task4.due = None
        task5.due = None
        task6.due = None
        task7.due = None
        task8.due = None

        task1.setOwner (danross)
        task2.setOwner (danross)
        task3.setOwner (danross)
        task4.setOwner (danross)
        task5.setOwner (danross)
        task6.setOwner (danross)
        task7.setOwner (danross)
        task8.setOwner (danross)
      
        danross.save
    }

}
