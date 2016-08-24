package ua.nure.romanenko.SummaryTask4.db.entity;

/**
 * Order entity.
 */
public class Order extends Entity {

	private static final long serialVersionUID = 5692708766041889396L;

	private int statusId;

	private Long userId;

	private Integer bill;

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Order [bill=" + bill + ", userId=" + userId + ", statusId="
				+ statusId + ", getId()=" + getId() + "]";
	}



}
