package ie.ul.mad.universityhub;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestTask extends AsyncTask<String[], Integer, String[]>
{
    @SuppressLint("StaticFieldLeak")
    SharedRemindersActivity activity;
    RequestTask(SharedRemindersActivity activity)
    {
        this.activity = activity;
    }
    protected String[] doInBackground(String[]... params)
    {
        HttpURLConnection con;
        String url = "http://universityhubreminders.epizy.com?action=add_point&text=sometext&checked=1&reminder_id=911";
        StringBuilder content = new StringBuilder();

        try {

            URL myurl = buildURL(params);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Cookie", "_ga=GA1.2.1766581258.1523836774; _gid=GA1.2.446969222.1523836774; __test=a573ec8309d0744c08cef7cc59b117b7");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())))
            {

                String line;

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] toReturn = {params[0][1], content.toString()};
        return toReturn;
    }
    protected void onPostExecute(String[] funcAndResult)
    {
        this.activity.requestResult = funcAndResult[1];
        // TODO if it was loading of reminders or points - call activity function to update it
        if (funcAndResult[0].equals("create_reminders"))
        {
            this.activity.afterRemindersCreate();
        }
        if (funcAndResult[0].equals("get_reminders_by_code"))
        {
            this.activity.afterRemindersLoad();
        }
        if (funcAndResult[0].equals("get_points_by_reminders_id"))
        {
            this.activity.afterPointsLoaded();
        }
    }
    private static URL buildURL(String[] ... params)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("universityhubreminders.epizy.com");
        for (int count = 0; count < params.length; count ++)
        {
            builder.appendQueryParameter(params[count][0], params[count][1]);
        }
        try
        {
            builder.build();
            URL urlToReturn = new URL(builder.toString());
            System.out.print(urlToReturn.toString());
            return urlToReturn;
        }
        catch (java.net.MalformedURLException exc)
        {
            exc.printStackTrace();
        }
        return null;
    }
}