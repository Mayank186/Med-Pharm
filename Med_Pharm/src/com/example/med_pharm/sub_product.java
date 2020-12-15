package com.example.med_pharm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;



import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class sub_product extends Activity implements OnItemClickListener
{
	String pid=null,rid=null;
	AlertDialog.Builder alert;
	String str=null;
	SharedPreferences ses=null;
	ListView lv=null;
	JSONParser jsonp=new JSONParser();
	String url="http://10.0.2.2/Med_Pharm/sub_product.php";
	ProgressDialog pDialog=null;
	
	private static final String TAG_table= "sub_pro";
	

	private static final String TAG_name="name";
	private static final  String tag_rate= "rate";
	private static final  String tag_stock= "stock";
	private static final  String tag_spid= "sp_id";
	
	ArrayList<HashMap<String, String>> slist=null;
	JSONArray array=null;
	
	Bundle b;
	
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.sub_product);
	     lv=(ListView)findViewById(R.id.listView1);
	     lv.setOnItemClickListener(this);
	     ses=getSharedPreferences(login.s_name,MODE_PRIVATE);
	     pid=ses.getString("pid",null);
	     rid=ses.getString("rid",null);
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
				Intent i=new Intent(sub_product.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(sub_product.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(sub_product.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("setting"))
			{
				Intent i=new Intent(sub_product.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(sub_product.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
				
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(sub_product.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 

	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(sub_product.this);
				pDialog.setMessage("Please wait  ....");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
			slist=  new ArrayList<HashMap<String, String>>();
			List<NameValuePair>params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pid", pid));
			JSONObject obj=jsonp.makeHttpRequest(url, "GET", params);
			
			try
			{
				array=obj.getJSONArray(TAG_table);
				for(int i=0;i<array.length();i++)
				{
					JSONObject c= array.getJSONObject(i);
					
					String pname=c.getString(TAG_name);
					String rate=c.getString(tag_rate);
					String stock=c.getString(tag_stock);
					String id=c.getString(tag_spid);
					
					HashMap<String, String> map = new HashMap<String, String>();
				    map.put(TAG_name, pname);
					map.put(tag_rate, rate);
					
					map.put(tag_spid, id);  
					map.put(tag_stock, stock);
					slist.add(map);
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
			ListAdapter adapter = new SimpleAdapter(sub_product.this,slist,R.layout.row_view_service,new String[] {TAG_name,tag_rate,tag_spid,tag_stock}, new int[] 		
				{
					R.id.textView2,R.id.textView4,R.id.textView5,R.id.textView7});
					Log.e("data is-",adapter.toString());
					lv.setAdapter(adapter);	 
				}
		}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		String str=((TextView)arg1.findViewById(R.id.textView5)).getText().toString();
		
		
		
		SharedPreferences ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
		SharedPreferences.Editor ob=ses.edit();
		ob.putString("spid", str);
		ob.commit();

		alert = new AlertDialog.Builder(sub_product.this);
		
		alert.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				
				// TODO Auto-generated method stub
				Intent i=new Intent(sub_product.this,sub_product.class);
				startActivity(i);
			}
		});
			
			
		alert.setNeutralButton("More Info", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				Intent i=new Intent(sub_product.this,detail_sub_product.class);
				startActivity(i);
				
				// TODO Auto-generated method stub
				
			}
		});
		alert.show();
	}
	

		
		
		
		
	

}
