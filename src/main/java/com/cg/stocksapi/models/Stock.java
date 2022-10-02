package com.cg.stocksapi.models;

import java.util.*;

import javax.persistence.*;

import org.springframework.lang.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="stock")
public class Stock {
	
	@Id
	// auto for mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Nullable
	private int quarter;
	@Nullable
	private String symbol;
	@Nullable
	// if time zone is not set to America/NY the time will be parsed incorrectly
	@JsonFormat( pattern = "MM/dd/yyyy", timezone = "America/New_York")
	private Date date;
	@Nullable
	private Double open;
	@Nullable
	private Double high;
	@Nullable
	private Double low;
	@Nullable
	private Double close;
	@Nullable
	private Double volume;
	@Nullable
	private Double percent_change_price;
	@Nullable
	private Double percent_change_volume_over_last_wk;
	@Nullable
	private Double previous_weeks_volume;
	@Nullable
	private Double next_weeks_open;
	@Nullable
	private Double next_weeks_close;
	@Nullable
	private Double percent_change_next_weeks_price;
	@Nullable
	private Double days_to_next_dividend;
	@Nullable
	private Double percent_return_next_dividend;
}
