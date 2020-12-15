package com.example.med_pharm;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.med_pharm.sub_product.Select_rec;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class detail_sub_product extends Activity implements OnClickListener
{
	SharedPreferences ses=null;
	String spid=null,rid=null;
	JSONParser jsonp=new JSONParser();
	String url="http://10.0.2.2/Med_Pharm/detail_sub_product.php";
	ProgressDialog pDialog=null;
	Spinner sp;
	
	private static final String TAG_table= "sub_pro";
	private static final String Tag_content="content";
	private static final String Tag_company="company";
	private static final String tag_mfg_date="mfg_date";
	private static final String tag_exp_date="exp_date";
	private static final String TAG_name="name";
	private static final  String tag_rate= "rate";
	private static final String tag_spid="sp_id";
	private static final String tag_pid="pid";
	//private static final String tag_stock="stock";
	
	JSONArray array=null;
	String name=null,content=null,rate=null,md=null,ed=null,company=null,strip=null;
	TextView pname=null;
	TextView pcontent=null;
	TextView prate=null;
	TextView pmd=null;
	TextView ped=null;
	TextView pcompany=null;
	TextView pstrip=null;
	ArrayAdapter<CharSequence>adapter;
	Button btn;
	ContentValues cv=null;
	private SQLiteDatabase db;
	
	
	
	protected void onCreate(Bundle savedInstanceState) 
	 {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.detail_sub_product);
	     
	     btn=(Button)findViewById(R.id.cart);
	     btn.setOnClickListener(this);
	     
	     db = openOrCreateDatabase("MEDDB", SQLiteDatabase.CREATE_IF_NECESSARY,null);
			
			
	     pname=(TextView)findViewById(R.id.name);
	     pcontent=(TextView)findViewById(R.id.content);
	     prate=(TextView)findViewById(R.id.rate);
	     pmd=(TextView)findViewById(R.id.mfg);
	     ped=(TextView)findViewById(R.id.exp);
	     pcompany=(TextView)findViewById(R.id.company);
	     
	     ses=getSharedPreferences(login.s_name, MODE_PRIVATE);
	     spid=ses.getString("spid",null);
	     rid=ses.getString("rid", null);
	     
	     sp=(Spinner)findViewById(R.id.spinner);
	     adapter=ArrayAdapter.createFromResource(detail_sub_product.this, R.array.Item, android.R.layout.simple_spinner_item);
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     sp.setAdapter(adapter);
	        
	     
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
				Intent i=new Intent(detail_sub_product.this,profile.class);
				startActivity(i);
			}
			
			
			if(str.equals("Product"))
			{
				Intent i=new Intent(detail_sub_product.this,after_login_product.class);
				startActivity(i);
			}
			
			if(str.equals("Order"))
			{
				Intent i=new Intent(detail_sub_product.this,order.class);
				startActivity(i);
			}
			
			if(str.equals("Setting"))
			{
				Intent i=new Intent(detail_sub_product.this,setting.class);
				startActivity(i);
			}
			
			if(str.equals("Feedback"))
			{
				Intent i=new Intent(detail_sub_product.this,feedback.class);
				startActivity(i);
			}
			
			if(str.equals("Logout"))
			{
	
				SharedPreferences.Editor edit=ses.edit();
				
				edit.putString("rid",rid);
				edit.commit();
				edit.clear();
				
				Intent i=new Intent(detail_sub_product.this,login.class);
				startActivity(i);
			}		 
			return true;
	 }
	 

	class Select_rec extends AsyncTask<String, String, String>
	{
			protected void onPreExecute() 
			{
				super.onPreExecute();
				pDialog = new ProgressDialog(detail_sub_product.this);
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
			params.add(new BasicNameValuePair("pid", spid));
			JSONObject obj=jsonp.makeHttpRequest(url, "GET", params);
			
			try
			{
				array=obj.getJSONArray(TAG_table);
				for(int i=0;i<array.length();i++)
				{
					JSONObject c= array.getJSONObject(i);
					name=c.getString(TAG_name);
					rate=c.getString(tag_rate);
					content=c.getString(Tag_content);
					company=c.getString(Tag_company);
					ed=c.getString(tag_exp_date);
					md=c.getString(tag_mfg_date);			
					
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
			pname.setText(name);
			pcontent.setText(content);
			prate.setText(rate);
			pcompany.setText(company);
			pmd.setText(md);
			ped.setText(ed);
						
		}
	}


	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		
		
		String sql="CREATE TABLE IF NOT EXISTS ADD_TO_CART(acid INTEGER PRIMARY KEY AUTOINCREMENT,PNAME TEXT,RATE TEXT,QTY INTEGER,SPID INTEGER,RID INTEGER)";
		db.execSQL(sql);
		
		if(btn==arg0)
		{
			strip=sp.getSelectedItem().toString();
			
			//String qty=strip.getText().toString();
			int sp_id=Integer.parseInt(spid);
			int r_id=Integer.parseInt(rid);
			
			cv=new ContentValues();
			cv.put("PNAME", name);
			cv.put("RATE", rate);
			cv.put("QTY", strip);
			cv.put("SPID", sp_id);
			cv.put("RID", r_id);
			
			long a=db.insert("ADD_TO_CART", null, cv);
				if(a>0)
				{
					Toast.makeText(detail_sub_product.this, "Product  is Add", Toast.LENGTH_LONG).show();
					
				//	SharedPreferences.Editor edit=ses.edit();
				//	edit.putString("rid",r_id+"");
				//	edit.commit();
					
					Intent i=new Intent(detail_sub_product.this,after_login_product.class);
					startActivity(i);
				}
				else
				{
					Toast.makeText(detail_sub_product.this, "Product is Not Add", Toast.LENGTH_LONG).show();
					Intent i=new Intent(detail_sub_product.this,after_login_product.class);
					startActivity(i);
				}
			
		}
		
		
	}


}
