package ie.ul.mad.universityhub;

import java.util.ArrayList;
import java.util.List;

public class SharedReminders
{
    String name;
    String code;
    String id;
    List<Point> points = new ArrayList<>();

    SharedReminders(String name)
    {
        this.name = name;
    }

    SharedReminders(String idOrCode, boolean codeNotId)
    {
        if (codeNotId)
        {
            this.code = idOrCode;
        }
        else
        {
            this.id = idOrCode;
        }
    }
    public void dbCreate(SharedRemindersActivity activity)
    {
        String [] action = {"action", "create_reminders"};
        String [] name = {"name", this.name};
        new RequestTask(activity).execute(action, name);
    }

    public void dbGetAllPoints(SharedRemindersActivity activity)
    {
        String [] action = {"action", "get_points_by_reminders_id"};
        String [] reminders_id = {"reminder_id", this.id};
        new RequestTask(activity).execute(action, reminders_id);
    }

    public void dbRemove(SharedRemindersActivity activity)
    {
        String [] action = {"action", "remove_reminders"};
        String [] id = {"id", this.id};
        new RequestTask(activity).execute(action, id);
    }

    public void dbGetById(SharedRemindersActivity activity)
    {
        String [] action = {"action", "get_reminders"};
        String [] id = {"id", this.id};
        new RequestTask(activity).execute(action, id);
    }
    public void dbGetByCode(SharedRemindersActivity activity)
    {
        String [] action = {"action", "get_reminders_by_code"};
        String [] code = {"code", this.code};
        new RequestTask(activity).execute(action, code);
    }
    public void dbUpdate(SharedRemindersActivity activity)
    {
        String [] action = {"action", "update_reminders"};
        String [] name = {"name", this.name};
        new RequestTask(activity).execute(action, name);
    }
}