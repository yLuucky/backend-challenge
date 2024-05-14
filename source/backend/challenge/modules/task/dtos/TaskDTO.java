package backend.challenge.modules.task.dtos;

import backend.challenge.modules.task.infra.http.views.TaskView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
public class TaskDTO {

	private String title;
	private String description;

	public static TaskDTO fromView(final TaskView taskView) {
		final TaskDTO task = new TaskDTO();
		task.setTitle(taskView.getTitle());
		task.setDescription(taskView.getDescription());

		return task;
	}

}
