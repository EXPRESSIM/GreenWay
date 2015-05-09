package com.stardust.express.dao.implementations;

public class Selection {
	
	public enum Operand {
		AND(" and "), OR(" or "), EMPTY("");
		
	    private String op = "";
		
	    Operand(String op) {
			this.op = op;
		}
		
		public String getOperand() {
			return this.op;
		}
		
	}
	
	public enum Operator {
		EQUAL("="),NOT_EQUAL("!="),GREATER(">"),GEATER_EQUAL(">="),LESS("<"),LESS_EQUAL("<="),LIKE("%");
		
	    private String opt = "";
		
		Operator(String opt) {
			this.opt = opt;
		}
		
		public String getOperator() {
			return this.opt;
		}
		
	}
	
	final private Operand operand;
	final private Operator operator;
	final private String property;
	final private Object value;
	
	public Selection(final String p, final Operator opt, final Object v) {
		this(p, opt, v, Operand.EMPTY);
	}

	public Selection(final String p, final Operator opt, final Object v, final Operand op) {
		property = p;
		operator = opt;
		value = v;
		operand = op;
	}
	
	public Operand getOperand(){
		return this.operand;
	}
	
	public Operator getOperator(){
		return this.operator;
	}
	
	public String getProperty(){
		return this.property;
	}
	
	public Object getValue(){
		return this.value;
	}
}


