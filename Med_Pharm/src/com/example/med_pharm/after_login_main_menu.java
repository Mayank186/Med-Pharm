package com.example.med_pharm;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class after_login_main_menu extends Activity implements OnItemClickListener , OnClickListener
{
	
	GridView gv;
	TextView tv;
	ImageButton ib;
	SharedPreferences ses=null;
	String r_id=null;
	String str[]=new String[]{"Profile","Product","Order","Setting","Feedback","Logout"};
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.after_login_main_menu);
	        ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
	        r_id=ses.getString("rid", null);
	        Log.e("rid is:",r_id);
	        gv=(GridView)findViewById(R.id.gridView1);
	        gv.setAdapter(new image(this, str));
	        
	        ib=(ImageButton)findViewById(R.id.imageButton1);
	        ib.setOnClickListener(this);
	        
	        gv.setOnItemClickListener(this);
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
				
				if (s.equalsIgnoreCase("Profile"))
				{
					iv.setImageResource(R.drawable.user);
				}
					
								
				if (s.equalsIgnoreCase("Product"))
				{
					iv.setImageResource(R.drawable.book);
				}
				
				if (s.equalsIgnoreCase("Order"))
				{
					iv.setImageResource(R.drawable.cartaddicon);
				}
				
				if (s.equalsIgnoreCase("Setting"))
				{
					iv.setImageResource(R.drawable.inquiry);
				}
				
				if (s.equalsIgnoreCase("Feedback"))
				{
					iv.setImageResource(R.drawable.feedback);
				}
				
				if (s.equalsIgnoreCase("Logout"))
				{
					iv.setImageResource(R.drawable.login_person);
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
		Toast.makeText(after_login_main_menu.this, str, Toast.LENGTH_LONG).show();
		
		if(str.equals("Profile"))
		{
			Intent i=new Intent(after_login_main_menu.this,profile.class);
			startActivity(i);
		}
		
		
		if(str.equals("Product"))
		{
			Intent i=new Intent(after_login_main_menu.this,after_login_product.class);
			startActivity(i);
		}
		
		if(str.equals("Order"))
		{
			Intent i=new Intent(after_login_main_menu.this,view_order.class);
			startActivity(i);
		}
		
		if(str.equals("Setting"))
		{
			Intent i=new Intent(after_login_main_menu.this,setting.class);
			startActivity(i);
		}
		
		if(str.equals("Feedback"))
		{
			Intent i=new Intent(after_login_main_menu.this,feedback.class);
			startActivity(i);
		}
		
		if(str.equals("Logout"))
		{
			SharedPreferences.Editor edit=ses.edit();
			
			edit.putString("rid",r_id);
			edit.commit();
			edit.clear();
			
			
			Intent i=new Intent(after_login_main_menu.this,login.class);
			startActivity(i);
		}
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		Intent i=new Intent(after_login_main_menu.this,order.class);
		startActivity(i);
	}
}
