package com.example.ganghoyong.login_register2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by ganghoyong on 2016. 4. 10..
 */
public class ServerRequests {


    ProgressDialog progressDialog;
    public static  final int CONNECTION_TIMEOUT = 1000 * 15;
    public static  final String SERVER_ADDRESS = "PHP SERVER 도메인 주소";

    public ServerRequests(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Plese wait....");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback)
    {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();

    }

    public void fetchUserDataInBackground(User user, GetUserCallback callBack)
    {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, callBack).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>
    {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack)
        {
            this.user = user;
            this.userCallback = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // 회원가입 부분
            // Use HashMap, it works similar to NameValuePair
            Map<String, String> dataToSend = new HashMap<>();
            dataToSend.put("email", user.mail);
            dataToSend.put("password", user.password);
            dataToSend.put("name",user.name);
            dataToSend.put("phoneNumber", user.phoneNumber);
            dataToSend.put("birthData",user.birthDate);

            // 수정
            String encodedStr = getEncodedData(dataToSend);
            BufferedReader reader = null;

            // Connection Handling
            try {
                // Convertion address String to URL
                URL url = new URL(SERVER_ADDRESS + "Register.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                // Post Method
                con.setRequestMethod("POST");
                // To enable inputting values using POST method
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                // Writing dataToSend to outputstream wirter
                writer.write(encodedStr);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, you will have to implement the procedure below
                writer.flush();

                //Data Read Procedure - Basically reading the data comming line by line
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = reader.readLine()) !=null)
                {
                    // Read till there is something available
                    sb.append(line + "\n");
                }
                line = sb.toString();
                //Just check to the values received in Logcat
                Log.i("custom_check","The values received in the store part are as follows:");
                Log.i("custom_check",line);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(reader != null){
                    try{
                        reader.close(); // Closing the
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            /*
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            */
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User>
    {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallBack)
        {
            this.user = user;
            this.userCallback = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {
            //ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            // Use HashMap, it works similar to NameValuePair
            // login page
            Map<String, String> dataToSend = new HashMap<>();
            dataToSend.put("email", user.mail);
            dataToSend.put("password", user.password);

            // 수정
            String encodedStr = getEncodedData(dataToSend);
            BufferedReader reader = null;

            // Connection Handling
            try {
                // Convertion address String to URL
                URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                // Post Method
                con.setRequestMethod("POST");
                // To enable inputting values using POST method
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                // Writing dataToSend to outputstream wirter
                writer.write(encodedStr);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, you will have to implement the procedure below
                writer.flush();

                //Data Read Procedure - Basically reading the data comming line by line
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while ((line = reader.readLine()) !=null)
                {
                    // Read till there is something available
                    sb.append(line + "\n");
                }
                line = sb.toString();
                //Just check to the values received in Logcat
                Log.i("custom_check","The values received in the store part are as follows:");
                Log.i("custom_check",line);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(reader != null){
                    try{
                        reader.close(); // Closing the
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            // 수정
            // user 메일 비밀번호
            // 로그인 부분
            //dataToSend.add(new BasicNameValuePair("name", user.name));
            /*
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User retrunuser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpReponse httpReponse = client.execute(post);

                HttpEntity entity = httpReponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if(jObject.length() == 0)
                {
                    retrunuser = null;
                }
                else
                {
                    retrunuser = new User(user.name user.mail, user.phoneNumber user.password);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
`           */
            return null;
        }

        @Override
        protected void onPostExecute(User returnuser) {
            progressDialog.dismiss();
            userCallback.done(returnuser);
            super.onPostExecute(returnuser);
        }

    }
    private String getEncodedData(Map<String,String> data)
    {
        StringBuilder sb = new StringBuilder();
        for(String key : data.keySet())
        {
            String value = null;
            try {
                value = URLEncoder.encode(data.get(key), "UTF-8");
            }catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            if(sb.length()>0)
            {
                sb.append("&");

                sb.append(key + "=" + value);
            }
        }
        return sb.toString();
    }

}
