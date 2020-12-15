package com.example.med_pharm;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class order extends Activity implements OnItemClickListener,OnClickListener
{
	private SQLiteDatabase db;
	ListView lv;
	AlertDialog.Builder alert;
	String str=null;
	Button btn;
	String rid=null;
	StringBuffer sb_spid=null,sb_rid=null,sb_qty=null,sb_rate=null,sb_pname=null;
	SharedPreferences ses=null;
	ArrayList<HashMap<String, String>> plist=null;
	protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
        
        ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
	    rid=ses.getString("rid",null);
	     
        
        plist=  new ArrayList<HashMap<String, String>>();
        
        db = openOrCreateDatabase("MEDDB", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        
        lv=(ListView)findViewById(R.id.listView1);
        lv.setOnItemClickListener(this);
        
        Cursor c=db.rawQuery("select *from ADD_TO_CART", null);
        int ans=c.getCount();
        if(ans==0)
        {
        	Toast.makeText(order.this, "Your Cart is Empty", Toast.LENGTH_LONG).show();
        	Intent i=new Intent(order.this,after_login_product.class);
        	startActivity(i);
        }
        else
        {
        	c.moveToFirst();
            sb_pname=new StringBuffer();
            sb_qty=new StringBuffer();
            sb_rate=new StringBuffer();
            sb_rid=new StringBuffer();
            sb_spid=new StringBuffer();
            
            
            do
            {
            	int c_id=c.getInt(c.getColumnIndex("acid"));
            	
            	String pname=c.getString(c.getColumnIndex("PNAME"));
            	sb_pname.append(pname+",");
            	String rate=c.getString(c.getColumnIndex("RATE"));
            	sb_rate.append(rate+",");
            	String qty=c.getString(c.getColumnIndex("QTY"));
            	sb_qty.append(qty+",");
            	String spid=c.getString(c.getColumnIndex("SPID"));
            	sb_spid.append(spid+",");
            	String rid=c.getString(c.getColumnIndex("RID"));
            	sb_rid.append(rid+",");
    			HashMap<String, String> map = new HashMap<String, String>();
    		    map.put("PNAME", pname);
    			map.put("RATE", rate);
    			map.put("acid", c_id+"");  
                plist.add(map);
                
            }while(c.moveToNext());
            
            c.close();
            ListAdapter adapter = new SimpleAdapter(order.this,plist,R.layout.row_order,new String[] {"PNAME","RATE","acid"}, new int[] 		
    				{
    					R.id.textView1,R.id.textView2,R.id.textView3});
    					Log.e("data is-",adapter.toString());
    					lv.setAdapter(adapter);	

        }
     }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        getMenuInflater().inflate(R.menu.menu_data, menu);
        return true;
    }
	 public boolean onOptionsItemSelected(MenuItem item)
	 {
		 String str=item.getTitle().toString();
		 if(str.equals("Profile"))
			{
				Intent i=new Intent(order.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(order.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(order.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(order.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(order.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{	
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",sb_rid+"");
				edit.commit();
				edit.clear();
				
				
				Intent i=new Intent(order.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		
		final String sid=((TextView)arg1.findViewById(R.id.textView3)).getText().toString();
		
		alert=new AlertDialog.Builder(order.this);
		alert.setTitle("Delete and Cancle");
		alert.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				// TODO Auto-generated method stub
			Intent i=new Intent(order.this,after_login_main_menu.class);	
			
			startActivity(i);
			}
		});
		alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) 
			{
				// TODO Auto-generated method stub
				int ans=db.delete("ADD_TO_CART", "acid"+"="+Integer.parseInt(sid), null);
				if(ans>0)
				{
					Toast.makeText(order.this, "Record is Delete", Toast.LENGTH_LONG).show();
					Intent i=new Intent(order.this,after_login_main_menu.class);	
					startActivity(i);
				}
				else
				{
					Toast.makeText(order.this, "Record is Not Delete", Toast.LENGTH_LONG).show();	
				}
			}
		});
		alert.show();


		
	}

	@Override
	public void onClick(View arg0) 
	{
		Intent i=new Intent(order.this,make_order.class);
		i.putExtra("pname", sb_pname+"");
		i.putExtra("rate", sb_rate+"");
		i.putExtra("qty", sb_qty+"");
		i.putExtra("spid", sb_spid+"");
		i.putExtra("rid", sb_rid+"");
		
		startActivity(i);
		
	}
}
