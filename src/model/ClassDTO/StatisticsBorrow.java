package model.ClassDTO;

public class StatisticsBorrow {
	private int count;
	private String month;
	public StatisticsBorrow(int count, String month) {
		super();
		this.count = count;
		this.month = month;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
}
