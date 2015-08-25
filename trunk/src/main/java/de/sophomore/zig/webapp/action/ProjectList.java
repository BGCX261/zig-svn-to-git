package de.sophomore.zig.webapp.action;

import java.io.Serializable;
import java.util.List;

import de.sophomore.zig.webapp.action.BasePage;
import de.sophomore.zig.model.Project;
import de.sophomore.zig.service.GenericManager;

public class ProjectList extends BasePage implements Serializable {

	private static final long serialVersionUID = 795107302975821518L;
	private GenericManager<Project, Long> projectManager;

    public void setProjectManager(GenericManager<Project, Long> projectManager) {
        this.projectManager = projectManager;
    }

    public ProjectList() {
        setSortColumn("id"); // sets the default sort column
    }

    public List<Project> getProjects() {
        return sort(projectManager.getAll());
    }
}
