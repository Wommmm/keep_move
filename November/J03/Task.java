package November.J03;

/** 普通任务记录：与 J02 的任务执行结果是不同的模型。 */
public class Task {
    private String id;
    private String title;
    private String status;

    public Task(String id, String title) {
        this.id = normalizeId(id);
        if (title == null) {
            throw new IllegalArgumentException("标题不能为 null");
        }
        String cleanTitle = title.strip();
        if (cleanTitle.isEmpty() || cleanTitle.length() > 200) {
            throw new IllegalArgumentException("标题去空白后长度必须为 1～200");
        }
        this.title = cleanTitle;
        this.status = "pending";
    }

    // 新增和查询共用同一 ID 规则，避免保存时整理、查询时却漏掉。
    static String normalizeId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("任务 ID 不能为 null");
        }
        String cleanId = id.strip();
        if (cleanId.isEmpty() || cleanId.length() > 64) {
            throw new IllegalArgumentException("任务 ID 去空白后长度必须为 1～64");
        }
        return cleanId;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return this.status;
    }
    public void changeStatus(String newStatus){
        if(!"pending".equals(newStatus)&&!"done".equals(newStatus)&&!"cancelled".equals(newStatus)){
            throw new IllegalArgumentException("无效的状态：" + newStatus);
        }
        if (this.status.equals(newStatus)){
            return;
        }
        if(!"pending".equals(this.status)){
            throw new IllegalArgumentException("任务状态已结束，不能从"+this.status+"改为"+newStatus);
        }
    this.status = newStatus;
    }
}    
