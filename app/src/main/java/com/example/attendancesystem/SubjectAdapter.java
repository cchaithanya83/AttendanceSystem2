package com.example.attendancesystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancesystem.R;
import com.example.attendancesystem.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<Subject> subjectList;

    public SubjectAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.bind(subject);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSubjectName;
        private RecyclerView recyclerViewStudents;
        private TextView tvNoStudents;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.t);
            recyclerViewStudents = itemView.findViewById(R.id.recyclerViewStudents);
            tvNoStudents = itemView.findViewById(R.id.tvNoStudents);
        }

        public void bind(Subject subject) {
            tvSubjectName.setText(subject.getSubjectName());

            List<String> studentList = subject.getStudentList();
            if (studentList.isEmpty()) {
                recyclerViewStudents.setVisibility(View.GONE);
                tvNoStudents.setVisibility(View.VISIBLE);
            } else {
                recyclerViewStudents.setVisibility(View.VISIBLE);
                tvNoStudents.setVisibility(View.GONE);

                // Set up the RecyclerView for student names
                recyclerViewStudents.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                StudentAdapter studentAdapter = new StudentAdapter(studentList);
                recyclerViewStudents.setAdapter(studentAdapter);
            }
        }
    }
}
