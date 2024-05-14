package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;
	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
					  retornado um array com todas as tarefas que foram criadas até o momento.
		*/
		 TaskDTO taskDTO = Utils.newTaskDTO();
		 createTaskService.execute(taskDTO);
		 List<Task> tasks = retrieveAllTasksService.execute();

		assertFalse(tasks.isEmpty());
		assertNotNull(tasks.get(0));
	}

}