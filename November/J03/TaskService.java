package November.J03;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TaskService {
    private Map<String, Task> tasks = new LinkedHashMap<>();

    public Task addTask(String id, String title) {
        Task task = new Task(id, title);
        String cleanId = task.getId();

        if (tasks.containsKey(cleanId)) {
            throw new IllegalArgumentException("任务 ID 已存在：" + cleanId);
        }

        tasks.put(cleanId, task);
        return task;
    }

    public Task findTask(String id) {
        String cleanId = Task.normalizeId(id);
        Task task = tasks.get(cleanId);

        if (task == null) {
            throw new NoSuchElementException("任务不存在：" + cleanId);
        }

        return task;
    }
}