package it.polito.tdp.countryborders.model;

public class Country {

	private int cCode;
	private String stateAbb;
	private String stateNme;

	public Country(){
		
		super();
		
	}
	
	public Country(int cCode, String stateAbb, String stateNme) {

		super();
		this.cCode = cCode;
		this.stateAbb = stateAbb;
		this.stateNme = stateNme;
	
	}

	/**
	 * @return the cCode
	 */
	public int getcCode() {
		return cCode;
	}

	/**
	 * @param cCode the cCode to set
	 */
	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	/**
	 * @return the stateAbb
	 */
	public String getStateAbb() {
		return stateAbb;
	}

	/**
	 * @param stateAbb the stateAbb to set
	 */
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	/**
	 * @return the stateNme
	 */
	public String getStateNme() {
		return stateNme;
	}

	/**
	 * @param stateNme the stateNme to set
	 */
	public void setStateNme(String stateNme) {
		this.stateNme = stateNme;
	}

}
