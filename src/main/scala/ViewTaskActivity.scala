package net.danross.toptask

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.{Button}
import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine

class ViewTaskActivity extends Activity 
{

    lazy val mainButton = findViewById (R.id.mainbutton).asInstanceOf[Button]
    lazy val editButton = findViewById (R.id.editbutton).asInstanceOf[Button]
    lazy val deleteButton = findViewById (R.id.deletebutton).asInstanceOf[Button]

    override def onCreate (savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())
        super.onCreate (savedInstanceState)
        setContentView (R.layout.view_task)

        mainButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    val mainIntent = new Intent (view.getContext (), classOf[MainActivity])
                        startActivityForResult (mainIntent, 0)

                }
            })


        deleteButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    //TODO delete properly from GAE
                    finish ()
                }
            })

        editButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    // TODO save info of this task in form
                    val editIntent = new Intent (view.getContext (), classOf[EditTaskActivity])
                        startActivityForResult (editIntent, 0)

                }
            })
    }
}
