package com.example.shen.loginauthentication3;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LENOVO_PC on 19/11/2017.
 */

public class ProfileData extends ArrayAdapter<GestureDetails> {

    private Activity context;
    private List<GestureDetails> profileList;

    public ProfileData(Activity context, List<GestureDetails> profileList){
        super(context, R.layout.view_profile, profileList);
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.view_profile, null, true);

        TextView textViewGesture = (TextView) listViewItem.findViewById(R.id.tvViewGestures);

        GestureDetails gestureDetails = profileList.get(position);

        textViewGesture.setText(gestureDetails.getDeviceMan());

        return listViewItem;
    }
}
