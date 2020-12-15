package com.example.med_pharm;

import java.util.ArrayList;
import java.util.List;

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
import android.provider.ContactsContract.Profile;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class profile extends Activity 
{
	String rid=null;
	
	TextView tname=null,tphone=null,taddress=null,temail=null;
	String name=null,address=null,phone=null,email=null;

	
	String url="http://10.0.2.2/Med_Pharm/profile.php";
	private static final String TAG_table = "retailer";
	private static final String TAG_rid = "r_id";
	private static final String TAG_name = "name";
	private static final String TAG_email = "email";
	private static final String TAG_phone = "phone";
	private static final String TAG_address = "address";

	
	
	
	JSONParser jsonp=new JSONParser();
	ProgressDialog pDialog=null;
	SharedPreferences ses=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
		rid=ses.getString("rid",null);
		
		tname=(TextView)findViewById(R.id.edname);
		tphone=(TextView)findViewById(R.id.edphone);
		temail=(TextView)findViewById(R.id.edemail);
		taddress=(TextView)findViewById(R.id.edaddress);
		
		//temail.setText(email);
	     new Select_profile().execute();
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
				Intent i=new Intent(profile.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(profile.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(profile.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(profile.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(profile.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
				
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(profile.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 

	class Select_profile extends AsyncTask<String, String, String>
	{
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(profile.this);
			pDialog.setMessage("Please wait ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		@Override
		protected String doInBackground(String... arg0) 
		{
			
				            
			
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("pid", rid));
				
				// getting product details by making HTTP request
				JSONObject json = jsonp.makeHttpRequest(url, "GET", params);

				try 
				{
					
					JSONArray array = json.getJSONArray(TAG_table);
					
					
					for(int j=0;j<array.length();j++)
					{
						JSONObject c=array.getJSONObject(j);
						name=c.getString(TAG_name);
						address=c.getString(TAG_address);
						phone=c.getString(TAG_phone);
						email=c.getString(TAG_email);
						
						
					}
				}
				catch(Exception e)
				{
					
					Log.e("Ex is :-",e+"");
				}
			 
		

			return null;
			
		}
		protected void onPostExecute(String file_url)
		{
			// dismiss the dialog once done
			pDialog.dismiss();
			tname.setText(name);
			taddress.setText(address);
			tphone.setText(phone);
			temail.setText(email);
			
		}
	}

}
