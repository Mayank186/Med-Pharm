package com.example.med_pharm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity 
{
	long ms=0,s_time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Thread my=new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			while(ms<s_time)
        			{
        				ms=ms+100;
        				Thread.sleep(100);
        			}
        		}
        		catch(Exception e)
        		{
        			
        		}
        		finally
        		{
        			Intent i=new Intent(MainActivity.this,login.class);
        			startActivity(i);
        		}
        	}
        };
        my.start();
    }


    
}

