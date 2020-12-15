package com.example.med_pharm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.med_pharm.new_user.Insert_Rec;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class make_order extends Activity implements OnClickListener  
{

	SQLiteDatabase db;
	Bundle b;
	String rid=null;
	SharedPreferences ses=null;
	String pname=null,rate=null,qty=null,spid=null;
	String name=null,address=null,phone=null;
	Button btn;
	ProgressDialog pDialog=null;
	JSONArray array=null;
	int cnt=0;
	JSONParser jsonp=new JSONParser();
	
	String url="http://10.0.2.2/Med_Pharm/add_order.php";
	private static final String Tag_name="name";
	private static final String Tag_address="address";
	private static final String Tag_phone="phone";
	
	
	EditText odname1=null;
	EditText odaddress1=null;
	EditText odphone1=null;
	
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.make_order);
	     
	     ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
	     rid=ses.getString("rid",null);
	     
	     
	     db = openOrCreateDatabase("MEDDB", SQLiteDatabase.CREATE_IF_NECESSARY,null);
	     
	     btn=(Button)findViewById(R.id.odbtn);
	     b=getIntent().getExtras();
	     pname=b.getString("pname");
	     rate=b.getString("rate");
	     qty=b.getString("qty");
	     spid=b.getString("spid");
	     rid=b.getString("rid");
	     Log.e("pname:-",pname);

	     odname1=(EditText)findViewById(R.id.odname);
	     odaddress1=(EditText)findViewById(R.id.odaddress);
	     odphone1=(EditText)findViewById(R.id.odphone);
	     
	     
	     
	     
	     
	     btn.setOnClickListener(this);
	     
	     
	
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
				Intent i=new Intent(make_order.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(make_order.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(make_order.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(make_order.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(make_order.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{

				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(make_order.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }


	

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		name=odname1.getText().toString();
		address=odaddress1.getText().toString();
		phone=odphone1.getText().toString();
		
		if(name.equals(""))
		{
			odname1.setError("Pls Enter Name");
		}
		else if(phone.equals(""))
		{
			odphone1.setError("Pls Enter Phone Number");
		}
		else if(address.equals(""))
		{
			odaddress1.setError("Pls Enter Address");
		}
		else if(phone.length()!=10)
		{
			odphone1.setError("Enter Valid Phone Number");
		}
		else
		{
			new Insert_Rec().execute();
		}
		
		
		
	}
	class Insert_Rec extends AsyncTask<String, String, String>
	{
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(make_order.this);
			pDialog.setMessage("Please wait  ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		@Override
		protected String doInBackground(String... arg0) 
		{
			// TODO Auto-generated method stub

			String o_date=null;
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			o_date=df.format(dateobj).toString();
			//System.out.println(df.format(dateobj));
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("pname", pname));
			params.add(new BasicNameValuePair("rate", rate));
			params.add(new BasicNameValuePair("qty", qty));
			params.add(new BasicNameValuePair("spid", spid));
			
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("phone", phone));
			params.add(new BasicNameValuePair("address", address));
			params.add(new BasicNameValuePair("r_id", rid));
			params.add(new BasicNameValuePair("o_date",o_date));
	
			JSONObject obj = jsonp.makeHttpRequest(url,"POST", params);
			
			// check for success tag
			try
			{
				int ans = obj.getInt("success");
				
				if (ans == 1)
				{
					cnt=1;
				} 
				else 
				{
					cnt=0;
				}
			} 
			catch (Exception e) 
			{

			} 
			
			return null;
			
		}

		protected void onPostExecute(String file_url)
		{
			//here ename like .gettextname in onclick method
			// dismiss the dialog once done
			pDialog.dismiss();
			
			
			if(cnt==1)
			{
				Toast.makeText(make_order.this, "Successfully Submmited", Toast.LENGTH_LONG).show();
				long ans=db.delete("ADD_TO_CART", null, null);
				if(ans==0)
				{
					Toast.makeText(make_order.this, "Not Delete", Toast.LENGTH_LONG).show();
				}
				Intent i=new Intent(make_order.this,after_login_main_menu.class);
				startActivity(i);

				
			}
				
				
			else
			{
				Toast.makeText(make_order.this, "Successfully not Submmited", Toast.LENGTH_LONG).show();
				
				Intent i=new Intent(make_order.this,after_login_main_menu.class);
				startActivity(i);

			}
				
		
		}

	}

}
