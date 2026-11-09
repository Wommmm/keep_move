package November.J03;

public class TaskApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();

        service.addTask(" A ", "学习 Java");
        service.addTask("B", "整理笔记");

        Task found = service.findTask(" A ");
        System.out.println(found.getId() + "：" + found.getTitle() + "：" + found.getStatus());
    }
}
