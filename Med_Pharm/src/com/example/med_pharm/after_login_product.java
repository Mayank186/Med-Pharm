package com.example.med_pharm;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;



import my.JsonParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class after_login_product extends Activity implements OnItemClickListener,OnClickListener
{
	ListView lv;
	ImageButton ib;
	JsonParser jsonp=new JsonParser();
	JSONArray array;
	ProgressDialog pDialog=null;
	String url="http://10.0.2.2/Med_Pharm/view_product.php";
	private static final String TAG_table= "product_detail";
	private static final String TAG_pname="pname";
	private static final  String tag_pid= "pid";
	ArrayList<HashMap<String, String>> plist=null;
	ListAdapter adapter=null;
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.after_login_product);
	     
	     lv=(ListView)findViewById(R.id.listView1);
	     lv.setOnItemClickListener(this);
	     
	     ib=(ImageButton)findViewById(R.id.imageButton1);
	     ib.setOnClickListener(this);
	        
	     new Select_rec().execute();
	     
	     EditText myFilter = (EditText) findViewById(R.id.editText1);
   	  		
	     myFilter.addTextChangedListener(new TextWatcher() 
	     {

	    	 public void afterTextChanged(Editable s) 
	    	 {
	    	 }

	    	 public void beforeTextChanged(CharSequence s, int start, int count, int after) 
	    	 {
	    	 }

	    	 public void onTextChanged(CharSequence s, int start, int before, int count) 
	    	 {
   	 
	    		 ((Filterable) adapter).getFilter().filter(s.toString());
	    	 }
   	  	});
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
				Intent i=new Intent(after_login_product.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(after_login_product.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(after_login_product.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(after_login_product.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(after_login_product.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
				Intent i=new Intent(after_login_product.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }

	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(after_login_product.this);
				pDialog.setMessage("Please wait  ....");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			plist=  new ArrayList<HashMap<String, String>>();
			JSONObject obj=jsonp.getJSONFromUrl(url);
			
			try
			{
				array=obj.getJSONArray(TAG_table);
				for(int i=0;i<array.length();i++)
				{
					JSONObject c= array.getJSONObject(i);
					String pid=c.getString(tag_pid);
					String pname=c.getString(TAG_pname);
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(TAG_pname, pname);
		            map.put(tag_pid, pid);
		                
		            plist.add(map);
				}
			}
			
			catch(Exception e)
			{
				Log.e("Ex is:-",e+"");
			}
			return null;
		}
		protected void onPostExecute(String file_url)
		{
			// dismiss the dialog once done
			pDialog.dismiss();
			adapter = new SimpleAdapter(after_login_product.this,plist,R.layout.row_view_product,new String[] {TAG_pname,tag_pid}, new int[] 
			{
	            R.id.tvpname,R.id.tvpid});
				Log.e("data is-",adapter.toString());
				lv.setAdapter(adapter);	 
			}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		String s=((TextView)arg1.findViewById(R.id.tvpid)).getText().toString();
		Log.e("s", s);
//		Toast.makeText(after_login_product.this, s, Toast.LENGTH_LONG).show();
		SharedPreferences ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
		SharedPreferences.Editor ob=ses.edit();
		ob.putString("pid", s);
		ob.commit();
		
		
		Intent i=new Intent(after_login_product.this,sub_product.class);
		startActivity(i);
		
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		
		Intent i=new Intent(after_login_product.this,order.class);
		startActivity(i);

		
	}
	
	
	
}
