package com.nebnewt.tasklist;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    LinearLayout layout;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout =(LinearLayout)findViewById(R.id.button_layout);
        TaskList.loadTaskList(this, "todo");

        addButton =(Button)findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onStart()
    {
        super.onStart();
        layout.removeAllViewsInLayout();
        for(int i=0 ; i<TaskList.todo.size();i++)
        {
            Button button = new Button(this);
            button.setText(TaskList.todo.get(i).getName());
            button.setId(i);
            layout.addView(button);
            final int j=i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(), ShowTask.class );
                    Log.i("SFSFSFS", TaskList.todo.get(j).getName());
                    i.putExtra("name", TaskList.todo.get(j).getName());
                    startActivity(i);
                }
            });
        }
        Intent intent = new Intent(this,MyAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        int[] ids = {R.xml.appwidget_info};
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(intent);
    }
}
