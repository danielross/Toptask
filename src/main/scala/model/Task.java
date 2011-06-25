package net.danross.toptask.model;

import java.io.Serializable;

public class Task implements Serializable {

	private static final long serialVersionUID = 7390103290165670099L;
	
    private Long id;
    private Long owner;
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

     public Long getOwner () {
        return owner;
    }

    public void setOwner (Long owner) {
        this.owner = owner;
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
