package com.example.med_pharm;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class new_user extends Activity implements android.view.View.OnClickListener
{
	String url="http://10.0.2.2/Med_Pharm/new_user.php";
	int cnt=0;
	ProgressDialog pDialog=null;
	EditText edname,edemail,edpass,edphone,edaddress;
	
	String name=null,email=null,pass=null,phone=null,address=null;
	Button btn;
	JSONParser Jsonp=new JSONParser();
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.new_user);
	        
	        btn=(Button)findViewById(R.id.button1);
	        btn.setOnClickListener(this);
	        
	        edname=(EditText)findViewById(R.id.nuname);
	        edemail=(EditText)findViewById(R.id.nuemail);
	        edpass=(EditText)findViewById(R.id.nupass);
	        edphone=(EditText)findViewById(R.id.nuphone);
	        edaddress=(EditText)findViewById(R.id.nuaddress);
	    }



		@Override
		public void onClick(View arg0) 
		{
			// TODO Auto-generated method stub
			if(btn==arg0)
			{
				name=edname.getText().toString();
				email=edemail.getText().toString();
				pass=edpass.getText().toString();
				phone=edphone.getText().toString();
				address=edaddress.getText().toString();
				
				boolean ans=email.contains("@") && email.contains(".");
				
				if(name.equals(""))
				{
					edname.setError("Pls Enter Name");
				}
				else if(email.equals(""))
				{
					edemail.setError("Pls Enter Email");
				}
				else if(pass.equals(""))
				{
					edpass.setError("Pls Enter Valid Password ");
				}
				else if(phone.equals(""))
				{
					edphone.setError("Pls Enter Phone Number");
				}
				else if(address.equals(""))
				{
					edaddress.setError("Pls Enter Address");
				}
				else if(pass.length()<8)
				{
					edpass.setError("Enter Strong Password");
				}
				else if(phone.length()!=10)
				{
					edphone.setError("Enter Valid Phone Number");
				}
				else if(ans==false)
				{
					edemail.setError("Pls Enter Valid Email");
				}
				else
				{
					new Insert_Rec().execute();
				}
				
		
				
		
			}
			
		}
	
	class Insert_Rec extends AsyncTask<String, String, String>
	{
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(new_user.this);
			pDialog.setMessage("Please wait  ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}


		@Override
		protected String doInBackground(String... arg0) 
		{
			// TODO Auto-generated method stub

			List<NameValuePair> params=new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("pass", pass));
			params.add(new BasicNameValuePair("phone", phone));
			params.add(new BasicNameValuePair("address", address));
			
			JSONObject obj = Jsonp.makeHttpRequest(url,"POST", params);
			
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
			edname.setText("");
			edemail.setText("");
			edpass.setText("");
			edphone.setText("");
			edaddress.setText("");
			
			
			if(cnt==1)
			{
				Toast.makeText(new_user.this, "Successfully inserted", Toast.LENGTH_LONG).show();
				Intent i=new Intent(new_user.this,login.class);
				startActivity(i);
			}
				
				
			else
			{
				Toast.makeText(new_user.this, "Error", Toast.LENGTH_LONG).show();
			}
				
		
		}

	}
}
