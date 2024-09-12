package com.lucianoBello.taskmasterl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(taskAdapter);

        Button btnAddTask = findViewById(R.id.btn_addTarea);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTaskDialog(null, -1);
            }
        });
    }

    //Metodo para modificar en una ventana emergente o de dialogo

    public void showTaskDialog(final Task task, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(task == null ? "Agregar Tarea" : "Editar Tarea");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, (ViewGroup) findViewById(android.R.id.content), false);
        final EditText input = viewInflated.findViewById(R.id.etTaskName);

        if (task != null) {
            input.setText(task.getName());
        }

        builder.setView(viewInflated);

        builder.setPositiveButton(task == null ? "Agregar" : "Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String taskName = input.getText().toString();
                if (task == null) {
                    Task newTask = new Task(taskName, false);
                    taskList.add(newTask);
                    taskAdapter.notifyItemInserted(taskList.size() - 1);
                } else {
                    task.setName(taskName);
                    taskAdapter.notifyItemChanged(position);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
