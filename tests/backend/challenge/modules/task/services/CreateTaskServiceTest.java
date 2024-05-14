package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;
	private IRetrieveAllTasksService retrieveAllTasksService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		final TaskDTO taskDTO = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(taskDTO);
		retrieveAllTasksService.execute();

		assertNotNull(newTask);
		assertEquals(taskDTO.getTitle(), newTask.getTitle());
	}




}