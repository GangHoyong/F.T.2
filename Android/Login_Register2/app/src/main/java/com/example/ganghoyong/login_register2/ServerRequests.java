package com.example.ganghoyong.login_register2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.util.ArrayList;
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
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            // user 메일 비밀번호
            // 로그인 부분
            dataToSend.add(new BasicNameValuePair("name", user.name));

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

            return null;
        }

        @Override
        protected void onPostExecute(User returnuser) {
            progressDialog.dismiss();
            userCallback.done(returnuser);
            super.onPostExecute(returnuser);
        }
    }

}
