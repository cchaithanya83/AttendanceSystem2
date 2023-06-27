package com.example.attendancesystem;

import com.example.attendancesystem.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<Subject> subjectList;

    public SubjectAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
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
        private TextView tvStudentNames;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.username);
            tvStudentNames = itemView.findViewById(R.id.usn);
        }

        public void bind(Subject subject) {
            tvSubjectName.setText(subject.getSubjectName());
            List<String> studentNames = subject.getStudentNames();
            String studentNamesText = "";
            for (String studentName : studentNames) {
                studentNamesText += studentName + ", ";
            }
            // Remove the trailing comma and space
            if (!studentNamesText.isEmpty()) {
                studentNamesText = studentNamesText.substring(0, studentNamesText.length() - 2);
            }
            tvStudentNames.setText(studentNamesText);
        }
    }
}
