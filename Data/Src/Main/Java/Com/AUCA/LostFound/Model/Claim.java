package Com.AUCA.LostFound.Model;

public class Claim {
    private String id;
    private String itemId;
    private String userId;
    private String status;

    public Claim() {
    }

    public Claim(String id, String itemId, String userId, String status) {
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
