package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Objects.isNull;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository,
							 final IRetrieveTaskByIdService retrieveTaskByIdService) {
		this.taskRepository = taskRepository;
        this.retrieveTaskByIdService = retrieveTaskByIdService;
    }

	@Override
	public void execute(final Long taskId) {
		Task deleteTask = retrieveTaskByIdService.execute(taskId);
		if (isNull(deleteTask)) {
			return;
		}

		taskRepository.delete(taskId);
	}

}
