package libraryapp.domain.model;

public enum MemberCategory {
	UNDERGRADUATE(3), 
	GRADUATE(2), 
	DEPARTMENT_HEAD(50),
	PROFESSOR(10);
	
	private final Integer bookLimit;
	
	MemberCategory(Integer bookLimit){
		this.bookLimit = bookLimit;
	}
	
	public Integer getBookLimit() {
		return this.bookLimit;
	}
}
