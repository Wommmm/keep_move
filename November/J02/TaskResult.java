package November.J02;

public class TaskResult {
    // 外部只能通过公开方法访问，不能直接读取或赋值。
    private String id;
    private String status;

    public TaskResult(String id, String status) {
        if (id == null) {
            throw new IllegalArgumentException("无效的ID：" + id);
        }
        String cleanId = id.strip();
        if (cleanId.isEmpty()) {
            throw new IllegalArgumentException("无效的ID：" + id);
        }
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("无效的状态：" + status);
        }
        this.id = cleanId;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean isFailure() {
        return "error".equals(this.status) || "timeout".equals(this.status);
    }


    public static boolean isValidStatus(String status) {
        return "success".equals(status)
            || "error".equals(status)
            || "timeout".equals(status);
    }

        

    public void changeStatus(String newStatus) {
        // 核心练习：先校验，合法才修改；拒绝非法值时保留原状态。
        if (isValidStatus(newStatus)) {
            this.status = newStatus;
        }
        else {
            throw new IllegalArgumentException("无效的状态：" + newStatus);
         }
    }
}
