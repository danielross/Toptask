package model;

import java.io.Serializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

import javax.persistence.Id;
import model.User;

public class Task implements Serializable {

	private static final long serialVersionUID = 7390103290165670099L;
	@Id
	private Long id;
    @Parent
    private Key<User> owner;
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

    public Key<User> getOwner () {
        return owner;
    }

    public void setOwner (Key<User> owner) {
        this.owner = new Key<User>(User.class, owner.getId());
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
