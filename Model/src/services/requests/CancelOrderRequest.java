package services.requests;

public class CancelOrderRequest extends RequestBase {
    private String userId;
    private int orderId;

    public CancelOrderRequest(String userId, int orderId)
    {
        this.userId = userId;
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;

        }
    }