package ie.ul.mad.universityhub;

public class Point
{
    String id;
    String reminder_id;
    String text;
    boolean checked;

    Point(String id, String reminder_id, String text, String checked)
    {
        this.id = id;
        this.reminder_id = reminder_id;
        this.text = text;
        if (checked.equals("1"))
        {
            this.checked = true;
        }
        else
        {
            this.checked = false;
        }
    }
    Point(String id)
    {
        this.id = id;
    }
    public void dbCreate(SharedRemindersActivity activity)
    {
        String checkedString;
        if (this.checked)
        {
            checkedString = "1";
        }
        else
        {
            checkedString = "0";
        }
        String [] action = {"action", "add_point"};
        String [] text = {"text", this.text};
        String [] checked = {"checked", checkedString};
        String [] reminder_id = {"reminder_id", this.reminder_id};
        new RequestTask(activity).execute(action, text, checked, reminder_id);
    }
    public void dbRemove(SharedRemindersActivity activity)
    {
        String [] action = {"action", "remove_point"};
        String [] id = {"id", this.id};
        new RequestTask(activity).execute(action, id);
    }
    public void dbUpdate(SharedRemindersActivity activity)
    {
        String checkedString;
        if (this.checked)
        {
            checkedString = "1";
        }
        else
        {
            checkedString = "0";
        }
        String [] action = {"action", "update_point"};
        String [] text = {"text", this.text};
        String [] checked = {"checked", checkedString};
        String [] id = {"id", this.id};
        new RequestTask(activity).execute(action, text, checked, id);
    }
    private void dbGet(SharedRemindersActivity activity)
    {
        String [] action = {"action", "get_point"};
        String [] id = {"id", this.id};
        new RequestTask(activity).execute(action, id);
    }
}
