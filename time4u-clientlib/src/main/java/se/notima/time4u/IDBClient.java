package se.notima.time4u;

import java.util.List;
import java.util.Map;
import java.util.Vector;


import se.notima.time4u.entities.BillingRun;
import se.notima.time4u.entities.Billitems;
import se.notima.time4u.entities.Bills;
import se.notima.time4u.entities.ProjectCustomerMap;
import se.notima.time4u.entities.T4uDayinfos;
import se.notima.time4u.entities.T4uPersons;
import se.notima.time4u.entities.T4uProjects;
import se.notima.time4u.entities.T4uTasks;

public interface IDBClient {

    /**
     * Tests if database connection works
     * 
     * @return
     */
    public boolean testConnection();
    
    public void setDbCredentials(String user, String pwd);

    public void setDbUrl(String url);

    public void setDbDriver(String driverName);
    /**
     * Call this method to reset the entity manager (if database connection
     * settings have been changed)
     */
    public boolean resetEntityManager() throws Exception;
    
    /**
     * Return all sub projects for the given project
     * @param projectId
     * @return
     */
    public List<T4uProjects> getSubProjects(String projectId);
    
    /**
     * 
     * @param projectId
     * @param includeProject	If true the top project (projectId) is also included in the list.
     * @return
     */
    public List<T4uProjects> getSubProjects(String projectId, boolean includeProject);
    
    public Map<String, ProjectCustomerMap> getProjectCustomerMaps();

    /**
     * Return customer map for given project. If none exists, null is returned.
     * @param projectId
     * @return
     */
    public ProjectCustomerMap getProjectCustomerMap(String projectId);
    
    /**
     * Return customer map for bPartnerId
     */
    public ProjectCustomerMap getProductCustomerMapByBPartnerId(int bPartnerId);    

    /**
     * Removes a project customer map
     * 
     * @param	projectId
     */
    public void removeProjectCustomerMap(String projectId);    
    
    /**
     * Creates a project customer map
     *
     * @param projectId
     * @param bpartnerId
     * @param productId
     * @return
     */
    public ProjectCustomerMap createProjectCustomerMap(String projectId, int bpartnerId, String bpartnerName, int productId);

    /**
     * Recursive method called from getSubProjects;
     * @param children
     * @param project
     * @see     getSubProjects
     */
    public void readChildren(Vector<T4uProjects> children, T4uProjects project, boolean recursive);

    /**
     * Return a list of all persons
     */
    public List<T4uPersons> getPersons();

    public T4uPersons getPerson(String personId);
    
    /**
     * Return a list of all projects
     */
    public List<T4uProjects> getProjects();
    
    /**
     * Return a list of all root projects
     */
    public List<T4uProjects> getRootProjects();
    /**
     * Return a list of all tasks
     */
    public List<T4uTasks> getTasks();

    public T4uTasks getTask(String taskId);
    
    /**
     * Return a list of tasks id's belonging to a specified list of projects
     * @return
     */
    public List<String> getTaskIdsForProjects(List<T4uProjects> projects);
    
    public BillingRun getEmptyBillingRun();

    public BillingRun createNewBillingRun();

    public Bills createEmptyBill();

    /**
     * Creates an empty bill item for a work item. This is equvalent to setting
     * it as billed.
     *
     * @param workItemId
     */
    public Billitems createEmptyBillItem(String workItemId);

    /**
     * Return all day infos (one dayinfo per worked day and person) for a given
     * period.
     *
     * @param from
     * @param to
     * @return
     */
    public List<T4uDayinfos> getDayInfos(java.sql.Date from, java.sql.Date to);
    
    /**
     * Return a list of WorkSpecification for given project during specified period.
     *
     * @param projectId     If null all projects are included. Project id is the top project, all sub projects are
     *                      included.
     * @param personId      If null all persons are included.
     * @param from          Must be given
     * @param until         Must be given
     * @return
     */
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.sql.Date from, java.sql.Date until);
    
    /**
     * Return a list of WorkSpecification for given project during specified period.
     *
     * @param projectId     If null all projects are included. Project id is the top project, all sub projects are
     *                      included.
     * @param personId      If null all persons are included.
     * @param from          Must be given
     * @param until         Must be given
     * @param showBilled    If true, all items are visible. If false, only items not billed are shown.
     *
     *                      NOTE!! This method can only be used if the custom table BILLITEMS is present.
     * @return
     */
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.sql.Date from, java.sql.Date until, boolean showBilled);
    
    /**
     * Return a list of WorkSpecification for given project during specified period.
     *
     * @param projectId     If null all projects are included. Project id is the top project, all sub projects are
     *                      included.
     * @param personId      If null all persons are included.
     * @param taskId		If null all tasks are included
     * @param from          Must be given
     * @param until         Must be given
     * @param showBilled    If true, all items are visible. If false, only items not billed are shown.
     *
     *                      NOTE!! This method can only be used if the custom table BILLITEMS is present.
     * @return
     */
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.lang.String taskId, java.sql.Date from, java.sql.Date until, boolean showBilled);
    
    /**
     * @param projectId     Project to get worked time for.
     * @param since         From date
     * @param until         Until date
     * @return  Worked time for the given project and subprojects.
     */
    public long getWorkedTimeForProject(String projectId, java.sql.Date since, java.sql.Date until);
    
    
    public void startTransaction() throws Exception;
    
    public void commitTransaction();
    
    public void persist(Object obj);    
    

    public List<WorkSummary> getWorkSummaryForProject(String projectId, String personId, String taskId, java.sql.Date from, java.sql.Date until, boolean showBilled);

    public List<WorkSummary> getWorkSummaryForProjectNoTaskSpec(String projectId, String personId, java.sql.Date from, java.sql.Date until, boolean showBilled);
    
    /**
     * Get unbilled work for all projects
     * 
     * @return
     */
    public List<WorkSummary> getUnbilledWorkSummaryForAllProjects(java.sql.Date since, java.sql.Date until);
    
    public java.sql.Date getLimitTimeForProject(String projectId, String personId, String taskId, java.sql.Date since, java.sql.Date until, boolean first, boolean showBilled);    
	
}
