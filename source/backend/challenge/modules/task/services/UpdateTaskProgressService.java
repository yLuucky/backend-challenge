package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exceptions.InvalidProgressException;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Objects.isNull;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;
    private final IRetrieveTaskByIdService retrieveTaskByIdService;

    @Inject
    public UpdateTaskProgressService(final ITaskRepository taskRepository,
                                     final IRetrieveTaskByIdService retrieveTaskByIdService) {
        this.taskRepository = taskRepository;
        this.retrieveTaskByIdService = retrieveTaskByIdService;
    }

    @Override
    public Task execute(final TaskProgressDTO taskProgressDTO) {
        final Task task = retrieveTaskByIdService.execute(taskProgressDTO.getId());

        if (isNull(task)) return null;

        if (taskProgressDTO.getProgress() < 1 || taskProgressDTO.getProgress() > 100) {
            throw new InvalidProgressException();
        }

        final TaskStatus taskStatus = taskProgressDTO.getProgress() == 100 ? TaskStatus.COMPLETE : TaskStatus.PROGRESS;
        task.setStatus(taskStatus);
        task.setProgress(taskProgressDTO.getProgress());

        return taskRepository.update(task);
    }
}
