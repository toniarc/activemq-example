package com.example.demo.challenge01;

import java.util.ArrayList;
import java.util.List;

public class SushiOrder {

	private Integer seatId;
	private ArrayList<String> menuItemIds;

	public SushiOrder(Integer seatId, ArrayList<String> menuItemIds) {
		this.seatId = seatId;
		this.menuItemIds = menuItemIds;
	}

	public List<String> getMenuItemIds() {
		return this.menuItemIds;
	}

	public Integer getSeatId() {
		return this.seatId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SushiOrder other = (SushiOrder) obj;
		if (seatId == null) {
			if (other.seatId != null)
				return false;
		} else if (!seatId.equals(other.seatId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SushiOrder [seatId=" + seatId + ", menuItemIds=" + menuItemIds + "]";
	}

}
