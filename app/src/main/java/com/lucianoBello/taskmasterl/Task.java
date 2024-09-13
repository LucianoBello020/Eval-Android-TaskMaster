package com.lucianoBello.taskmasterl;

public class Task {
    private String name;
    private boolean completed;
    private String date; // Nuevo atributo para la fecha

    public Task(String name, boolean completed, String date) {
        this.name = name;
        this.completed = completed;
        this.date = date; // Inicializar la fecha
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDate() {
        return date; // Método para obtener la fecha
    }

    public void setDate(String date) {
        this.date = date; // Método para establecer la fecha
    }
}