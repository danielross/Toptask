package net.danross.toptask

import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar

import scala.collection.mutable.PriorityQueue


// ******************************
// Task
// ******************************
class Task (name: String,
            pStart: Int,
            pEnd: Int = 5,
            due: Option[Date],
            description: String = "No description")
{
    val tsStart = new Date ().getTime

    // It seems I need to do this which is weird
    def getName = name
    def getDescription = description

    def timeLapse = {
        if (due.isDefined) {
            val tl = due.get.getTime - tsStart
            if (tl > 0)
                tl
            else
                -2 //TODO with errors
        }
        else
            -1
    }

    def priority = {
        val tsNow = new Date ().getTime
        val x = tsNow - tsStart

        if (timeLapse == -1)
            pStart
        else if (timeLapse == -2) {
            //println ("Task is overdue")
            pEnd
        }
        else {
            val a = (pEnd - pStart) / (timeLapse * 1.0) //TODO cast float properly
            a * x + pStart
        }
    }

    override def toString = {
        String.format (name + " (%.2f):\n" + description, double2Double(priority))
    }
}

object Task
{
    implicit def orderTask: Ordering[Task] =
        new Ordering[Task] {
            def compare (thisTask: Task, otherTask: Task) =
                thisTask.priority.compare (otherTask.priority)
        }
}


// ******************************
// ToptaskList
// ******************************
class ToptaskList (name: String, list: PriorityQueue[Task])
{
    override def toString = {
        var tasks = ""
        for (iter<-list.iterator) {
            tasks += iter + "\n"
        }
        name + "\n" + tasks
    }

}

