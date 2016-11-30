package com.nebnewt.tasklist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowTask extends Activity {

    String name;
    Task task;
    Button deleteButton, backButton;
    TextView nameText, descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        name=getIntent().getExtras().getString("name");

        task =findTask();
        deleteButton =(Button)findViewById(R.id.del);
        backButton =(Button)findViewById(R.id.back);
        nameText =(TextView)findViewById(R.id.name);
        descriptionText =(TextView)findViewById(R.id.description);
        nameText.setText(task.getName());
        descriptionText.setText(task.getDescription());
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskList.todo.remove(task);
                finish();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private Task findTask()
    {
        for(int i=0 ; i<TaskList.todo.size() ; i++)
        {
            if(TaskList.todo.get(i).getName().equals(name))
            {

                return TaskList.todo.get(i);
            }
        }
        return null;
    }
}
