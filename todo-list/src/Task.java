public class Task {
    private String[] task;
    private String taskName;
    private String description = "Sem descrição";
    private String status = "Não iniciado"; // pesquisar e substituir por ENUM

    public Task(String taskName) {
        this.taskName = taskName;
        this.description = description;
        this.status = status;
    }

    public Task(String taskName, String description, String status) {
        this.taskName = taskName;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nNome: " + taskName +
                "\nDescrição: " + description +
                "\nEstado: " + status +
                "\n--------------------------------------------------";
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
