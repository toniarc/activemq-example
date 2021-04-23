package com.example.demo.challenge01;

public class SushiItem {

	private Integer seatId;
	private String roll;

	public SushiItem(Integer seatId, String roll) {
		this.seatId = seatId;
		this.roll = roll;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roll == null) ? 0 : roll.hashCode());
		result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SushiItem other = (SushiItem) obj;
		if (roll == null) {
			if (other.roll != null)
				return false;
		} else if (!roll.equals(other.roll))
			return false;
		if (seatId == null) {
			if (other.seatId != null)
				return false;
		} else if (!seatId.equals(other.seatId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SushiItem [seatId=" + seatId + ", roll=" + roll + "]";
	}
	
}
