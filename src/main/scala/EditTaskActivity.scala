package net.danross.toptask

import android.app.{Activity, AlertDialog, DatePickerDialog, TimePickerDialog, Dialog}
import android.os.Bundle
import android.view.View
import android.widget.{Spinner, ArrayAdapter, Button, ImageButton, DatePicker, TimePicker, EditText}

import java.util.Calendar
import org.restlet.ext.jackson.JacksonConverter
import org.restlet.engine.Engine

object EditTaskActivity
{
    final val START_DATE_ID = 0
    final val START_TIME_ID = 1
    final val END_DATE_ID = 2
    final val END_TIME_ID = 3

    private def pad (c: Int): String =
    (if (c >= 10) "" else "0") + String.valueOf (c)

}

class EditTaskActivity extends Activity 
{
    import EditTaskActivity._ //companion object

    // Start Date variables
    private var startDateDisplay: EditText = _
    private var startPickDate: ImageButton = _   
    private var startYear: Int = _
    private var startMonth: Int = _
    private var startDay: Int = _

    // End Date variables
    private var endDateDisplay: EditText = _
    private var endPickDate: ImageButton = _   
    private var endYear: Int = _
    private var endMonth: Int = _
    private var endDay: Int = _

    // Start Time variables
    private var startTimeDisplay: EditText = _
    private var startPickTime: ImageButton = _
    private var startHour: Int = _
    private var startMinute: Int = _

    // End Time variables
    private var endTimeDisplay: EditText = _
    private var endPickTime: ImageButton = _
    private var endHour: Int = _
    private var endMinute: Int = _


    // Spinners and confirm button
    lazy val confirmButton = findViewById (R.id.confirm).asInstanceOf[Button]
    lazy val category_spinner = findViewById (R.id.category_spinner).asInstanceOf[Spinner]
    lazy val start_spinner = findViewById (R.id.start_spinner).asInstanceOf[Spinner]
    lazy val end_spinner = findViewById (R.id.end_spinner).asInstanceOf[Spinner]


    // Entry Point
    override def onCreate (savedInstanceState: Bundle) {
        Engine.getInstance().getRegisteredConverters().clear()
        Engine.getInstance().getRegisteredConverters().add(new JacksonConverter())

        super.onCreate (savedInstanceState)
        setContentView (R.layout.edit_task)

        startDateDisplay = findViewById (R.id.startdate).asInstanceOf[EditText]
        startPickDate = findViewById (R.id.startdatebutton).asInstanceOf[ImageButton]
        startTimeDisplay = findViewById (R.id.starttime).asInstanceOf[EditText]
        startPickTime = findViewById (R.id.starttimebutton).asInstanceOf[ImageButton]
        endDateDisplay = findViewById (R.id.enddate).asInstanceOf[EditText]
        endPickDate = findViewById (R.id.enddatebutton).asInstanceOf[ImageButton]
        endTimeDisplay = findViewById (R.id.endtime).asInstanceOf[EditText]
        endPickTime = findViewById (R.id.endtimebutton).asInstanceOf[ImageButton]



        val category_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.categories, android.R.layout.simple_spinner_item)
            val start_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.priorities, android.R.layout.simple_spinner_item)
            val end_adapter:ArrayAdapter[CharSequence] = ArrayAdapter.createFromResource (this, R.array.priorities, android.R.layout.simple_spinner_item)

            category_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)
        start_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)
        end_adapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item)

        category_spinner.setAdapter (category_adapter)
        start_spinner.setAdapter (start_adapter)
        end_spinner.setAdapter (end_adapter)


        // add a click listener to the date buttons
        startPickDate setOnClickListener new View.OnClickListener() {
            def onClick (v: View) {
                showDialog (START_DATE_ID)
            }
        }

        endPickDate setOnClickListener new View.OnClickListener() {
            def onClick (v: View) {
                showDialog (END_DATE_ID)
            }
        }

        // add a click listener to the time buttons
        startPickTime setOnClickListener new View.OnClickListener() {
            def onClick (v: View) {
                showDialog (START_TIME_ID)
            }
        }

        endPickTime setOnClickListener new View.OnClickListener() {
            def onClick (v: View) {
                showDialog (END_TIME_ID)
            }
        }


        // get the current dates
        val c = Calendar.getInstance
        startYear = c get Calendar.YEAR
        startMonth = c get Calendar.MONTH
        startDay = c get Calendar.DAY_OF_MONTH
        startHour = c get Calendar.HOUR_OF_DAY
        startMinute = c get Calendar.MINUTE        
        endYear = c get Calendar.YEAR
        endMonth = c get Calendar.MONTH
        endDay = c get Calendar.DAY_OF_MONTH
        endHour = c get Calendar.HOUR_OF_DAY
        endMinute = c get Calendar.MINUTE



        // display the current dates and times (these methods are below)
        updateDateDisplay (START_DATE_ID)
        updateDateDisplay (END_DATE_ID)
        updateTimeDisplay (START_TIME_ID)
        updateTimeDisplay (END_TIME_ID)

        confirmButton.setOnClickListener (new View.OnClickListener {
                override def onClick (view: View) {
                    setResult (Activity.RESULT_OK)
                    finish ()
                }
            })
    }

    // updates the start date in the EditView
    private def updateDateDisplay (id: Int) {
        val startDate =  new StringBuilder ()
            .append(startDay).append("-")        
        // Month is 0 based so add 1
        .append(startMonth + 1).append("-")
        .append(startYear).append(" ")
        val endDate =  new StringBuilder ()
            .append(endDay).append("-")        
        .append(endMonth + 1).append("-")
        .append(endYear).append(" ")

        id match {
            case START_DATE_ID => startDateDisplay setText startDate
            case END_DATE_ID => endDateDisplay setText endDate
            case _ => null
        }
    }

    private def updateTimeDisplay (id: Int) {
        val startTime = new StringBuilder ()
            .append(pad(startHour)).append(":")
        .append(pad(startMinute))
        val endTime = new StringBuilder ()
            .append(pad(endHour)).append(":")
        .append(pad(endMinute))        
        id match {
            case START_TIME_ID => startTimeDisplay setText startTime
            case END_TIME_ID => endTimeDisplay setText endTime
            case _ => null
        }
    }


    // the callback received when the user "sets" the date in the dialog
    private val startDateSetListener = new DatePickerDialog.OnDateSetListener() {
        def onDateSet (view: DatePicker, year: Int, 
            monthOfYear: Int, dayOfMonth: Int) {
            startYear = year
            startMonth = monthOfYear
            startDay = dayOfMonth
            updateDateDisplay (START_DATE_ID)
        }
    }

    private val endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        def onDateSet (view: DatePicker, year: Int, 
            monthOfYear: Int, dayOfMonth: Int) {
            endYear = year
            endMonth = monthOfYear
            endDay = dayOfMonth
            updateDateDisplay (END_DATE_ID)
        }
    }

    // the callback received when the user "sets" the time in the dialog
    private val startTimeSetListener =
    new TimePickerDialog.OnTimeSetListener() {
        def onTimeSet (view: TimePicker, hourOfDay: Int, minute: Int) {
            startHour = hourOfDay
            startMinute = minute
            updateTimeDisplay (START_TIME_ID)
        }
    }
    private val endTimeSetListener =
    new TimePickerDialog.OnTimeSetListener() {
        def onTimeSet (view: TimePicker, hourOfDay: Int, minute: Int) {
            endHour = hourOfDay
            endMinute = minute
            updateTimeDisplay (END_TIME_ID)
        }
    }

    override protected def onCreateDialog (id: Int): Dialog =
    id match {
        case START_DATE_ID =>
        new DatePickerDialog (this, startDateSetListener, startYear, startMonth, startDay)
        case END_DATE_ID =>
        new DatePickerDialog (this, endDateSetListener, endYear, endMonth, endDay)
        case START_TIME_ID =>
        new TimePickerDialog (this, startTimeSetListener, startHour, startMinute, false)
        case END_TIME_ID =>
        new TimePickerDialog (this, endTimeSetListener, endHour, endMinute, false)
        case _ =>
        null
    }
}
