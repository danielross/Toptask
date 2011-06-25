package net.danross.toptask

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.{Button}
import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine


class LoginActivity extends Activity
{
    lazy val loginButton = findViewById (R.id.loginbutton).asInstanceOf[Button]
    lazy val addButton = findViewById (R.id.insertuserbutton).asInstanceOf[Button]

    override def onCreate (savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())

        super.onCreate (savedInstanceState)
        setContentView (R.layout.login)

        loginButton.setOnClickListener (new View.OnClickListener {
                // TODO properly one day...
                override def onClick (view: View) {
                    val mainIntent = new Intent (view.getContext (), classOf[MainActivity])
                        startActivityForResult (mainIntent, 0)
                }
            })

        addButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    val addUserIntent = new Intent (view.getContext (), classOf[AddUserActivity])
                        startActivityForResult (addUserIntent, 0)
                }
            })
    }
}
