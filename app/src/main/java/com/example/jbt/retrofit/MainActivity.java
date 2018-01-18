package com.example.jbt.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create retrofit and assign BaseURL (will not change)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //connect the retrofit class with the interface calss
        //generate new instance of the interface and call it service
        GitHubService service = retrofit.create(GitHubService.class);

        final Call<List<User>> allUsersCall=  service.getAllUsers("190");


        (findViewById(R.id.downloadBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  //  if not on main thread you can use this call
                   // Response<List<User>> allUsersResponse= allUsersCall.execute();

                    allUsersCall.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Response<List<User>> response, Retrofit retrofit) {

                            List<User> allUsers= response.body();

                            ListView usersLV=  (ListView) findViewById(R.id.usersLV);
                              ArrayAdapter<User> myAdapter= new ArrayAdapter<User>(MainActivity.this,
                                   android.R.layout.simple_list_item_1,allUsers );
                             usersLV.setAdapter(myAdapter);


                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });



            }
        });







    }
}
