package com.temal.chakib.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BasicSpinnerAdapter extends BaseAdapter {

    private List<String> mSpinnerItems = new ArrayList<String>();
    private List<Contact> mData = new ArrayList<Contact>();
    private Context mContext;

    public BasicSpinnerAdapter(List<Contact> mData , List<String> mSpinnerItems , Context mContext) {
        this.mSpinnerItems = mSpinnerItems;
        this.mData = mData;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return  mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.contact_item, null);


        TextView firstname_ui = (TextView) root.findViewById(R.id.firstName);
        TextView lastname_ui = (TextView) root.findViewById(R.id.lastName);
        TextView phone_ui = (TextView) root.findViewById(R.id.phoneNumber);
        TextView age_ui = (TextView) root.findViewById(R.id.age);

        //((ViewGroup) age_ui.getParent()).removeView(age_ui);

        Contact contact = (Contact) getItem(position);
        firstname_ui.setText(contact.getFirstName());
        lastname_ui.setText(contact.getLastName());
        phone_ui.setText(contact.getPhoneNumber());

        Spinner spinner = (Spinner) root.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mSpinnerItems);
        spinner.setAdapter(adapter);

        spinner.setFocusable(false);



//            age_ui.setText(contact.getAge());
        return root;
    }

}
