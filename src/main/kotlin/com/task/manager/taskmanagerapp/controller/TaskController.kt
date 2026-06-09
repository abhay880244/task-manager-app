package com.task.manager.taskmanagerapp.controller

import com.task.manager.taskmanagerapp.model.TaskResponse
import com.task.manager.taskmanagerapp.model.TaskRequest
import com.task.manager.taskmanagerapp.service.TaskService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTasks(): List<TaskResponse> = taskService.getAllTasks().map { TaskResponse.fromTask(it) }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): TaskResponse = TaskResponse.fromTask(taskService.getTaskById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@Valid @RequestBody request: TaskRequest): TaskResponse = TaskResponse.fromTask(taskService.createTask(request))

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @Valid @RequestBody request: TaskRequest): TaskResponse =
        TaskResponse.fromTask(taskService.updateTask(id, request))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) = taskService.deleteTask(id)

    @GetMapping("/high-priority")
    fun getAllHighPriorityTask(): List<TaskResponse> = taskService.getAllHighPriorityTask().map { TaskResponse.fromTask(it) }
}
