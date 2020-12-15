package com.example.med_pharm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.med_pharm.login.Select_Rec;


import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class change_password extends Activity implements OnClickListener 
{
	SharedPreferences ses=null;
	String rid=null;
	JSONParser jsonp=new JSONParser();
	String url="http://10.0.2.2/Med_Pharm/profile.php";
	ProgressDialog pDialog=null;
	
	private static final String TAG_table= "retailer";
	
	private static final String Tag_password="password";
	private static final String tag_rid="r_id";
	ArrayList<HashMap<String, String>> slist=null;
	JSONArray array=null;
	String password=null,op=null,cp=null,np=null;
	EditText oldpass=null,newpass=null,confirmpass=null;
	Button btn;
	
	String up_url="http://10.0.2.2/Med_Pharm/changepassword.php";
	int cnt=0;
	
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.change_password);
	     oldpass=(EditText)findViewById(R.id.oldpass);
	     newpass=(EditText)findViewById(R.id.newpass);
	     confirmpass=(EditText)findViewById(R.id.confpass);
	     ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
	     rid=ses.getString("rid",null);
	     new Select_rec().execute();
	     btn=(Button)findViewById(R.id.btuchange);
	     btn.setOnClickListener(this);
	 }
	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(change_password.this);
				pDialog.setMessage("Please wait  ....");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
		
			List<NameValuePair>params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pid",rid));
			JSONObject obj=jsonp.makeHttpRequest(url, "GET", params);
			
			try
			{
				array=obj.getJSONArray(TAG_table);
				for(int i=0;i<array.length();i++)
				{
					JSONObject c= array.getJSONObject(i);
					password=c.getString(Tag_password);
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
			
			
		}
	}


	@Override
	public void onClick(View arg0) 
	{
		np=newpass.getText().toString();
		cp=confirmpass.getText().toString();
		op=oldpass.getText().toString();
		if(btn==arg0)
		{
			if(np.equals(""))
			{
				newpass.setError("Pls Enter Valid Password ");
			}
			
			else if(np.length()<8)
			{
				newpass.setError("Enter Strong Password");
			}
			
			else if(cp.equals(""))
			{
				confirmpass.setError("Enter Strong Password ");
			}
			
			else if(op.equals(password))
			{
				
				
				if(np.equals(cp))
				{
					new Update_pass().execute();
				}
				
				
				
				else
				{

					confirmpass.setError("Confirm Password not match");
					Toast.makeText(change_password.this, "Confirm Password not match", Toast.LENGTH_LONG).show();
				}
			}
			
			
			
			
			
			else
			{
				oldpass.setError("Old Password not match");
				Toast.makeText(change_password.this, "Old Password not match", Toast.LENGTH_LONG).show();
			}
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
				Intent i=new Intent(change_password.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(change_password.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(change_password.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(change_password.this,change_password.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(change_password.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
				
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(change_password.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 
	 
	
	 class Update_pass extends AsyncTask<String, String, String>
	 {
		 protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(change_password.this);
				pDialog.setMessage("Please wait  ....");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

		@Override
		protected String doInBackground(String... arg0)
		{
			// TODO Auto-generated method stub
		
			List<NameValuePair>params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("np", np));
			params.add(new BasicNameValuePair("pid", rid));
			JSONObject obj=jsonp.makeHttpRequest(up_url, "POST", params);
			
			try
			{
				int ans=obj.getInt("success");
				if(ans==1)
				{
					cnt=1;
				}
				else
				{
					cnt=0;
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
			if(cnt==1)
			{
				newpass.setText("");
				oldpass.setText("");
				confirmpass.setText("");
				Toast.makeText(change_password.this, "Password is Updated", Toast.LENGTH_LONG).show();
				Intent i=new Intent(change_password.this,after_login_main_menu.class);
				startActivity(i);
				
			}
			else
			{
				newpass.setText("");
				oldpass.setText("");
				confirmpass.setText("");
				Toast.makeText(change_password.this, "Password is not Updated", Toast.LENGTH_LONG).show();
			}
			
		}
 
	 }

}
