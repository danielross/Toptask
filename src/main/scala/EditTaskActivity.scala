package net.danross.toptask

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.{Spinner, ArrayAdapter, Button}

class EditTaskActivity extends Activity 
{

    lazy val confirmButton = findViewById(R.id.confirm).asInstanceOf[Button]


    override def onCreate (savedInstanceState: Bundle) {
        super.onCreate (savedInstanceState)
        setContentView (R.layout.edit_task)

        val category_spinner:Spinner = findViewById (R.id.category_spinner).asInstanceOf[Spinner]
        val start_spinner:Spinner = findViewById (R.id.start_spinner).asInstanceOf[Spinner]
        val end_spinner:Spinner = findViewById (R.id.end_spinner).asInstanceOf[Spinner]
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
    


   // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
    //        this, R.array.planets_array, android.R.layout.simple_spinner_item);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  //  spinner.setAdapter(adapter);
      
}
