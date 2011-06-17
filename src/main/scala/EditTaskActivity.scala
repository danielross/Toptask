package net.danross.toptask

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.{Spinner, ArrayAdapter, Button}

class EditTaskActivity extends Activity 
{

   lazy val confirmButton = findViewById(R.id.confirm).asInstanceOf[Button]
   lazy val category_spinner = findViewById (R.id.category_spinner).asInstanceOf[Spinner]
   lazy val start_spinner = findViewById (R.id.start_spinner).asInstanceOf[Spinner]
   lazy val end_spinner = findViewById (R.id.end_spinner).asInstanceOf[Spinner]


    override def onCreate (savedInstanceState: Bundle) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.edit_task)

        val category_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.categories, android.R.layout.simple_spinner_item)
        val start_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.priorities, android.R.layout.simple_spinner_item)
        val end_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.priorities, android.R.layout.simple_spinner_item)
        
        category_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)
        start_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)
        end_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)

        category_spinner.setAdapter (category_adapter)
        start_spinner.setAdapter (start_adapter)
        end_spinner.setAdapter (end_adapter)

        confirmButton.setOnClickListener (new View.OnClickListener {
            override def onClick (view: View) {
                setResult (Activity.RESULT_OK)
                finish ()
            }
        })
        
      }      
}
