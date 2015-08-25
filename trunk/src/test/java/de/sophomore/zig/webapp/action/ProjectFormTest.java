package de.sophomore.zig.webapp.action;

import de.sophomore.zig.service.GenericManager;
import de.sophomore.zig.model.Project;
import de.sophomore.zig.webapp.action.BasePageTestCase;

public class ProjectFormTest extends BasePageTestCase {
    private ProjectForm bean;
    private GenericManager<Project, Long> projectManager;
        
    public void setProjectManager(GenericManager<Project, Long> projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        bean = new ProjectForm();
        bean.setProjectManager(projectManager);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        bean = null;
    }

    public void testAdd() throws Exception {
        Project project = new Project();

        // enter all required fields
        project.setName("" + Math.random());
        bean.setProject(project);

        assertEquals("list", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getProject());
        assertFalse(bean.hasErrors());
    }

    public void testSave() {
        log.debug("testing save...");
        bean.setId(-1L);

        assertEquals("edit", bean.edit());
        assertNotNull(bean.getProject());
        Project project = bean.getProject();

        // update required fields
        project.setName("YtYuIdClYvLfRxScYcLwHzEpXvEcTlQoEoHqSxUsOvKkIzTaApFjKyOiQtHoFuQuNbOfZwEtSbJzYeSsKmMyKwMaNiZnIyReNiAgFpMpOhFcWyDuUgAwWrRy");
        bean.setProject(project);

        assertEquals("edit", bean.save());
        assertFalse(bean.hasErrors());
    }

    public void testRemove() throws Exception {
        log.debug("testing remove...");
        Project project = new Project();
        project.setId(-2L);
        bean.setProject(project);

        assertEquals("list", bean.delete());
        assertFalse(bean.hasErrors());
    }
}