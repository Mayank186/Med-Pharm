package com.example.med_pharm;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class setting extends Activity implements OnItemClickListener 
{
	
	GridView gv;
	TextView tv;
	String rid=null;
	SharedPreferences ses=null;
	
	String str[]=new String[]{"Change Password","Contact Us"};
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.setting);
	        
	        ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
			rid=ses.getString("rid",null);
			
	        
	        gv=(GridView)findViewById(R.id.gridView1);
	        gv.setAdapter(new image(this, str));
	        
	        gv.setOnItemClickListener(this);
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
				Intent i=new Intent(setting.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(setting.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(setting.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(setting.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(setting.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
				
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(setting.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }

	
	class image extends BaseAdapter
	 {
		 String detail[];
		 Context context;
		 
		 public image(Context context,String detail[])
		 {
			 this.context=context;
			 this.detail=detail;
		 }
		 
		@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return detail.length;
		}

		@Override
		public Object getItem(int arg0) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) 
		{
			// TODO Auto-generated method stub
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
			
			View grid;
			
			if( arg1 == null)
			{
				grid=new View(context);
				grid=inflater.inflate(R.layout.row, null);
				
				ImageView iv=(ImageView)grid.findViewById(R.id.imageView1);
				tv=(TextView)grid.findViewById(R.id.textView1);
				tv.setText(detail[arg0]);
				String s=detail[arg0];
				
				if (s.equalsIgnoreCase("Change Password"))
				{
					iv.setImageResource(R.drawable.changepassword);
				}
					
								
				if (s.equalsIgnoreCase("Contact Us"))
				{
					iv.setImageResource(R.drawable.contact_us);
				}
					
			}
			
			else 
			{
				grid = (View) arg1;
			}
			
			return grid;
		}
		 
	 }
	 
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		String str = ((TextView) arg1.findViewById(R.id.textView1)).getText().toString();
		Toast.makeText(setting.this, str, Toast.LENGTH_LONG).show();
		
		if(str.equals("Change Password"))
		{
			Intent i=new Intent(setting.this,change_password.class);
			startActivity(i);
		}
		
		
		if(str.equals("Contact Us"))
		{
			Intent i=new Intent(setting.this,contact_us.class);
			startActivity(i);
		}
	}
}
