package com.nebnewt.tasklist;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by Neb on 25.11.2016.
 */

public class MyAppWidgetProvider extends AppWidgetProvider{
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            ComponentName thisWidget =new ComponentName(context, MyAppWidgetProvider.class);

            int c;
            c = TaskList.todo.size()>5 ? 5:TaskList.todo.size();
            StringBuilder sb=new StringBuilder();
            for(int j=0 ; j<c ; j++)
            {
                sb.append(TaskList.todo.get(j).getName()+"\n");
            }
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.appwidget);
            remoteViews.setTextViewText(R.id.textview, "");
            remoteViews.setTextViewText(R.id.textview, sb.toString());

            Intent intent = new Intent(context, MyAppWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
     }

    }

}
