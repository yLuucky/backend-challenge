package backend.challenge.modules.task.models;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Task {

	private Long id;
	private String title;
	private String description;
	private int progress;
	private TaskStatus status;
	private Date createdAt;

	public static Task fromDto(TaskDTO taskDTO) {
		Task task = new Task();
		task.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());

		return task;
	}

}
