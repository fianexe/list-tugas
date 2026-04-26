package com.example.utp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.utp.state.TaskState
import com.example.utp.ui.components.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onTaskClick: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Daftar Tugas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Tugas")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(TaskState.tasks) { task ->
                TaskItem(
                    task = task,
                    onTaskClick = onTaskClick,
                    onToggleCompletion = { TaskState.toggleTaskComplete(it) },
                    onDeleteTask = { TaskState.deleteTask(it) }
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Tambah Tugas Baru") },
                text = {
                    Column {
                        TextField(
                            value = newTaskTitle,
                            onValueChange = { newTaskTitle = it },
                            label = { Text("Judul") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = newTaskDescription,
                            onValueChange = { newTaskDescription = it },
                            label = { Text("Deskripsi") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (newTaskTitle.isNotBlank()) {
                            TaskState.addTask(newTaskTitle, newTaskDescription)
                            newTaskTitle = ""
                            newTaskDescription = ""
                            showDialog = false
                        }
                    }) {
                        Text("Tambah")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}
