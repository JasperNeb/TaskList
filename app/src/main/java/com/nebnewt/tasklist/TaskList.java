package com.nebnewt.tasklist;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Neb on 18.11.2016.
 */

public abstract class TaskList {
    public static ArrayList<Task> todo=new ArrayList<>();

    //FIXME sth with exceptions ?
    static public void savetasklist(Context ctx, String filename)
    {
        FileOutputStream fos;
        try{
            fos= ctx.openFileOutput(filename, ctx.MODE_PRIVATE);
            try {
                fos.write("[".getBytes());
            } catch (IOException e) {
                return;
            }
            for(int i=0 ; i<todo.size() ; i++)
            {
                JSONObject obj = new JSONObject();
                try{
                    obj.put("name", todo.get(i).getName());
                    obj.put("description", todo.get(i).getName());

                }catch(JSONException e)
                {
                    return;
                }

                try{
                    fos.write(obj.toString().getBytes());
                    if (todo.size()-i>1) fos.write(",".getBytes());
                }catch(IOException e)
                {
                    return;
                }

            }

        try{
            fos.write("]".getBytes());
            fos.close();
        }
        catch(IOException e)
        {
            return;
        }
        }catch(FileNotFoundException e)
        {
            return;
        }
    }

    static public void loadTaskList( Context myContext, String filename) {
        FileInputStream fis;

        ArrayList <String> temp=new ArrayList<>();
        try{
            fis=myContext.openFileInput(filename);
        }catch(FileNotFoundException e){
            return;
        }
        JSONArray jsonArray = new JSONArray();

        try {
            InputStreamReader inputStream = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputStream);
            StringBuilder builder = new StringBuilder();
            {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
            inputStream.close();
            jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();

        } catch (IOException | JSONException e) {
            Log.e("error", e.getMessage());
        }
        try{
            todo.clear();
            for(int i=0 ; i<jsonArray.length() ; i++)
            {
                JSONObject tempObject = jsonArray.getJSONObject(i);
                String name=tempObject.getString("name");
                String description=tempObject.getString("description");
                Task t=new Task(name, description, 1);
                todo.add(t);
            }
        } catch(JSONException e){return;}
        return;
    }
}
