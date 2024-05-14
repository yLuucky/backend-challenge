package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exceptions.InvalidProgressException;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private ICreateTaskService createTaskService;
	private IUpdateTaskService updateTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private IUpdateTaskProgressService updateTaskProgressService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository, retrieveTaskByIdService);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository, retrieveTaskByIdService);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		final int expectedProgress = 85;

		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);

		final TaskProgressDTO taskToBeUpdated = TaskProgressDTO.create();

		taskToBeUpdated.setId(newTask.getId());
		taskToBeUpdated.setProgress(expectedProgress);


		updateTaskProgressService.execute(taskToBeUpdated);
		final Task updatedTask = retrieveTaskByIdService.execute(taskToBeUpdated.getId());

		assertEquals(updatedTask.getProgress(), expectedProgress);
		assertEquals(updatedTask.getStatus(), TaskStatus.PROGRESS);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		final int expectedProgress = 100;

		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);

		final TaskProgressDTO taskToBeUpdated = TaskProgressDTO.create();

		taskToBeUpdated.setId(newTask.getId());
		taskToBeUpdated.setProgress(expectedProgress);


		updateTaskProgressService.execute(taskToBeUpdated);
		final Task updatedTask = retrieveTaskByIdService.execute(taskToBeUpdated.getId());

		assertEquals(updatedTask.getProgress(), expectedProgress);
		assertEquals(updatedTask.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred() {
		final int expectedProgress = 85;

		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);

		final TaskProgressDTO taskToBeUpdated = TaskProgressDTO.create();

		taskToBeUpdated.setId(newTask.getId());
		taskToBeUpdated.setProgress(expectedProgress);


		updateTaskProgressService.execute(taskToBeUpdated);
		final Task updatedTask = retrieveTaskByIdService.execute(taskToBeUpdated.getId());

		assertEquals(updatedTask.getProgress(), expectedProgress);
		assertNotEquals(updatedTask.getStatus(), TaskStatus.COMPLETE);
	}

	@Test(expected = InvalidProgressException.class)
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred() {
		final int expectedProgress = 102;

		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);

		final TaskProgressDTO taskToBeUpdated = TaskProgressDTO.create();

		taskToBeUpdated.setId(newTask.getId());
		taskToBeUpdated.setProgress(expectedProgress);

		updateTaskProgressService.execute(taskToBeUpdated);
	}

}
