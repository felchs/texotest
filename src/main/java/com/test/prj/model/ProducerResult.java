package com.test.prj.model;

public class ProducerResult {

	private Producer[] min;
	
	private Producer[] max;
	
	public ProducerResult() {
	}
	
	public ProducerResult(Producer[] min, Producer[] max) {
		super();
		this.min = min;
		this.max = max;
	}

	public Producer[] getMin() {
		return min;
	}

	public void setMin(Producer[] min) {
		this.min = min;
	}

	public Producer[] getMax() {
		return max;
	}

	public void setMax(Producer[] max) {
		this.max = max;
	}


}
