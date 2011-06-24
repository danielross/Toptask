package model;

import java.io.Serializable;

import javax.persistence.Id;

public class Task implements Serializable {

	private static final long serialVersionUID = 7390103290165670099L;
	@Id
	private Long id;
	private String name;
	private String category;
	private Long pStart;
	private Long pEnd;
    private String due;
    private String description;

	public Task() {}

	public Long getId() {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getDueDate() {
	    return due;
	}

	public void setDueDate(String due) {
	    this.due = due;
	}

    public Long getPriorityStart() {
	    return pStart;
	}

	public void setPriorityStart (Long pStart) {
	    this.pStart = pStart;
	}

	public Long getPriorityEnd() {
	    return pEnd;
	}

	public void setPriorityEnd (Long pEnd) {
	    this.pEnd = pEnd;
	}
}
