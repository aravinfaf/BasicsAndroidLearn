package com.e.learn.firebaserecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e.learn.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseInputActivity extends AppCompatActivity {


    private EditText editText, etd;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    DatabaseReference databaseReference;
    ArrayList<Model> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_input);

        databaseReference = FirebaseDatabase.getInstance().getReference("artists");
        data = new ArrayList<>();

        editText = findViewById(R.id.et);
        etd = findViewById(R.id.etd);
        button = findViewById(R.id.btn);
        recyclerView = findViewById(R.id.list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = databaseReference.push().getKey();
                Model model = new Model(id, editText.getText().toString(), etd.getText().toString());

                databaseReference.child(id).setValue(model);
                editText.setText("");
                etd.setText("");

                Toast.makeText(FirebaseInputActivity.this, "Artist added", Toast.LENGTH_LONG).show();

               fetch();
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;
        public TextView txtDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtDesc = itemView.findViewById(R.id.list_desc);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }

        public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }
    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference("artists");

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, new SnapshotParser<Model>() {
                            @NonNull
                            @Override
                            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Model(snapshot.child("mId").getValue().toString(),
                                        snapshot.child("mTitle").getValue().toString(),
                                        snapshot.child("mDesc").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Model model) {
                holder.setTxtTitle(model.getmTitle());
                holder.setTxtDesc(model.getmDesc());

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(FirebaseInputActivity.this, model.getmTitle(), Toast.LENGTH_SHORT).show();

                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(FirebaseInputActivity.this);
                        bottomSheetDialog.setContentView(R.layout.bottom_sheet_update);
                        bottomSheetDialog.show();

                        EditText nameET = bottomSheetDialog.findViewById(R.id.nameET);
                        Button updateBT = bottomSheetDialog.findViewById(R.id.updateBT);
                        Button deleteBT = bottomSheetDialog.findViewById(R.id.deleteBT);

                        updateBT.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (nameET.getText().toString().trim().length() != 0) {
                                    //getting the specified artist reference
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("artists").child(model.getmId());

                                    //updating artist
                                    Model artist = new Model(model.getmId(), nameET.getText().toString(), "hh");
                                    databaseReference.setValue(artist);
                                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                                    bottomSheetDialog.dismiss();

                                    notifyDataSetChanged();
                                }

                            }
                        });

                        deleteBT.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("artists").child(model.getmId());
                                databaseReference.removeValue();
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                                bottomSheetDialog.dismiss();

                            }
                        });
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
