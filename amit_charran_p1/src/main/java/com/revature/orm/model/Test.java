package com.revature.orm.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.Entity;
import com.revature.orm.annotations.Id;

import java.sql.Date;

@Entity(tableName= "test_table")
public class Test {
	
	@Id(columnName = "test_id")
	private int id;
	
	@Column(columnName = " test_field_1")
	private String testfield1;
	
	@Column(columnName = "test_field_2")
	private String testField2;

	@Column(columnName = "test_field_3")
	private int testField3;

	@Column(columnName = "test_field_4")
	private double testField4;

	@Column(columnName = "Test_field_5")
	private Date testField5;
	

}
