package com.task.manager.taskmanagerapp.model

import java.time.LocalDateTime

data class TaskResponse(
    val id: Long?,
    val version: Long?,
    val title: String,
    val description: String?,
    val priority: Priority,
    val completed: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun fromTask(task: Task): TaskResponse = TaskResponse(
            id = task.id,
            version = task.version,
            title = task.title,
            description = task.description,
            priority = task.priority,
            completed = task.completed,
            createdAt = task.createdAt,
            updatedAt = task.updatedAt
        )
    }
}
