package com.example.healthcheck.util

enum class Event {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_EVENT
}

fun String?.toEvent(): Event {
    return when {
        this == "ADD" -> {
            Event.ADD
        }
        this == "UPDATE" -> {
            Event.UPDATE
        }
        this == "DELETE" -> {
            Event.DELETE
        }
        this == "DELETE_ALL" -> {
            Event.DELETE_ALL
        }
        this == "UNDO" -> {
            Event.UNDO
        }
        else -> {
            Event.NO_EVENT
        }
    }
}