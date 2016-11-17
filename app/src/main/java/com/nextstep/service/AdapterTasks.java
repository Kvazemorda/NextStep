package com.nextstep.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nextstep.R;
import com.nextstep.entity.TaskEntity;

import java.util.List;


public class AdapterTasks extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<TaskEntity> tasks;

    public AdapterTasks(Context ctx, List<TaskEntity> tasks) {
        this.ctx = ctx;
        this.tasks = tasks;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
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
        TaskEntity task = tasks.get(position);

        ((TextView)view.findViewById(R.id.taskText)).setText(task.getTitle());
        ((TextView)view.findViewById(R.id.descTaskText)).setText(task.getDescription());
        ((TextView)view.findViewById(R.id.costTask)).setText(String.valueOf(task.getPlanCashFlow()));

        return view;
    }
}
