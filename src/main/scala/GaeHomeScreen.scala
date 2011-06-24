package net.danross.toptask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine


class GaeHomeScreen extends Activity {
  
    lazy val addUser = findViewById (R.id.addUser).asInstanceOf[Button]
    lazy val getUsers = findViewById (R.id.getUsers).asInstanceOf[Button]    
    
    override def onCreate (savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
    	Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())

        super.onCreate (savedInstanceState)
        setContentView (R.layout.main2)
        
        addUser.setOnClickListener (new OnClickListener() {
            override def onClick (v: View) {
        		val addIntent = new Intent(v.getContext, classOf[AddUserActivity])
        		startActivity (addIntent)
	        }
	    })
        
        getUsers.setOnClickListener(new OnClickListener() {
            override def onClick (v: View) {
                val getUsersIntent = new Intent (v.getContext, classOf[GetAllUserActivity])
        		startActivity (getUsersIntent)
	        }
	    })
        
    }
    
}

