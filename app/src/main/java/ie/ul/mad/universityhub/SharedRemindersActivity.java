package ie.ul.mad.universityhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SharedRemindersActivity extends AppCompatActivity
{
    String requestResult;
    Button sharedRemindersGoButton;
    EditText codeTextTextView;
    EditText nameTextView;
    SharedReminders sReminders;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_reminders);

        sharedRemindersGoButton = findViewById(R.id.shared_reminders_button);
        sharedRemindersGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                codeTextTextView = findViewById(R.id.shared_reminders_listcode_edittext);
                String codeText = codeTextTextView.getText().toString();
                if (codeText.isEmpty())
                {
                    EditText nameTextView = findViewById(R.id.shared_reminders_name_edittext);
                    createReminders(nameTextView.getText().toString());
                }
                else
                {
                    loadReminders(codeText);
                }

            }
        });

    }
    public void showMessage(String message)
    {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void loadReminders(String code)
    {
        this.sReminders = new SharedReminders(code, true);
        this.sReminders.dbGetByCode(this);
    }

    public void afterRemindersLoad()
    {
        JSONArray inputArray = null;
        try
        {
            showMessage(this.requestResult);
            inputArray = new JSONArray(this.requestResult);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try {
            if (!inputArray.get(0).toString().equals("true"))
            {
                ArrayList<String> listdata = new ArrayList<String>();
                    for (int i=0;i<inputArray.length();i++){
                        listdata.add(inputArray.getString(i));
                    }
                showMessage("error, try again");
            }
            else
            {
                this.sReminders.dbGetAllPoints(this);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    protected void createReminders(String name)
    {
        this.sReminders = new SharedReminders(name);
        this.sReminders.dbCreate(this);
    }

    public void afterRemindersCreate()
    {
        showMessage("reminders should have been created");
    }

    public void afterPointsLoaded()
    {
        showMessage(this.requestResult);
    }

    public void showReminders()
    {

    }
}