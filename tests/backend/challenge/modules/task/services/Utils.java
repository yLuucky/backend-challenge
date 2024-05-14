package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;

public class Utils {

    public static TaskDTO newTaskDTO() {
        TaskDTO taskDTO = TaskDTO.create();

        taskDTO.setTitle("Title Test");
        taskDTO.setDescription("Description Test");

        return taskDTO;
    }
}
