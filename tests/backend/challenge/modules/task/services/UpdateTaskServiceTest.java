package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.controllers.TaskController;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;
	private ICreateTaskService createTaskService;
	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private TaskController taskController;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		updateTaskService = new UpdateTaskService(taskRepository, retrieveTaskByIdService);
		IRetrieveAllTasksService retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		IDeleteTaskService deleteTaskService = new DeleteTaskService(taskRepository, retrieveTaskByIdService);
		taskController = new TaskController(createTaskService, deleteTaskService, updateTaskService,
				retrieveAllTasksService, retrieveTaskByIdService);

	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		final String expectedName = "New Name";
		final String expectedDescription = "New Description";

		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);

		newTask.setTitle(expectedName);
		newTask.setDescription(expectedDescription);

		updateTaskService.execute(newTask);
		final Task updatedTask = retrieveTaskByIdService.execute(newTask.getId());

		assertEquals(updatedTask.getTitle(), expectedName);
		assertEquals(updatedTask.getDescription(), expectedDescription);
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		/*
			HELPME: Teste palha
			TODO: Para que esse teste passe, você deve validar na sua rota de update se
			 			o id da tarefa enviada pela url existe ou não. Caso não exista, retornar um erro com status 400.
		*/
		Response httpStatus = taskController.index(1234L);
		assertEquals(httpStatus.statusCode(),404);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		final TaskDTO task = Utils.newTaskDTO();
		final Task newTask = createTaskService.execute(task);
		final TaskStatus initialStatus = newTask.getStatus();

		final Task taskToBeUpdated = new Task();

		taskToBeUpdated.setId(newTask.getId());
		taskToBeUpdated.setTitle(newTask.getTitle());
		taskToBeUpdated.setDescription(newTask.getDescription());
		taskToBeUpdated.setStatus(TaskStatus.COMPLETE);


		updateTaskService.execute(taskToBeUpdated);
		final Task updatedTask = retrieveTaskByIdService.execute(taskToBeUpdated.getId());

		assertEquals(updatedTask.getStatus(), initialStatus);
	}


}