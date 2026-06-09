package com.task.manager.taskmanagerapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskManagerAppApplication

fun main(args: Array<String>) {
    runApplication<TaskManagerAppApplication>(*args)
}
