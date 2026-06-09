package com.task.manager.taskmanagerapp.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class TaskRequest @JsonCreator constructor(
    @JsonProperty("title") @NotBlank(message = "Title cannot be blank") val title: String,
    @JsonProperty("description") val description: String? = null,
    @JsonProperty("priority") val priority: Priority? = null,
    @JsonProperty("completed") val completed: Boolean? = null
) {
    fun toTask(): Task {
        val task = Task(title = title, description = description)
        priority?.let { task.priority = it }
        completed?.let { task.completed = it }
        return task
    }
}
