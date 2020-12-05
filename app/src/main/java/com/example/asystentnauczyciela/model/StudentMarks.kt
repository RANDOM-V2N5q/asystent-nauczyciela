package com.example.asystentnauczyciela.model

import androidx.room.Embedded

data class StudentMarks (
    @Embedded
    var student: Student,
    @Embedded
    var mark: Mark
)