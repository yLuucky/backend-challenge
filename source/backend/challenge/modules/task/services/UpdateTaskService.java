package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Objects.isNull;

@Singleton
public class UpdateTaskService implements IUpdateTaskService{

    private final ITaskRepository taskRepository;
    private final IRetrieveTaskByIdService retrieveTaskByIdService;

    @Inject
    public UpdateTaskService(final ITaskRepository taskRepository,
                             final IRetrieveTaskByIdService retrieveTaskByIdService) {
        this.taskRepository = taskRepository;
        this.retrieveTaskByIdService = retrieveTaskByIdService;
    }

    @Override
    public Task execute(final Task task) {
        final Task updateTask = retrieveTaskByIdService.execute(task.getId());

        if (isNull(updateTask)) {
            return null;
        }

        updateTask.setTitle(task.getTitle());
        updateTask.setDescription(task.getDescription());
        return taskRepository.update(updateTask);
    }
}
