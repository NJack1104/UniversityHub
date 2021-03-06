package ie.ul.mad.universityhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OfficeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_office, container, false);

        Button Test = (Button) view.findViewById(R.id.C_Reminder);
        Test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), ReminderActivity.class);
                startActivity(in);
            }
        });

        Button Test1 = (Button) view.findViewById(R.id.C_Event);
        Test1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getActivity(), CalendarActivity.class);
                startActivity(in);
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Office");
    }
}