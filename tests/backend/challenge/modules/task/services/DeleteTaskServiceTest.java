package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNull;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private IDeleteTaskService deleteTaskService;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		deleteTaskService = new DeleteTaskService(taskRepository, retrieveTaskByIdService);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() {
		final TaskDTO taskDTO = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(taskDTO);

		deleteTaskService.execute(newTask.getId());
		final Task task = retrieveTaskByIdService.execute(newTask.getId());
		assertNull(task);
	}




}