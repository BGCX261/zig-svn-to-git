package de.sophomore.zig.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class is used to represent available projects in the database.
 *
 * @author <a href="mailto:alexandre.koval@gmail.com">Alexandre Koval</a>
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "findProjectByName",
    		query = "select p from Project p where p.name = :name ")
//    ,@NamedQuery(name = "findCustomers",
//    	    query = "select customer from User customer where customer.name = :name ")
}) 

public class Project extends BaseObject implements Serializable {

    private static final long serialVersionUID = -6490479255596588603L;
    private Long id;
    private Set<User> customers = new HashSet<User>();
    private String name;
    private String description;
    private Date begin;
    private Date end;
    private Date lastupdated;

    /**
     *  Default constructor - creates a new instance with no values set.
     */
    public Project() {
		init();
	}

    /**
     *  Create a new instance and set the name.
     *  @param name name of the project.
     */
    public Project(final String name) {
        this.name = name;
        init();
    }

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

    @Column(nullable=false,length=120,unique=true)
    public String getName () {
        return this.name;
    }

    @Column(length = 4000)
	public String getDescription() {
		return this.description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Project)) {
			return false;
		}

		final Project project = (Project) o;

		return !(name != null ? !name.equals(project.name)
				: project.name != null);

	}

    /**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.name).toString();
	}

	public int compareTo(Object o) {
		return (equals(o) ? 0 : -1);
	}

    /**
	 * @param lastupdated
	 *            the lastupdated to set
	 */
	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	/**
	 * @return the lastupdated
	 */
	@Column(name = "lastupdated")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastupdated() {
		return lastupdated;
	}

	private void init() {
//		lastupdated = Calendar.getInstance().getTime();
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(Date begin) {
		this.begin = begin;
	}

	/**
	 * @return the begin
	 */
	@Column(name = "begin")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBegin() {
		return begin;
	}

    /**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * @return the end
	 */
	@Column(name = "end")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEnd() {
		return end;
	}

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name="project_user",
            joinColumns = { @JoinColumn( name="project_id") },
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    public Set<User> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<User> pCustomers) {
		this.customers = pCustomers;
	}

    /**
     * Convert project customers to LabelValue objects for convenience.
     * @return a list of LabelValue objects with customer information
     */
    @Transient
	public List<LabelValue> getCustomerList() {
		List<LabelValue> projectCustomers = new ArrayList<LabelValue>();

		if (this.customers != null) {
			for (User customer : customers) {
				// convert the project's customers to LabelValue Objects
				projectCustomers.add(new LabelValue(customer.getFullName(),
						customer.getFullName()));
			}
		}

		return projectCustomers;
	}

    @Transient
	public String getCustomersAsString() {
		String _ret = "";
		
//		Set<User> _customers = getCustomers();
//		
//		for (Iterator iterator = _customers.iterator(); iterator.hasNext();) {
//			User user = (User) iterator.next();
//			
//		}
		
		for (User customer : getCustomers()) {
			// convert the project's customers to LabelValue Objects
			_ret = customer.getFullName() + ", ";
		}
		
//    	for (int i = 0; i < getCustomerList().size(); i++) {
//    		_ret += getCustomerList().get(i).getValue() + " ,";	
//		}
		return _ret;
	}

    /**
     * Adds a customer for the project
     * @param customer the fully instantiated project
     */
    public void addCustomer(User customer) {
		getCustomers().add(customer);
	}
    
    @Transient
    public String getShortDescription() {
    	int _shortDescriptionLength = 72; 
		return (getDescription().length() < _shortDescriptionLength) ? getDescription() : getDescription().substring(0, _shortDescriptionLength - 1) + "...";
	}
}