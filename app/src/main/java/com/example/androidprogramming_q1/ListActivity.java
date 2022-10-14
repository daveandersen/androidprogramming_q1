package com.example.androidprogramming_q1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.androidprogramming_q1.models.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().hide();

        String url = "https://reqres.in/api/users";

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));
    }

    private StringRequest getStringRequest(String url){
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{

                JSONObject object = new JSONObject(response);
                Log.d("objectTag", object.toString());
                JSONArray array = object.getJSONArray("data");
                users = new ArrayList<User>();

                for(int i = 0; i < array.length(); i++) {
                    JSONObject user = array.getJSONObject(i);
                    Log.d("userTag", user.toString());
                    users.add(
                            new User(
                                user.getInt("id"),
                                user.getString("email"),
                                user.getString("first_name"),
                                user.getString("last_name"),
                                user.getString("avatar")
                    ));
                }
                Log.d("usersTag",users.toString());
                prepareListView();

            } catch (JSONException e){
                e.printStackTrace();
            }

        }, (error) -> {

        });
    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(users);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }
}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<User> users;

    public DataModelAdapter(ArrayList<User> dataModels) {
        this.users = dataModels;
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_card_view, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        User user = users.get(position);

//        holder.firstName.setText(user.getFirstname());
//        holder.lastName.setText(user.getLastname());
        holder.name.setText(user.getFirstname()+ " " + user.getLastname());

        Glide.with(holder.parent.getContext())
                .load(user.getAvatar())
                .circleCrop()
                .into(holder.imageView);

        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("onClickedUser", new Gson().toJson(user));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    ImageView imageView;
    TextView firstName;
    TextView lastName;
    LinearLayout id;
    TextView name;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        id = itemView.findViewById(R.id.linear_layout);
        imageView = itemView.findViewById(R.id.image_view);
        name = itemView.findViewById(R.id.name_text_view);
//        firstName = itemView.findViewById(R.id.first_name_text_view);
//        lastName = itemView.findViewById(R.id.last_name_text_view);
    }

}