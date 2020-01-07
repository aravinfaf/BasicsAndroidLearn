package com.e.learn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

     public static final String BASE_URL ="http://192.168.192.31/";
    //live
     // public static final String BASE_URL ="http://103.118.172.90/";
      public static final String LOCAL ="http://10.30.100.79/";
      public static final String INSERT ="http://10.30.100.79/";

    //Canteen
   // public static final String CANTEEN_URL="http://192.168.192.31/";
   // public static final String CANTEEN_URL="http://103.118.172.90/";

    //LIVE http://192.168.192.12:5001/get_key
    public static final String BOOKING_URL="http://192.168.192.12:5001/";

    //LOCAL
    //public static final String BOOKING_URL="http://10.100.1.60:5001/";


    static Retrofit retrofit,retrofit1,retrofit3;



    public static Retrofit booking(String username,String password){

        if (retrofit3 == null) {
            HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor(username,password))
                    .connectTimeout(380, TimeUnit.SECONDS)
                    .readTimeout(380, TimeUnit.SECONDS)
                    .writeTimeout(380, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit3=new Retrofit.Builder()
                    .baseUrl(BOOKING_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                    .build();
        }
        return retrofit3;
    }

}
