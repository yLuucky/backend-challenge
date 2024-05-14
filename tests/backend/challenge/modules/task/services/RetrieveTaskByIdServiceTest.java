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
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;
	private ICreateTaskService createTaskService;

	@Before
	public void init () {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
			 			retornado uma tarefa com o mesmo id informado.
		*/
		 TaskDTO taskDTO = Utils.newTaskDTO();
		 Task newTask = createTaskService.execute(taskDTO);

		 Task taskById = retrieveTaskByIdService.execute(newTask.getId());

		 assertNotNull(newTask);
		 assertEquals(taskById.getId(), newTask.getId());
	}

}
