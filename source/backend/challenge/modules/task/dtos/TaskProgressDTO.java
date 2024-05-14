package backend.challenge.modules.task.dtos;

import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
public class TaskProgressDTO {

	private Long id;
	private int progress;

	public static TaskProgressDTO fromView(final TaskProgressView taskProgressView, final Long taskId) {
		final TaskProgressDTO taskProgress = new TaskProgressDTO();
		taskProgress.setId(taskId);
		taskProgress.setProgress(taskProgressView.getProgress());

		return taskProgress;
	}

}
