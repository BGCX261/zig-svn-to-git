package de.sophomore.zig.webapp.action;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import de.sophomore.zig.model.Project;
import de.sophomore.zig.service.GenericManager;

public class ProjectForm extends BasePage implements Serializable {

	private static final long serialVersionUID = -3483235894060367145L;
	private GenericManager<Project, Long> projectManager;
    private Project project = new Project();
    private Long id;

    public void setProjectManager(GenericManager<Project, Long> projectManager) {
        this.projectManager = projectManager;
    }

    public GenericManager<Project, Long> getProjectManager() {
        return projectManager;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String delete() {
        projectManager.remove(project.getId());
        addMessage("project.deleted");

        return "list";
    }

    public String edit() {
        // Comparison to zero (vs. null) is required with MyFaces 1.2.2, not with previous versions
        if (id != null && id != 0) {
            project = projectManager.get(id);
        } else {
            project = new Project();
        }

        return "edit";
    }

    public String save() {
        boolean isNew = (project.getId() == null || project.getId() == 0);
        
        project.setLastupdated(Calendar.getInstance().getTime());

        projectManager.save(project);

        String key = (isNew) ? "project.added" : "project.updated";
        addMessage(key);

        if (isNew) {
            return "list";
        } else {
            return "edit";
        }
    }
    
    public void descriptionValidator(FacesContext facesContext, UIComponent uiComponent, Object newValue) {
    	if (newValue instanceof String) {
    		if (((String) newValue).length() > 4000) {
    			System.out.println("error");	
    		}
    	}
    }
    
} 