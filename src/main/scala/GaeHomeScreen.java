package net.danross.toptask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import org.restlet.ext.jackson.JacksonConverter;
import org.restlet.engine.Engine;


public class GaeHomeScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Engine.getInstance().getRegisteredConverters().clear();
    	Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        Button addUser = (Button) findViewById(R.id.addUser);
        addUser.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Intent i = new Intent(GaeHomeScreen.this, AddUserActivity.class);
		startActivity(i);
	    }
	});
        
        Button getUsers = (Button) findViewById(R.id.getUsers);
        getUsers.setOnClickListener(new OnClickListener() {
	    
	    @Override
	    public void onClick(View arg0) {
		Intent i = new Intent(GaeHomeScreen.this, GetAllUserActivity.class);
		startActivity(i);
	    }
	});
        
    }
    
  
}
