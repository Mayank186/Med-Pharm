package com.example.med_pharm;

import android.app.Activity;
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.med_pharm.new_user.Insert_Rec;



import my.JsonParser;
import android.R.string;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint.Join;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class forgottpass extends Activity implements OnClickListener 
{
	private static final String TAG_table= "retailer";
	private static final String Tag_password="password";
	private static final String tag_phone="phone";
	
	JSONParser jsonp=new JSONParser();
	
	String password=null,phone=null,email=null;
	Button btn;
	EditText ed;
	ProgressDialog pDialog=null;
	String url="http://10.0.2.2/Med_Pharm/forgottpass.php";//eid=dimpy@yahoo.com"
	JSONArray array=null;
	protected void onCreate(Bundle savedIstanceState) 
	{
		super.onCreate(savedIstanceState);
		setContentView(R.layout.forgottpass);
		ed=(EditText)findViewById(R.id.fpass);
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		boolean ans=email.contains("@") && email.contains(".");
		
		email=ed.getText().toString();
		
		if(email.equals(""))
		{
			ed.setError("Pls Enter Email");
		}
		
		else if(ans==false)
		{
			ed.setError("Pls Enter Valid Email");
		}
		else
		{
			new Select_rec().execute();
		}
		
		Intent i=new Intent(forgottpass.this,login.class);
		startActivity(i);
		
		 
	}
	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(forgottpass.this);
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
			params.add(new BasicNameValuePair("eid", email));
			JSONObject obj=jsonp.makeHttpRequest(url, "GET", params);
			
			try
			{
				array=obj.getJSONArray(TAG_table);
				for(int i=0;i<array.length();i++)
				{
					JSONObject c= array.getJSONObject(i);
			
					phone=c.getString(tag_phone);
					password=c.getString(Tag_password);
					Log.e("Phone",phone);
					Log.e("Pass",password);
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
			try {
				SmsManager smsManager = SmsManager.getDefault();
				
				smsManager.sendTextMessage(phone, null, "Your Password is:-"+password, null, null);
				
				Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
			  } catch (Exception e) {
				Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
				e.printStackTrace();
			  }


			
		}
	}


}
