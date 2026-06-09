package com.task.manager.taskmanagerapp.repository

import com.task.manager.taskmanagerapp.model.Task
import com.task.manager.taskmanagerapp.model.Priority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Task t WHERE t.priority = com.task.manager.taskmanagerapp.model.Priority.HIGH")
    fun getAllHighPriorityTask(): List<Task>
}
