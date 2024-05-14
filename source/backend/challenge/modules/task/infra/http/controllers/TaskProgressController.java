package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.services.IUpdateTaskProgressService;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService) {
		this.updateTaskProgressService = updateTaskProgressService;
	}

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") final Long taskId, final TaskProgressView taskProgressView) {
		final TaskProgressDTO taskProgressDTO = TaskProgressDTO.fromView(taskProgressView, taskId);

		updateTaskProgressService.execute(taskProgressDTO);
		return DefaultResponse.ok();
	}
}
