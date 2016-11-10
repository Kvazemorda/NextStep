package com.nextstep.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nextstep.R;
import com.nextstep.Task;
import com.nextstep.entity.PersonEntity;

import java.util.ArrayList;


public class AdapterPerson extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<PersonEntity> persons;

    public AdapterPerson(Context ctx, ArrayList<PersonEntity> persons) {
        this.ctx = ctx;
        this.persons = persons;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = lInflater.inflate(R.layout.task, parent, false);
        }
        PersonEntity personEntity = persons.get(position);

        ((TextView)view.findViewById(R.id.taskText)).setText(personEntity.getName());
        ((TextView)view.findViewById(R.id.descTaskText)).setText(personEntity.getEmail());
        ((TextView)view.findViewById(R.id.costTask)).setText(String.valueOf(personEntity.getId()));

        return view;
    }
}
