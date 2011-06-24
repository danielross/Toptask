package net.danross.toptask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import net.danross.toptask.model.User;
import net.danross.toptask.model.UserController;

public class AddUserActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.add_user);

	Button btn = (Button) findViewById(R.id.addUserNow);
	btn.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
			addUser();			
	    }
	});
    }
    
    final void addUser()
    {
	final Thread checkUpdate = new Thread() {
	    public void run() {
		EditText name = (EditText) findViewById(R.id.userName);
		EditText firstname = (EditText) findViewById(R.id.userFirstName);
		EditText mail = (EditText) findViewById(R.id.userMail);
		EditText phone = (EditText) findViewById(R.id.userPhone);
		User u = new User();
		u.setFirstname(firstname.getText().toString());
		u.setLastname(name.getText().toString());
		u.setMail(mail.getText().toString());
		u.setPhone(phone.getText().toString());
		final UserController uc = new UserController();
		try {
		    uc.create(u);
		} catch (Exception e) {
		    return;
		}
		final Intent intent = new Intent(AddUserActivity.this,
			GaeHomeScreen.class);
		startActivity(intent);
	    }
	};
	checkUpdate.start();
    }
}
