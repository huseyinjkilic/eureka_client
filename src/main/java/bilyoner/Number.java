package bilyoner;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Number {

	public int number;
	
	public int getNumber() {
		return number;
	}

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdDate;
	
	public Number() {}

	public Number(int number, Date createdDate) {
	    this.number = number;
	    this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Numbers [number=" + number + ", createdDate=" + createdDate + "]";
	}

}
