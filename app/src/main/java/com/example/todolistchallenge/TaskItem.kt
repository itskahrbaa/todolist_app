package com.example.todolistchallenge

import java.util.UUID

class TaskItem(
    var title: String,
    var desc: String,
    var id : UUID = UUID.randomUUID()
) {

}