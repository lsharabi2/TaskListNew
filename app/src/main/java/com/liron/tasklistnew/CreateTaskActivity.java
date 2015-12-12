package com.liron.tasklistnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTaskActivity extends AppCompatActivity
{
    Task _task;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        final EditText textDesc = (EditText) findViewById(R.id.textDesc);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);

        _task = (Task)getIntent().getSerializableExtra("task");
        if (_task != null)
        {
            btnCreate.setText("Edit");
            textDesc.setText(_task.getDesc());
        }
        else
        {
            _task = new Task();
        }

        btnCreate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(CreateTaskActivity.this, TaskListActivity.class);

                _task.setDesc(textDesc.getText().toString());

                i.putExtra("task", _task);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

}
