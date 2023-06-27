package com.example.attendancesystem;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class view_student extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirestoreAdapter adapter;

    private FirebaseFirestore db;
    private CollectionReference collectionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        collectionRef = db.collection("Student"); // Replace with your collection name

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FirestoreAdapter();
        recyclerView.setAdapter(adapter);

        // Fetch data from Firestore
        fetchDataFromFirestore();
    }

    private void fetchDataFromFirestore() {
        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(view_student.this, "sucess", Toast.LENGTH_SHORT).show();
                    List<YourModelClass> data = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        // Assuming you have a custom model class to represent your data
                        YourModelClass model = document.toObject(YourModelClass.class);
                        data.add(model);
                    }
                    adapter.setData(data);

                } else {
                    Log.d("Firestore", "Error getting documents: " + task.getException());
                }
            }
        });
    }

    private static class FirestoreAdapter extends RecyclerView.Adapter<FirestoreAdapter.ViewHolder> {
        private List<YourModelClass> data = new ArrayList<>();

        public void setData(List<YourModelClass> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            YourModelClass item = data.get(position);
            holder.username.setText(item.getUsername());


            holder.usn.setText(item.getusn());
            holder.semester.setText(item.getSemester());

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView username;
            public TextView usn;
            public TextView semester;




            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                username = itemView.findViewById(R.id.username);
                usn = itemView.findViewById(R.id.usn);
                semester = itemView.findViewById(R.id.semester);

            }
        }
    }

}
