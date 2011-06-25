package net.danross.toptask

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.{Button, EditText, Toast}
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
                      if (checkLogin)
                          startActivityForResult (mainIntent, 0)
                      else
                          Toast.makeText(LoginActivity.this, "Wrong login or password", Toast.LENGTH_LONG).show()         
                }
            })

        addButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    val addUserIntent = new Intent (view.getContext (), classOf[AddUserActivity])
                        startActivityForResult (addUserIntent, 0)
                }
            })
    }

    def checkLogin = {
        //TODO properly with thread to lookup datastore
        val name = findViewById (R.id.email).asInstanceOf[EditText]
        val password = findViewById(R.id.password).asInstanceOf[EditText]

        if (name.getText.toString == "daniel@ross.com" && 
                password.getText.toString == "danielross")
              true
              else
                false
    }
}
