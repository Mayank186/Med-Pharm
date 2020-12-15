package com.example.med_pharm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class view_order extends Activity
{
	String rid=null;
	ListView lv;
	TextView tvname,tvaddress,tvphone,tvtotal;
	String sname,saddress,sphone,rate,pname,qty,status;
	long t_amout=0;
	JSONParser jsonp=new JSONParser();
	JSONArray array;
	SharedPreferences ses=null;
	ProgressDialog pDialog=null;
	int f_result=1;
	String url="http://10.0.2.2/Med_Pharm/view_order.php";
	
	private static final String TAG_table= "order_detail";
	private static final String TAG_name="name";
	private static final String TAG_address="address";
	private static final String TAG_phone="phone";
	private static final  String tag_rid= "rid";
	private static final String TAG_oid="o_id";
	private static final String TAG_pname="pname";
	private static final String TAG_rate="rate";
	private static final String TAG_qty="qty";
	private static final String TAG_status="status";
	//private static final String TAG_spid="spid";
	
	ArrayList<HashMap<String, String>> plist=null;
	
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.view_order);
	     
	     ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
		 rid=ses.getString("rid",null);
			
	     
	     lv=(ListView)findViewById(R.id.listView1);
	     //lv.setOnItemClickListener(this);
	     
	     tvname=(TextView)findViewById(R.id.textView2);
	     tvaddress=(TextView)findViewById(R.id.textView4);
	     tvphone=(TextView)findViewById(R.id.textView6);
	     tvtotal=(TextView)findViewById(R.id.textView10);
	     
	     plist=  new ArrayList<HashMap<String, String>>();
	     
	        
	     new Select_rec().execute();
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
				Intent i=new Intent(view_order.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(view_order.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(view_order.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(view_order.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(view_order.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{	
				
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(view_order.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 

	
	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(view_order.this);
				pDialog.setMessage("Please wait  ....");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("rid", rid));
			
			JSONObject json=jsonp.makeHttpRequest(url, "GET", params);
			//JSONObject json = jsonp.makeHttpRequest(url, "GET", params);
			
			
			plist=  new ArrayList<HashMap<String, String>>();
			//JSONObject obj=jsonp.getJSONFromUrl(url);
			
			try
			{
				int ans=json.getInt("success");
				Log.e("Ans is:-",ans+"");
				if(ans==0)
				{
					f_result=0;
				}
				else
				{
					array=json.getJSONArray(TAG_table);
					
					for(int i=0;i<array.length();i++)
					{
						JSONObject c= array.getJSONObject(i);
						
						sname=c.getString(TAG_name);
						saddress=c.getString(TAG_address);
						sphone=c.getString(TAG_phone);
						
						
						rate=c.getString(TAG_rate);
						pname=c.getString(TAG_pname);
						qty=c.getString(TAG_qty);
						status=c.getString(TAG_status);
						t_amout=t_amout+((Long.parseLong(rate))*(Long.parseLong(qty)));
						
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_pname, pname);
			            map.put(TAG_rate, rate);
			            map.put(TAG_qty, qty);
			            map.put(TAG_status, status);
			                
			            plist.add(map);
				
				}
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
			
			if(f_result==0)
			{
				Toast.makeText(view_order.this,"No Order On This ID", Toast.LENGTH_LONG).show();
				Intent i=new Intent(view_order.this,after_login_product.class);
				startActivity(i);
			}
			else
			{
				tvname.setText(sname);
				tvaddress.setText(saddress);
				tvphone.setText(sphone);
				tvtotal.setText(t_amout+"");
				Log.e("T_amout is:",t_amout+"");
				
				ListAdapter adapter = new SimpleAdapter(view_order.this,plist,R.layout.row_view_order,new String[] {TAG_pname,TAG_rate,TAG_qty,TAG_status}, new int[] 
				{
		            R.id.tvpname,R.id.tvrate,R.id.tvqty,R.id.tvstatus});
					Log.e("data is-",adapter.toString());
					lv.setAdapter(adapter);	 
				}
			
			}
			
			
	}

	
}
