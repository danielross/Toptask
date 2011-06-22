package net.danross.toptask

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.{Button}

class LoginActivity extends Activity
{
    lazy val loginButton = findViewById (R.id.loginbutton).asInstanceOf[Button]

    override def onCreate (savedInstanceState: Bundle) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.login)

        loginButton.setOnClickListener (new View.OnClickListener {
                // TODO properly one day...
                override def onClick (view: View) {
                    val mainIntent = new Intent (view.getContext (), classOf[MainActivity])
                    startActivityForResult (mainIntent, 0)
                }
            })

    }
}
