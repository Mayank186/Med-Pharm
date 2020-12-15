package com.example.med_pharm;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.med_pharm.login.Select_Rec;
import com.example.med_pharm.make_order.Insert_Rec;

import my.JsonParser;
import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class feedback extends Activity implements OnRatingBarChangeListener,android.view.View.OnClickListener
{
	Button btn;
	RelativeLayout r;
	EditText fdname,fddes;
	String fname=null,fdes=null,frate=null;
	String rid=null,rate=null;
	SharedPreferences ses=null;
	RatingBar rb;
	String url="http://10.0.2.2/Med_Pharm/feedback.php";
	int cnt=0;
	ProgressDialog pDialog=null;
	JSONParser JSONP=new JSONParser();	
	
	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        
        r=(RelativeLayout)findViewById(R.id.relativeLayout1);
        ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
        
        rid=ses.getString("rid", null);
        rb=(RatingBar)findViewById(R.id.ratingBar1);
        btn=(Button)findViewById(R.id.fbtn);
        fdname=(EditText)findViewById(R.id.fname);
        fddes=(EditText)findViewById(R.id.fdes);
        
        rb.setOnRatingBarChangeListener(this);
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
				Intent i=new Intent(feedback.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(feedback.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(feedback.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(feedback.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(feedback.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{

				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(feedback.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }

	
	@Override
	public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) 
	{
		// TODO Auto-generated method stub
		rate=arg1+"";
		Toast.makeText(feedback.this, rate, Toast.LENGTH_LONG).show();
	}
	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		
		
		if(btn==arg0)
		{
			fname=fdname.getText().toString();
			fdes=fddes.getText().toString();	
			
			if(fname.equals(""))
			{
				fdname.setError("Pls Enter Name");
			}
			else if(fdes.equals(""))
			{
				fddes.setError("Pls Enter Description");
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
			pDialog = new ProgressDialog(feedback.this);
			pDialog.setMessage("Please wait Insert Record ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
	@Override
	protected String doInBackground(String... arg0)
	{
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("fname", fname));
		params.add(new BasicNameValuePair("fdes", fdes));
		params.add(new BasicNameValuePair("frate", rate));
		params.add(new BasicNameValuePair("r_id", rid));
		JSONObject obj=JSONP.makeHttpRequest(url, "POST", params);
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
			
				Toast.makeText(getApplicationContext(), "Successfully Submited", Toast.LENGTH_LONG).show();
				fdname.setText("");
				fddes.setText("");
				Intent i=new Intent(feedback.this,after_login_main_menu.class);
				startActivity(i);
				
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Not  Submited", Toast.LENGTH_LONG).show();
			Intent i=new Intent(feedback.this,after_login_main_menu.class);
			startActivity(i);
			
		}
	}
	
	
	}
	
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	

}
