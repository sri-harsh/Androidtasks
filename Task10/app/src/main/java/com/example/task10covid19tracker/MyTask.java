package com.example.task10covid19tracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MyTask extends AsyncTask<Void,Void,String> {


    String url="https://api.covid19api.com/dayone/country/IN";
    RecyclerView myRv;
    Context ct;
    ProgressDialog pd;

    public MyTask(MainActivity mainActivity, RecyclerView rv) {

        myRv=rv;
        ct=mainActivity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        pd=new ProgressDialog(ct);
        pd.setMessage("Please wait");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL u=new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) u.openConnection();
            InputStream is= connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line="";
            StringBuilder builder=new StringBuilder();
            while((line=reader.readLine())!=null){

                builder.append(line);
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(ct, ""+s, Toast.LENGTH_SHORT).show();
        pd.dismiss();
        List<Covid> covidList =new ArrayList<>();
        try {
            JSONArray rootJsonArray = new JSONArray(s);

            for(int i=(rootJsonArray.length()-1);i>0;i--){
                JSONObject indexObject = rootJsonArray.getJSONObject(i);
                String date= indexObject.optString("Date").substring(0,10);
                String active= indexObject.optString("Active");
                String deaths= indexObject.optString("Deaths");
                String recovered= indexObject.optString("Recovered");
                Covid covid=new Covid(date,active,recovered,deaths);
                covidList.add(covid);
            }

            Log.i("SIZE",""+covidList.size());

            myRv.setLayoutManager(new LinearLayoutManager(ct));
            myRv.setAdapter(new CovidAdapter(ct,covidList));

            //Log.i("DATA",date+""+active+""+confirmed+""+recovered);
            //mytv.setText(date+"\n"+active+"\n"+confirmed+"\n"+recovered);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
