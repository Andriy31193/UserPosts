package edu.itstep.myapplic09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Api api;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api = NetworkService.getInstance().getApi();

        // Fetch the list of posts
        api.getAll().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                postAdapter = new PostAdapter(posts, new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Post post) {
                        // Handle item click
                        showUserInfo(post.getUserId());
                    }
                });
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void showUserInfo(int userId) {
        // Fetch user information by user ID
        api.getUserById(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                // Launch UserInfoActivity with user information
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                intent.putExtra("userName", user.getName());
                intent.putExtra("userPhone", user.getPhone());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
