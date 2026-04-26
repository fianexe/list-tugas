package com.example.utp.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.utp.model.Task

object TaskState {
    val tasks: SnapshotStateList<Task> = mutableStateListOf(
        Task(title = "Belajar Jetpack Compose", description = "Pelajari dasar-dasar Compose"),
        Task(title = "Mengerjakan UTP PAM", description = "Buat aplikasi To-Do List"),
        Task(title = "Push ke GitHub", description = "Upload project ke repository", isCompleted = true)
    )

    fun addTask(title: String, description: String) {
        if (title.isNotBlank()) {
            tasks.add(Task(title = title.trim(), description = description.trim()))
        }
    }

    fun deleteTask(taskId: String) {
        tasks.removeIf { it.id == taskId }
    }

    fun toggleTaskComplete(taskId: String) {
        val index = tasks.indexOfFirst { it.id == taskId }
        if (index != -1) {
            tasks[index] = tasks[index].copy(isCompleted = !tasks[index].isCompleted)
        }
    }

    fun getTaskById(taskId: String): Task? {
        return tasks.find { it.id == taskId }
    }
}
