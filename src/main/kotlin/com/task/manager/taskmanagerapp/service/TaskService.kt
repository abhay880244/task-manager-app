package com.task.manager.taskmanagerapp.service

import com.task.manager.taskmanagerapp.model.Task
import com.task.manager.taskmanagerapp.model.TaskRequest
import com.task.manager.taskmanagerapp.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun getAllTasks(): List<Task> = taskRepository.findAll()

    fun getTaskById(id: Long): Task =
        taskRepository.findById(id).orElseThrow { NoSuchElementException("Task not found with id: $id") }

    fun createTask(request: TaskRequest): Task = taskRepository.save(request.toTask())

    fun updateTask(id: Long, request: TaskRequest): Task {
        val existing = getTaskById(id)
        existing.title = request.title
        existing.description = request.description
        request.priority?.let { existing.priority = it }
        request.completed?.let { existing.completed = it }
        existing.updatedAt = LocalDateTime.now()
        return taskRepository.save(existing)
    }

    fun deleteTask(id: Long) = taskRepository.deleteById(id)

    fun getAllHighPriorityTask(): List<Task> = taskRepository.getAllHighPriorityTask()

}
