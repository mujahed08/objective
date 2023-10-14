package com.objective.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Menu {

	private String id;
	private String title;
	private Boolean isLeaf;
	private List<Menu> subGroup;
}
