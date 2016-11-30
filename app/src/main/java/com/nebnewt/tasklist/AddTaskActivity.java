package com.nebnewt.tasklist;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class AddTaskActivity extends Activity {

    ArrayList <RadioButton> rb;
    Button addButton;
    RadioGroup ll;
    int importance=2;
    EditText titleText, descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ll=(RadioGroup) findViewById(R.id.importance);
        addButton =(Button)findViewById(R.id.add);
        titleText =(EditText)findViewById(R.id.title);
        descriptionText =(EditText)findViewById(R.id.description);
        rb=new ArrayList<>();
        for(int i=0 ; i<5 ; i++)
        {
            RadioButton b=new RadioButton(this);
            b.setId(i);
            rb.add(b);
            b.setText(Integer.toString(i));
            ll.addView(b);
        }
        rb.get(2).setChecked(true);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task;
                for(int i=0 ; i<5 ; i++)
                {
                    RadioButton r=rb.get(i);
                    if(r.isChecked())
                    {
                        importance=i;
                        break;
                    }

                }
                task=new Task( titleText.getText().toString(), descriptionText.getText().toString(), importance);
                TaskList.todo.add(task);
                TaskList.savetasklist(AddTaskActivity.this, "todo");
                finish();
            }
        });
    }


}
