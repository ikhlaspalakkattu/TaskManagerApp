package com.example.buttonlist;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.ArrayList;

// incorrect latest
//public class MainActivity extends AppCompatActivity {
//
//    private ConstraintLayout constraintLayout;
//    private int lastViewId;
//    private static final int MAX_TASKS = 50;
//    private final ArrayList<View> taskRows = new ArrayList<>();
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        constraintLayout = findViewById(R.id.constraintLayout);
//        Button addButton = findViewById(R.id.addButton);
//        sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);
//
//        lastViewId = addButton.getId();
//
//        addButton.setOnClickListener(v -> {
//            if (taskRows.size() < MAX_TASKS) {
//                addTaskRow(null, null);
//            } else {
//                addButton.setText("Limit Reached");
//                addButton.setEnabled(false);
//            }
//        });
//
//        loadTasks();
//    }
//
//    private void addTaskRow(String taskKey, String taskName) {
//        ConstraintLayout taskRowLayout = new ConstraintLayout(this);
//        taskRowLayout.setId(View.generateViewId());
//        constraintLayout.addView(taskRowLayout);
//
//        ConstraintLayout.LayoutParams rowLayoutParams = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_PARENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskRowLayout.setLayoutParams(rowLayoutParams);
//
//        Button taskButton = new Button(this);
//        taskButton.setId(View.generateViewId());
//
//        String key;
//        if (taskKey == null) {
//            key = "task_" + System.currentTimeMillis();
//            saveTaskData(key, "New Task");
//        } else {
//            key = taskKey;
//        }
//        taskButton.setTag(key);
//
//        if (taskName == null) {
//            taskButton.setText("New Task");
//        } else {
//            taskButton.setText(taskName);
//        }
//
//        taskRowLayout.addView(taskButton);
//
//        ConstraintLayout.LayoutParams taskButtonParams = new ConstraintLayout.LayoutParams(
//                0,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskButton.setLayoutParams(taskButtonParams);
//
//        taskButton.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, TaskPage.class);
//            intent.putExtra("taskKey", key);
//            startActivityForResult(intent, 1);
//        });
//
//        Button checkButton = new Button(this);
//        checkButton.setId(View.generateViewId());
//        checkButton.setText("V");
//
//        taskRowLayout.addView(checkButton);
//
//        ConstraintLayout.LayoutParams checkButtonParams = new ConstraintLayout.LayoutParams(
//                0,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        checkButton.setLayoutParams(checkButtonParams);
//
//        checkButton.setOnClickListener(v -> removeTaskRow(taskRowLayout));
//
//        ConstraintSet taskRowSet = new ConstraintSet();
//        taskRowSet.clone(taskRowLayout);
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.START, taskRowLayout.getId(), ConstraintSet.START, 16);
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.END, checkButton.getId(), ConstraintSet.START, 8);
//        taskRowSet.constrainPercentWidth(taskButton.getId(), 0.85f);
//
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.START, taskButton.getId(), ConstraintSet.END, 8);
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.END, taskRowLayout.getId(), ConstraintSet.END, 16);
//        taskRowSet.constrainPercentWidth(checkButton.getId(), 0.15f);
//
//        taskRowSet.applyTo(taskRowLayout);
//
//        ConstraintSet mainSet = new ConstraintSet();
//        mainSet.clone(constraintLayout);
//
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
//
//        mainSet.applyTo(constraintLayout);
//
//        lastViewId = taskRowLayout.getId();
//        taskRows.add(taskRowLayout);
//    }
//
//    private void removeTaskRow(View taskRow) {
//        Button taskButton = (Button) ((ConstraintLayout) taskRow).getChildAt(0);
//        String taskKey = (String) taskButton.getTag();
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(taskKey);
//        editor.remove(taskKey + "_name");
//        editor.remove(taskKey + "_description");
//        editor.remove(taskKey + "_dueDate");
//        editor.apply();
//
//        constraintLayout.removeView(taskRow);
//        taskRows.remove(taskRow);
//
//        ConstraintSet mainSet = new ConstraintSet();
//        mainSet.clone(constraintLayout);
//
//        int previousViewId = R.id.addButton;
//        for (View row : taskRows) {
//            mainSet.connect(row.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16);
//            mainSet.connect(row.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
//            mainSet.connect(row.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
//            previousViewId = row.getId();
//        }
//
//        mainSet.applyTo(constraintLayout);
//        lastViewId = taskRows.isEmpty() ? R.id.addButton : taskRows.get(taskRows.size() - 1).getId();
//    }
//
//    private void saveTaskData(String taskKey, String taskName) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(taskKey, taskName);
//        editor.apply();
//    }
//
//    private void loadTasks() {
//        Map<String, ?> allTasks = sharedPreferences.getAll();
//        for (Map.Entry<String, ?> entry : allTasks.entrySet()) {
//            if (entry.getKey().startsWith("task_") && !entry.getKey().contains("_")) {
//                String taskKey = entry.getKey();
//                String taskName = (String) entry.getValue();
//                addTaskRow(taskKey, taskName);
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            String updatedTaskName = data.getStringExtra("taskName");
//            String taskKey = data.getStringExtra("taskKey");
//
//            for (View row : taskRows) {
//                Button taskButton = (Button) ((ConstraintLayout) row).getChildAt(0);
//                if (taskButton.getTag().equals(taskKey)) {
//                    taskButton.setText(updatedTaskName);
//                    saveTaskData(taskKey, updatedTaskName);
//                    break;
//                }
//            }
//        }
//    }
//}





public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private int lastViewId;
    private int counter = 0;
    private static final int MAX_TASKS = 50;
    private final ArrayList<View> taskRows = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
        Button addButton = findViewById(R.id.addButton);
        sharedPreferences = getSharedPreferences("TaskData", MODE_PRIVATE);

        lastViewId = addButton.getId();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < MAX_TASKS) {
                    addTaskRow();
                } else {
                    addButton.setText("Limit Reached");
                    addButton.setEnabled(false);
                }
            }
        });

        // Load existing tasks from SharedPreferences
        loadTasks();
    }

    private void addTaskRow() {
        ConstraintLayout taskRowLayout = new ConstraintLayout(this);
        taskRowLayout.setId(View.generateViewId());
        constraintLayout.addView(taskRowLayout);

        ConstraintLayout.LayoutParams rowLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        taskRowLayout.setLayoutParams(rowLayoutParams);

        Button taskButton = new Button(this);
        taskButton.setId(View.generateViewId());
        taskButton.setText("Task " + (++counter));
        taskButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

        String taskKey = "task_" + counter;
        taskButton.setTag(taskKey);  // Set task key as tag

        taskRowLayout.addView(taskButton);

        ConstraintLayout.LayoutParams taskButtonParams = new ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        taskButton.setLayoutParams(taskButtonParams);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskPage.class);
                intent.putExtra("taskKey", taskKey);
                startActivityForResult(intent, 1);
            }
        });

        Button checkButton = new Button(this);
        checkButton.setId(View.generateViewId());
        checkButton.setText("V");

        taskRowLayout.addView(checkButton);

        ConstraintLayout.LayoutParams checkButtonParams = new ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        checkButton.setLayoutParams(checkButtonParams);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTaskRow(taskRowLayout);
            }
        });

        ConstraintSet taskRowSet = new ConstraintSet();
        taskRowSet.clone(taskRowLayout);
        taskRowSet.connect(taskButton.getId(), ConstraintSet.START, taskRowLayout.getId(), ConstraintSet.START, 16);
        taskRowSet.connect(taskButton.getId(), ConstraintSet.END, checkButton.getId(), ConstraintSet.START, 8);
        taskRowSet.constrainPercentWidth(taskButton.getId(), 0.85f);

        taskRowSet.connect(checkButton.getId(), ConstraintSet.START, taskButton.getId(), ConstraintSet.END, 8);
        taskRowSet.connect(checkButton.getId(), ConstraintSet.END, taskRowLayout.getId(), ConstraintSet.END, 16);
        taskRowSet.constrainPercentWidth(checkButton.getId(), 0.15f);

        taskRowSet.applyTo(taskRowLayout);

        ConstraintSet mainSet = new ConstraintSet();
        mainSet.clone(constraintLayout);

        mainSet.connect(taskRowLayout.getId(), ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16);
        mainSet.connect(taskRowLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        mainSet.connect(taskRowLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);

        mainSet.applyTo(constraintLayout);

        lastViewId = taskRowLayout.getId();
        taskRows.add(taskRowLayout);

        // Save the new task in SharedPreferences
        saveTaskData(taskKey, "Task " + counter);
    }

    private void removeTaskRow(View taskRow) {
        constraintLayout.removeView(taskRow);
        taskRows.remove(taskRow);

        ConstraintSet mainSet = new ConstraintSet();
        mainSet.clone(constraintLayout);

        int previousViewId = R.id.addButton;
        for (View row : taskRows) {
            mainSet.connect(row.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16);
            mainSet.connect(row.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
            mainSet.connect(row.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
            previousViewId = row.getId();
        }

        mainSet.applyTo(constraintLayout);

        lastViewId = taskRows.isEmpty() ? R.id.addButton : taskRows.get(taskRows.size() - 1).getId();
    }

    private void saveTaskData(String taskKey, String taskName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(taskKey, taskName);
        editor.apply();
    }



    private void loadTasks() {
        // Load tasks from SharedPreferences
        for (int i = 1; i <= MAX_TASKS; i++) {
            String taskKey = "task_" + i;
            String taskName = sharedPreferences.getString(taskKey, null);



            if (taskName != null) {
                // Add task button to the layout
                addTaskRowWithSavedData(taskKey, taskName);
            }
        }
    }

    private void addTaskRowWithSavedData(String taskKey, String taskName) {
        ConstraintLayout taskRowLayout = new ConstraintLayout(this);
        taskRowLayout.setId(View.generateViewId());
        constraintLayout.addView(taskRowLayout);

        ConstraintLayout.LayoutParams rowLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        taskRowLayout.setLayoutParams(rowLayoutParams);

        Button taskButton = new Button(this);
        taskButton.setId(View.generateViewId());
        taskButton.setText(taskName);
        taskButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

        taskButton.setTag(taskKey);

        taskRowLayout.addView(taskButton);

        ConstraintLayout.LayoutParams taskButtonParams = new ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        taskButton.setLayoutParams(taskButtonParams);

        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskPage.class);
                intent.putExtra("taskKey", taskKey);
                startActivityForResult(intent, 1);
            }
        });

        Button checkButton = new Button(this);
        checkButton.setId(View.generateViewId());
        checkButton.setText("V");

        taskRowLayout.addView(checkButton);

        ConstraintLayout.LayoutParams checkButtonParams = new ConstraintLayout.LayoutParams(
                0,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        checkButton.setLayoutParams(checkButtonParams);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTaskRow(taskRowLayout);
            }
        });

        ConstraintSet taskRowSet = new ConstraintSet();
        taskRowSet.clone(taskRowLayout);
        taskRowSet.connect(taskButton.getId(), ConstraintSet.START, taskRowLayout.getId(), ConstraintSet.START, 16);
        taskRowSet.connect(taskButton.getId(), ConstraintSet.END, checkButton.getId(), ConstraintSet.START, 8);
        taskRowSet.constrainPercentWidth(taskButton.getId(), 0.85f);

        taskRowSet.connect(checkButton.getId(), ConstraintSet.START, taskButton.getId(), ConstraintSet.END, 8);
        taskRowSet.connect(checkButton.getId(), ConstraintSet.END, taskRowLayout.getId(), ConstraintSet.END, 16);
        taskRowSet.constrainPercentWidth(checkButton.getId(), 0.15f);

        taskRowSet.applyTo(taskRowLayout);

        ConstraintSet mainSet = new ConstraintSet();
        mainSet.clone(constraintLayout);

        mainSet.connect(taskRowLayout.getId(), ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16);
        mainSet.connect(taskRowLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        mainSet.connect(taskRowLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);

        mainSet.applyTo(constraintLayout);

        lastViewId = taskRowLayout.getId();
        taskRows.add(taskRowLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String updatedTaskName = data.getStringExtra("taskName");

            // Find and update the task button in the layout
            for (View row : taskRows) {
                Button taskButton = (Button) ((ConstraintLayout) row).getChildAt(0);
                if (taskButton.getText().toString().startsWith("Task")) {
                    taskButton.setText(updatedTaskName);
                    break;
                }
            }
        }
    }
}

//public class MainActivity extends AppCompatActivity {
//
//    private ConstraintLayout constraintLayout;
//    private int lastViewId; // Tracks the ID of the last added view
//    private int counter = 0; // Counter for tasks
//    private static final int MAX_TASKS = 50; // Limit to 50 tasks
//    private final ArrayList<View> taskRows = new ArrayList<>(); // Track all task rows
//    private SharedPreferences sharedPreferences;
//    private static final String PREFS_NAME = "TaskPrefs";
//    private static final String TASKS_KEY = "tasks"; // SharedPreferences key for tasks
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        constraintLayout = findViewById(R.id.constraintLayout);
//        Button addButton = findViewById(R.id.addButton);
//
//        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//
//        // Set the initial view ID to the button's ID
//        lastViewId = addButton.getId();
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (counter < MAX_TASKS) { // Limit to 50 tasks
//                    addTaskRow();
//                } else {
//                    // Optionally show a message if the limit is reached
//                    addButton.setText("Limit Reached");
//                    addButton.setEnabled(false);
//                }
//            }
//        });
//
//        loadTasks(); // Load saved tasks when the app starts
//    }
//
//    private void addTaskRow() {
//        // Create a container layout for the task row
//        ConstraintLayout taskRowLayout = new ConstraintLayout(this);
//        taskRowLayout.setId(View.generateViewId());
//        constraintLayout.addView(taskRowLayout);
//
//        // Set layout params for the task row
//        ConstraintLayout.LayoutParams rowLayoutParams = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_PARENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskRowLayout.setLayoutParams(rowLayoutParams);
//
//        // Create the Task Button
//        Button taskButton = new Button(this);
//        taskButton.setId(View.generateViewId());
//        taskButton.setText("Task " + (++counter)); // Default task name
//        taskButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
//
//        // Add the Task Button to the task row
//        taskRowLayout.addView(taskButton);
//
//        // Set layout params for the Task Button
//        ConstraintLayout.LayoutParams taskButtonParams = new ConstraintLayout.LayoutParams(
//                0, // Width will be constrained
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskButton.setLayoutParams(taskButtonParams);
//
//        // Add click listener to navigate to TaskPage
//        taskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TaskPage.class);
//                intent.putExtra("taskKey", "task_" + counter); // Pass taskKey to TaskPage
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        // Create the Check Button
//        Button checkButton = new Button(this);
//        checkButton.setId(View.generateViewId());
//        checkButton.setText("V");
//
//        // Add the Check Button to the task row
//        taskRowLayout.addView(checkButton);
//
//        // Set layout params for the Check Button
//        ConstraintLayout.LayoutParams checkButtonParams = new ConstraintLayout.LayoutParams(
//                0, // Width will be constrained
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        checkButton.setLayoutParams(checkButtonParams);
//
//        // Set constraints for the task row
//        ConstraintSet taskRowSet = new ConstraintSet();
//        taskRowSet.clone(taskRowLayout);
//
//        // Task Button: Start to Start of parent, End to Start of Check Button
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.START, taskRowLayout.getId(), ConstraintSet.START, 16);
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.END, checkButton.getId(), ConstraintSet.START, 8);
//        taskRowSet.constrainPercentWidth(taskButton.getId(), 0.85f); // 85% width
//
//        // Check Button: Start to End of Task Button, End to End of parent
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.START, taskButton.getId(), ConstraintSet.END, 8);
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.END, taskRowLayout.getId(), ConstraintSet.END, 16);
//        taskRowSet.constrainPercentWidth(checkButton.getId(), 0.15f); // 15% width
//
//        taskRowSet.applyTo(taskRowLayout);
//
//        // Set constraints for the task row in the main layout
//        ConstraintSet mainSet = new ConstraintSet();
//        mainSet.clone(constraintLayout);
//
//        // Position the task row below the last view
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
//
//        mainSet.applyTo(constraintLayout);
//
//        // Update the lastViewId and track the task row
//        lastViewId = taskRowLayout.getId();
//        taskRows.add(taskRowLayout);
//
//        // Add functionality to the Check Button
//        checkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeTaskRow(taskRowLayout);
//            }
//        });
//    }
//
//    private void removeTaskRow(View taskRow) {
//        // Remove the task row from the layout and the list
//        constraintLayout.removeView(taskRow);
//        taskRows.remove(taskRow);
//
//        // Reposition the remaining rows
//        ConstraintSet mainSet = new ConstraintSet();
//        mainSet.clone(constraintLayout);
//
//        int previousViewId = R.id.addButton; // Start with the Add Task button
//        for (View row : taskRows) {
//            mainSet.connect(row.getId(), ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16);
//            mainSet.connect(row.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
//            mainSet.connect(row.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
//            previousViewId = row.getId();
//        }
//
//        // Apply the updated constraints
//        mainSet.applyTo(constraintLayout);
//
//        // Update the lastViewId to the last task row or the Add Task button
//        lastViewId = taskRows.isEmpty() ? R.id.addButton : taskRows.get(taskRows.size() - 1).getId();
//
//        // Save the updated tasks list after deletion
//        saveTasks();
//    }
//
//    private void loadTasks() {
//        // Load the saved task names from SharedPreferences and recreate the task buttons
//        Set<String> savedTasks = sharedPreferences.getStringSet(TASKS_KEY, new HashSet<String>());
//        for (String taskName : savedTasks) {
//            addTaskRowWithName(taskName);
//        }
//    }
//
//    private void addTaskRowWithName(String taskName) {
//        // Create a container layout for the task row
//        ConstraintLayout taskRowLayout = new ConstraintLayout(this);
//        taskRowLayout.setId(View.generateViewId());
//        constraintLayout.addView(taskRowLayout);
//
//        // Set layout params for the task row
//        ConstraintLayout.LayoutParams rowLayoutParams = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_PARENT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskRowLayout.setLayoutParams(rowLayoutParams);
//
//        // Create the Task Button
//        Button taskButton = new Button(this);
//        taskButton.setId(View.generateViewId());
//        taskButton.setText(taskName); // Set saved task name
//        taskButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
//
//        // Add the Task Button to the task row
//        taskRowLayout.addView(taskButton);
//
//        // Set layout params for the Task Button
//        ConstraintLayout.LayoutParams taskButtonParams = new ConstraintLayout.LayoutParams(
//                0, // Width will be constrained
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        taskButton.setLayoutParams(taskButtonParams);
//
//        // Add click listener to navigate to TaskPage
//        taskButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TaskPage.class);
//                intent.putExtra("taskKey", taskName); // Pass taskKey to TaskPage
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        // Create the Check Button
//        Button checkButton = new Button(this);
//        checkButton.setId(View.generateViewId());
//        checkButton.setText("V");
//
//        // Add the Check Button to the task row
//        taskRowLayout.addView(checkButton);
//
//        // Set layout params for the Check Button
//        ConstraintLayout.LayoutParams checkButtonParams = new ConstraintLayout.LayoutParams(
//                0, // Width will be constrained
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        checkButton.setLayoutParams(checkButtonParams);
//
//        // Set constraints for the task row
//        ConstraintSet taskRowSet = new ConstraintSet();
//        taskRowSet.clone(taskRowLayout);
//
//        // Task Button: Start to Start of parent, End to Start of Check Button
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.START, taskRowLayout.getId(), ConstraintSet.START, 16);
//        taskRowSet.connect(taskButton.getId(), ConstraintSet.END, checkButton.getId(), ConstraintSet.START, 8);
//        taskRowSet.constrainPercentWidth(taskButton.getId(), 0.85f); // 85% width
//
//        // Check Button: Start to End of Task Button, End to End of parent
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.START, taskButton.getId(), ConstraintSet.END, 8);
//        taskRowSet.connect(checkButton.getId(), ConstraintSet.END, taskRowLayout.getId(), ConstraintSet.END, 16);
//        taskRowSet.constrainPercentWidth(checkButton.getId(), 0.15f); // 15% width
//
//        taskRowSet.applyTo(taskRowLayout);
//
//        // Set constraints for the task row in the main layout
//        ConstraintSet mainSet = new ConstraintSet();
//        mainSet.clone(constraintLayout);
//
//        // Position the task row below the last view
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.TOP, lastViewId, ConstraintSet.BOTTOM, 16);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
//        mainSet.connect(taskRowLayout.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
//
//        mainSet.applyTo(constraintLayout);
//
//        // Update the lastViewId and track the task row
//        lastViewId = taskRowLayout.getId();
//        taskRows.add(taskRowLayout);
//
//        // Add functionality to the Check Button
//        checkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeTaskRow(taskRowLayout);
//            }
//        });
//    }
//
//    private void saveTasks() {
//        // Save the current task names to SharedPreferences
//        Set<String> taskNames = new HashSet<>();
//        for (View row : taskRows) {
//            Button taskButton = (Button) row.findViewById(View.generateViewId());
//            taskNames.add(taskButton.getText().toString()); // Add task name
//        }
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putStringSet(TASKS_KEY, taskNames);
//        editor.apply();
//    }
//}