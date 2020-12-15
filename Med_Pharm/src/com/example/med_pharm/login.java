package com.example.med_pharm;

import org.json.JSONArray;
import org.json.JSONObject;

import my.JsonParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity implements OnClickListener
{
	TextView tv,tv_for;
	EditText ename,epass;
	int flag=0;
	String url="http://10.0.2.2/Med_Pharm/retailer_login.php";
	ProgressDialog pDialog=null;
	Button btn;
	String dname=null,dpass=null,uname=null,upass=null,rid=null;
	JsonParser jsonp=new JsonParser();
	private static final String TAG_table= "retailer";
	private static final String TAG_Uname="email";
	private static final String TAG_password="password";
	private static final  String tag_rid= "r_id";
	JSONArray array=null;
	SharedPreferences ses=null;
	public static String s_name="ss";
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		tv=(TextView)findViewById(R.id.tvloginnew);
		tv.setOnClickListener(this);
		
		tv_for=(TextView)findViewById(R.id.tvloginfor);
		tv_for.setOnClickListener(this);
		
		
		ename=(EditText)findViewById(R.id.edloginuname);
		epass=(EditText)findViewById(R.id.edloginpass);
		btn=(Button)findViewById(R.id.btnlogin);
		btn.setOnClickListener(this);
		ses=getSharedPreferences(s_name, MODE_PRIVATE);
	}
	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		if(tv==arg0)
		{
		Intent i=new Intent(login.this,new_user.class);
		startActivity(i);
		}
		
		if(tv_for==arg0)
		{
		Intent i=new Intent(login.this,forgottpass.class);
		startActivity(i);
		}
		
		if(btn==arg0)
		{
			uname=ename.getText().toString();
			upass=epass.getText().toString();
			if(uname.equals(""))
			{
				ename.setError("Pls Enter User Name");
			}
			else if(upass.equals(""))
			{
				epass.setError("Pls Enter Password");
			}
			else
			{
				new Select_Rec().execute();
			}
		}
   }
 class Select_Rec extends AsyncTask<String, String, String>
{
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(login.this);
			pDialog.setMessage("Please wait  ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

	@Override
	protected String doInBackground(String... arg0)
	{
		// TODO Auto-generated method stub
		JSONObject obj=jsonp.getJSONFromUrl(url);
		
		try
		{
			array=obj.getJSONArray(TAG_table);
			for(int i=0;i<array.length();i++)
			{
				JSONObject c= array.getJSONObject(i);
				rid=c.getString(tag_rid);
				dname=c.getString(TAG_Uname);
				dpass=c.getString(TAG_password);
				
				if((uname.equals(dname)) && (upass.equals(dpass)))
				{
					flag=1;
					break;
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
		if(flag==1)
		{
			
			Toast.makeText(login.this, "Successfully Login", Toast.LENGTH_LONG).show();
			SharedPreferences.Editor edit=ses.edit();
			
			edit.putString("rid",rid);
			edit.commit();
			edit.clear();
			Intent i=new Intent(login.this,after_login_main_menu.class);
			startActivity(i);
			ename.setText("");
			epass.setText("");
		}
		else
		{
			epass.setText("");
			ename.setText("");
			Toast.makeText(login.this, "Invalid", Toast.LENGTH_LONG).show();
			
		}
	}
}
}