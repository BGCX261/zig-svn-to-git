package de.sophomore.zig.webapp.action;

import de.sophomore.zig.webapp.action.BasePageTestCase;
import de.sophomore.zig.service.GenericManager;
import de.sophomore.zig.model.Project;

public class ProjectListTest extends BasePageTestCase {
    private ProjectList bean;
    private GenericManager<Project, Long> projectManager;

    public void setProjectManager(GenericManager<Project, Long> projectManager) {
        this.projectManager = projectManager;
    }
        
    @Override @SuppressWarnings("unchecked")
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new ProjectList();
        bean.setProjectManager(projectManager);
        
        // add a test project to the database
        Project project = new Project();

        // enter all required fields
        project.setName("OnIjVoSzPzJvTeSmYlWoZqTmFsOyLbPvPhOlKaVnKaOdZnAeMnMdHvRzCpYrGeGjZpQbAcChBxLcOgRxAfZaMsMkIzZfIxJlWgXvEkMcOaXhYgDkFfWnLfMy");

        projectManager.save(project);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testSearch() throws Exception {
        assertTrue(bean.getProjects().size() >= 1);
        assertFalse(bean.hasErrors());
    }
}