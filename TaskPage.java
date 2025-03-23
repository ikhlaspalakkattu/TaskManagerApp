package com.example.buttonlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

public class TaskPage extends AppCompatActivity {

    private EditText taskNameInput, taskDescriptionInput, taskDueDateInput;
    private Button confirmButton;
    private String taskKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_page);

        taskNameInput = findViewById(R.id.taskNameInput);
        taskDescriptionInput = findViewById(R.id.taskDescriptionInput);
        taskDueDateInput = findViewById(R.id.taskDueDateInput);
        confirmButton = findViewById(R.id.confirmButton);

        Intent intent = getIntent();
        taskKey = intent.getStringExtra("taskKey");

        loadTaskData();

        confirmButton.setOnClickListener(v -> {
            saveTaskData();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("taskKey", taskKey);
            returnIntent.putExtra("taskName", taskNameInput.getText().toString());
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }

    private void saveTaskData() {
        SharedPreferences sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(taskKey + "_name", taskNameInput.getText().toString());
        editor.putString(taskKey + "_description", taskDescriptionInput.getText().toString());
        editor.putString(taskKey + "_dueDate", taskDueDateInput.getText().toString());
        editor.apply();
    }

    private void loadTaskData() {
        SharedPreferences sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);
        taskNameInput.setText(sharedPreferences.getString(taskKey + "_name", ""));
        taskDescriptionInput.setText(sharedPreferences.getString(taskKey + "_description", ""));
        taskDueDateInput.setText(sharedPreferences.getString(taskKey + "_dueDate", ""));
    }
}


//
////
////public class TaskPage extends AppCompatActivity {
////
////    private EditText taskNameInput, taskDescriptionInput, taskDueDateInput;
////    private Button confirmButton;
////    private String taskKey;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.task_page);
////
////        taskNameInput = findViewById(R.id.taskNameInput);
////        taskDescriptionInput = findViewById(R.id.taskDescriptionInput);
////        taskDueDateInput = findViewById(R.id.taskDueDateInput);
////        confirmButton = findViewById(R.id.confirmButton);
//
//        // Get the task key from the Intent
//        Intent intent = getIntent();
//        taskKey = intent.getStringExtra("taskKey");
//
//        // Load task data if it exists
//        loadTaskData();
//
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveTaskData();
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("taskName", taskNameInput.getText().toString());
//                setResult(RESULT_OK, returnIntent);
//                finish(); // Close this activity and return to the main page
//            }
//        });
//    }
//
//    private void saveTaskData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        //t1
//        editor.putString(taskKey + "_name", taskNameInput.getText().toString());
//        editor.putString(taskKey + "_description", taskDescriptionInput.getText().toString());
//        editor.putString(taskKey + "_dueDate", taskDueDateInput.getText().toString());
//        editor.apply();
//    }
//
//    private void loadTaskData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);
//
//        String taskName = sharedPreferences.getString(taskKey + "_name", "");
//        String taskDescription = sharedPreferences.getString(taskKey + "_description", "");
//        String taskDueDate = sharedPreferences.getString(taskKey + "_dueDate", "");
//
//        taskNameInput.setText(taskName);
//        taskDescriptionInput.setText(taskDescription);
//        taskDueDateInput.setText(taskDueDate);
//    }
//}
