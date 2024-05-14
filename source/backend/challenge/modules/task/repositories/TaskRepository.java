package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TaskRepository implements ITaskRepository {

	private final static List<Task> tasks = new ArrayList<>();

	@Override
	public Task index(final Long taskId) {
		return tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst().orElse(null);
	}

	@Override
	public List<Task> show() {
		return tasks;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		final Task task = Task.fromDto(taskDTO);
		tasks.add(task);

		return task;
	}

	@Override
	public Task update(final Task task) {
		final Task taskToUpdate = this.index(task.getId());
		taskToUpdate.setTitle(task.getTitle());
		taskToUpdate.setDescription(task.getDescription());
		taskToUpdate.setProgress(task.getProgress());
		taskToUpdate.setStatus(task.getStatus());

		return taskToUpdate;
	}

	@Override
	public void delete(final Long taskId) {
		final Task taskToDelete = this.index(taskId);
		tasks.remove(taskToDelete);
	}

}
