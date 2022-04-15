package com.study.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "ORDER_ITEM")
public class OrderItem {
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "item_id")
	private Long itemId;
	
	private int orderPrice;
	
	private int count;
}
