package com.liron.tasklistnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.opengl.Visibility;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements View.OnClickListener
{

    private List<Task> items;
    private int itemLayout;
    private Activity activity;
    TasksProvider provider;

    public MyRecyclerAdapter(Activity activity, int itemLayout)
    {
        this.activity = activity;
        provider = new TasksProvider(activity);

        this.items = new ArrayList<Task>();

        Cursor cursor = provider.Qall();
        while (cursor.moveToNext())
        {
            this.items.add(new Task(cursor));
        }

        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        final Task item = items.get(position);
        holder.itemView.setTag(item);
        holder.text.setText(item.getDesc());
        holder.btnDone.setChecked(item.isDone());
        holder.btnDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Task task = (Task) holder.itemView.getTag();
                task.setIsDone(isChecked);
                provider.update(task);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public void onClick(View view)
    {
        Task task = (Task) view.getTag();
        Intent i = new Intent(activity, CreateTaskActivity.class);
        i.putExtra("task", task);
        activity.startActivityForResult(i, 177);
    }

    public void add(Task item)
    {
        provider.insert(item);
        int position = items.indexOf(item);
        if (-1 == position)
        {
            items.add(item);
            notifyItemInserted(items.size());
        }
        else
        {
            items.remove(position);
            items.add(position, item);
            notifyItemChanged(position);
        }
    }

    public void remove(Task item)
    {
        int position = items.indexOf(item);
        if (-1 == position)
        {
            return;
        }

        items.remove(position);
        provider.delete(item);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text;
        public CheckBox btnDone;

        public ViewHolder(View itemView)
        {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView);
            btnDone = (CheckBox) itemView.findViewById(R.id.btnDone);
        }
    }
}