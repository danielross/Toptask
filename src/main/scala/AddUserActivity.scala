package net.danross.toptask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.{Button, EditText, Toast}

import net.danross.toptask.model.User
import net.danross.toptask.model.UserController
import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine

class AddUserActivity extends Activity {

    lazy val addUserButton = findViewById (R.id.addUserNow).asInstanceOf[Button]


    override def onCreate(savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        addUserButton.setOnClickListener (new OnClickListener() {

                override def onClick (v: View) {
                    addUser()
                }
            })
    }


    final def addUser() {

        val checkUpdate = new Thread() {
            override def run() {
                val name = findViewById (R.id.userName).asInstanceOf[EditText]
                val firstname = findViewById(R.id.userFirstName).asInstanceOf[EditText]
                val mail = findViewById(R.id.userMail).asInstanceOf[EditText]
                val phone = findViewById(R.id.userPhone).asInstanceOf[EditText]
                val user = new User
                user.setFirstname(firstname.getText().toString())
                user.setLastname(name.getText().toString())
                user.setMail(mail.getText().toString())
                user.setPhone(phone.getText().toString())

                val uc = new UserController
                try {
                    uc.create (user)
                    //Toast.makeText(AddUserActivity.this, "User saved!", Toast.LENGTH_SHORT).show()

                } catch {
                    case e: Exception => 
                    runOnUiThread (new Runnable() {
                        override def run() {
                            // Display warning
                            val ctx = getApplicationContext
                            val txt = "Sorry, user was not saved!"
                            val duration = Toast.LENGTH_LONG
                            val toast = Toast.makeText(ctx, txt, duration)
                            toast.show()
                        }
                    })                         
                }

                  val intent = new Intent(AddUserActivity.this, classOf[GaeHomeScreen])
                  startActivity (intent)
            }
        }

        checkUpdate.start
    }
}
