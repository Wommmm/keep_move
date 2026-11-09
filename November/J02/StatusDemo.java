package November.J02;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class StatusDemo {
    // 示例：接收一个状态字符串，返回它是否代表成功。
    static boolean isSuccess(String status) {
        return "success".equals(status);
    }

    static class TaskReport{
        int total;
        int failedCount;
        List<TaskResult> failedTasks;

        TaskReport(int total,int failedCount,List<TaskResult> failedTasks){
            this.total = total;
            this.failedCount = failedCount;
            this.failedTasks = failedTasks;
        }
        static TaskReport buildReport(List<TaskResult> tasks){ 
             // 调用 selectFailedTasks(tasks)，取得未成功任务列表。
             List<TaskResult> failedTasks = selectFailedTasks(tasks);
             int failedCount = failedTasks.size();
              // 创建并返回 TaskReport 对象。
              return new TaskReport(tasks.size(),failedCount,failedTasks);
        }
    }

    // 练习：success、error、timeout 返回 true，其余返回 false。
    static boolean isValidStatus(String status) {
        return TaskResult.isValidStatus(status);
    }

    static String describeStatus(String status)
    {
        if (!isValidStatus(status)){
            throw new IllegalArgumentException("无效的状态"+status);
        }
        if ("success".equals(status)){
            return "成功";
        } else if ("error".equals(status)){
            return "失败";
        } else {
            return "超时";
        }

    }
    static int countFailures(String[] statuses){
        return selectFailures(statuses).size();
    }

    static List<String> selectFailures(String[] statuses){
        List<String> selected = new ArrayList<>();
        for (String status : statuses){
            if (!isValidStatus(status)){
                throw new IllegalArgumentException("无效的状态"+status);
            }
            if ("error".equals(status)||"timeout".equals(status))
                selected.add(status);
        }
        return selected;
    }
    static Map<String, Integer> countByStatus(String[] statuses){
        Map<String, Integer> counts = new HashMap<>();
        counts.put("success",0 );
        counts.put("error",0);
        counts.put("timeout",0);
        for (String status : statuses){
            if (!isValidStatus(status)){
                throw new IllegalArgumentException("无效的状态"+status);
            }
                counts.put(status, counts.get(status)+1);
        }
        return counts;
    }
    static List<TaskResult> selectFailedTasks(List<TaskResult> tasks){
        List<TaskResult> selected = new ArrayList<>();
        for (TaskResult task : tasks){
            if (task.isFailure()){
                selected.add(task);
            }
        }
        return selected;
    }

    static void printReport(TaskReport report){
        System.out.println("总任务数：" + report.total);
        System.out.println("未成功数：" + report.failedCount);
         // 遍历 report.failedTasks，打印每个任务的 ID 和状态。
        for (TaskResult task : report.failedTasks){
            System.out.println(task.getId() + "：" + task.getStatus());
        }
    }

    public static void main(String[] args) {
        List<TaskResult> tasks = List.of(
            new TaskResult("A", "success"),
            new TaskResult("B", "error"),
            new TaskResult("C", "timeout")
        );
        TaskReport report = TaskReport.buildReport(tasks);
        printReport(report);
    }
}
