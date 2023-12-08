/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.notima.time4u;

import se.notima.time4u.entities.*;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Daniel Tamm
 */
public class DBClient implements IDBClient {

    private Map         m_emProperties = new TreeMap();
    private EntityManagerFactory emf;
    public EntityManager em;
    private String PERSISTENCE_UNIT_NAME = "time4u-clientlibPU2";
    private EntityTransaction m_et;
    private boolean m_isDerby;
    private boolean m_isMysql;

    private boolean initEntityManager() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, m_emProperties);
        }
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return(emf.isOpen());
    }

    /**
     * Tests if database connection works
     * 
     * @return
     */
    public boolean testConnection() {
    	boolean result = false;
    	try {
    		result = initEntityManager();
    	} catch (Exception ee) {
    		ee.printStackTrace();
    	}
    	return(result);
    }
    
    public void setDbCredentials(String user, String pwd) {
        m_emProperties.put("hibernate.connection.username", user);
        m_emProperties.put("hibernate.connection.password", pwd);
        
    }

    public void setDbUrl(String url) {
    	m_emProperties.put("hibernate.connection.url", url);
    }

    public void setDbDriver(String driverName) {
    	m_emProperties.put("hibernate.connection.driver_class", driverName);
        if (driverName.contains("mysql")) {
        	m_isMysql = true;
        	m_isDerby = false;
        	m_emProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        }
        if (driverName.contains("derby")) {
        	m_isDerby = true;
        	m_isMysql = false;
        	m_emProperties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        }
    }

    /**
     * Call this method to reset the entity manager (if database connection
     * settings have been changed)
     */
    public boolean resetEntityManager() throws Exception {
        if (em!=null) {
            em.close();
        }
        if (emf!=null) {
            emf.close();
        }
        return(initEntityManager());
    }

    public List<T4uProjects> getSubProjects(String projectId) {
    	return getSubProjects(projectId, false);
    }
    
    /**
     * Return all sub projects for the given project
     * @param projectId
     * @return
     */
    public List<T4uProjects> getSubProjects(String projectId, boolean includeProject) {
        initEntityManager();
        Vector<T4uProjects> children;
        T4uProjects top = null;
        if (projectId==null) {
            Query q = em.createNamedQuery("T4uProjects.findAll");
            children = new Vector<T4uProjects>(q.getResultList());
        } else {
            // First read the project tree
            Query q = em.createNamedQuery("T4uProjects.findById");
            q.setParameter("id", projectId);
            top = (T4uProjects) q.getSingleResult();
            children = new Vector<T4uProjects>();
            if (top != null) {
                // Find children
                readChildren(children, top, true);
            }
        }
        if (includeProject && top!=null)
        	children.add(0, top);
        return (children);
    }

    public Map<String, ProjectCustomerMap> getProjectCustomerMaps() {
        initEntityManager();
        Query q = em.createNamedQuery("ProjectCustomerMap.findAll");
        List<ProjectCustomerMap> maps = q.getResultList();
        Map<String, ProjectCustomerMap> result = new TreeMap<String, ProjectCustomerMap>();
        ProjectCustomerMap m;
        for (Iterator<ProjectCustomerMap> it = maps.iterator(); it.hasNext(); ) {
            m = it.next();
            result.put(m.getProjectCustomerMapPK().getProjectId(), m);
        }
        return(result);
    }

    /**
     * Return customer map for given project. If none exists, null is returned.
     * @param projectId
     * @return
     */
    public ProjectCustomerMap getProjectCustomerMap(String projectId) {
        initEntityManager();
        Query q = em.createNamedQuery("ProjectCustomerMap.findByProjectId");
        q.setParameter("projectId", projectId);
        List<ProjectCustomerMap> maps = q.getResultList();
        if (maps.isEmpty())
            return null;
        return(maps.get(0));
    }

    
    /**
     * Return customer map for bPartnerId
     */
    public ProjectCustomerMap getProductCustomerMapByBPartnerId(int bPartnerId) {
    	initEntityManager();
    	Query q = em.createNamedQuery("ProjectCustomerMap.findByBPartnerId");
        q.setParameter("bPartnerId", bPartnerId);
        List<ProjectCustomerMap> maps = q.getResultList();
        if (maps.isEmpty())
            return null;
        return(maps.get(0));
    }
    
    /**
     * Removes a project customer map
     * 
     * @param	projectId
     */
    public void removeProjectCustomerMap(String projectId) {
    	initEntityManager();
    	ProjectCustomerMap map = getProjectCustomerMap(projectId);
    	if (map!=null) {
    		em.remove(map);
    	}
    }
    
    /**
     * Creates a project customer map
     *
     * @param projectId
     * @param bpartnerId
     * @param productId
     * @return
     */
    public ProjectCustomerMap createProjectCustomerMap(String projectId, int bpartnerId, String bpartnerName, int productId) {
        initEntityManager();
        ProjectCustomerMap map = new ProjectCustomerMap();
        ProjectCustomerMapPK mapPK = new ProjectCustomerMapPK();
        mapPK.setProjectId(projectId);
        mapPK.setBPartnerId(bpartnerId);
        map.setBpartnerName(bpartnerName);
        map.setProjectCustomerMapPK(mapPK);
        map.setCProductId(productId);
        em.getTransaction().begin();
        em.persist(map);
        em.getTransaction().commit();
        return(map);
    }

    /**
     * Recursive method called from getSubProjects;
     * @param children
     * @param project
     * @see     getSubProjects
     */
    public void readChildren(Vector<T4uProjects> children, T4uProjects project, boolean recursive) {
        Query q = em.createNamedQuery("T4uProjects.findByParentId");
        q.setParameter("parentId", project);
        List<T4uProjects> kids = q.getResultList();
        if (kids.isEmpty()) {
            return;
        }
        T4uProjects p;
        for (Iterator<T4uProjects> it = kids.iterator(); it.hasNext();) {
            p = it.next();
            children.add(p);
            // Read the children of the child
            if (recursive) readChildren(children, p, true);
        }
    }

    /**
     * Return a list of all persons
     */
    public List<T4uPersons> getPersons() {
        initEntityManager();
        Query q = em.createNamedQuery("T4uPersons.findAllActive");
        q.setParameter("active", true);
        q.setParameter("deleted", false);
        List<T4uPersons> result = q.getResultList();
        return(result);
    }

    /**
     * Return a list of all projects
     */
    public List<T4uProjects> getProjects() {
        initEntityManager();
        Query q = em.createNamedQuery("T4uProjects.findAllActive");
        q.setParameter("active", true);
        q.setParameter("deleted", false);
        List<T4uProjects> result = q.getResultList();
        return(result);
    }

    /**
     * Return a list of all root projects
     */
    public List<T4uProjects> getRootProjects() {
        initEntityManager();
        Query q = em.createNamedQuery("T4uProjects.findRootProjects");
        q.setParameter("active", true);
        q.setParameter("deleted", false);
        List<T4uProjects> result = q.getResultList();
        return(result);
    }

    /**
     * Return a list of all tasks
     */
    public List<T4uTasks> getTasks() {
        initEntityManager();
        Query q = em.createNamedQuery("T4uTasks.findAll");
        List<T4uTasks> result = q.getResultList();
        return(result);
    }

    public T4uTasks getTask(String taskId) {
    	initEntityManager();
    	Query q = em.createNamedQuery("T4uTasks.findById");
    	q.setParameter("id", taskId);
        List<T4uTasks> tasks = q.getResultList();
        if (tasks.isEmpty())
            return null;
        return(tasks.get(0));
    }
    
    public T4uPersons getPerson(String personId) {
    	initEntityManager();
    	Query q = em.createNamedQuery("T4uPersons.findById");
    	q.setParameter("id", personId);
        List<T4uPersons> persons = q.getResultList();
        if (persons.isEmpty())
            return null;
        return(persons.get(0));
    }
    
    /**
     * Return a list of tasks id's belonging to a specified list of projects
     * @return
     */
    public List<String> getTaskIdsForProjects(List<T4uProjects> projects) {
    	if (projects.size()==0) return(new Vector<String>());
    	initEntityManager();
    	StringBuffer query = new StringBuffer("select id from T4U_TASKS where project_id in (");
    	for (T4uProjects project : projects) {
    		query.append("'" + project.getId() + "',");
    	}
    	// Replace the last "," with a parantesis
    	query.replace(query.length()-1, query.length(), ")");
    	Query q = em.createNativeQuery(query.toString());
    	List<String> rows = (List<String>)q.getResultList();
    	List<String> result = new Vector<String>();
    	for (String row : rows) {
    		result.add(row);
    	}
    	return(result);
    }
    
    public BillingRun getEmptyBillingRun() {
        initEntityManager();
        Query q = em.createNamedQuery("BillingRun.findByBillingRunId");
        q.setParameter("billingRunId", Integer.valueOf(0));
        BillingRun r = null;
        try {
        	r = (BillingRun)q.getSingleResult();
        } catch (javax.persistence.NoResultException nre) {}
        if (r==null) {
            r = new BillingRun();
            r.setBillingRunId(0);
            r.setBillDate(null);
//            em.persist(r);
        }
        return(r);
    }

    public BillingRun createNewBillingRun() {
        initEntityManager();
        String query = "select max(billing_run_id) from BILLINGRUN";
        Query q = em.createNativeQuery(query);
        Integer max = (Integer)q.getSingleResult();
        if (max==null) {
            max = new Integer(1);
        }
        BillingRun r = new BillingRun();
        r.setBillingRunId(max);
        r.setBillDate(java.util.Calendar.getInstance().getTime());
        return(r);
    }

    public Bills createEmptyBill() {
        initEntityManager();
        Query q = em.createNamedQuery("Bills.findByBillId");
        q.setParameter("billId", Integer.valueOf(0));
        Bills r = null;
        try {
        	r = (Bills)q.getSingleResult();
        } catch (javax.persistence.NoResultException nre) {}
        if (r==null) {
            r = new Bills();
            r.setBillId(0);
            // em.persist(r);
        }
        return(r);
    }

    /**
     * Creates an empty bill item for a work item. This is equvalent to setting
     * it as billed.
     *
     * @param workItemId
     */
    public Billitems createEmptyBillItem(String workItemId) {
        Billitems bi = new Billitems();
        bi.setBillingRunId(0);
        bi.setMultiplier(1.0d);
        bi.setWorkitemId(workItemId);
        bi.setBillId(0);
        em.persist(bi);
        return(bi);
    }

    /**
     * Return all day infos (one dayinfo per worked day and person) for a given
     * period.
     *
     * @param from
     * @param to
     * @return
     */
    public List<T4uDayinfos> getDayInfos(java.sql.Date from, java.sql.Date to) {
        initEntityManager();
        Query q = em.createNamedQuery("T4uDayinfos.findInPeriod");
        q.setParameter("from", from);
        q.setParameter("to", to);
        List<T4uDayinfos> result = q.getResultList();
        return (result);
    }

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
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.sql.Date from, java.sql.Date until) {
        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(from, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId);
        // Add main project to the list (if any)
        if (projectId!=null) {
            T4uProjects mainProject = (T4uProjects)em.createNamedQuery("T4uProjects.findById").setParameter("id", projectId).getSingleResult();
            sProjects.add(mainProject);
        }

        // Create query
        StringBuffer query = new StringBuffer(
                "select T4U_WORKITEMS.id, T4U_DAYINFOS.person_id, T4U_WORKITEMS.dayinfo_id, T4U_DAYINFOS.daydate, wBegin," +
                "wEnd, T4U_WORKITEMS.project_id, T4U_PROJECTS.name, task_id, todo_id, wComment " +
                "from T4U_WORKITEMS " +
                "left join T4U_DAYINFOS on (T4U_WORKITEMS.dayinfo_id=T4U_DAYINFOS.id) " +
                "left join T4U_PROJECTS on (T4U_WORKITEMS.project_id=T4U_PROJECTS.id) " +
                "where T4U_WORKITEMS.dayinfo_id in (");
        for (int i = 0; i < days.size(); i++) {
            query.append("'" + days.get(i).getId() + "'");
            // Append comma as long as this is not the last day info
            if (i < (days.size() - 1)) {
                query.append(",");
            }
        }
        query.append(") ");
        if (projectId!=null) {
            query.append(" and project_id in (");
            for (int i=0; i<sProjects.size(); i++) {
                query.append("'" + sProjects.get(i).getId() + "'");
                // Append comma as long as this is not the last project
                if (i<(sProjects.size()-1)) {
                    query.append(",");
                }
            }
            query.append(")");
        }
        if (personId!=null) {
            query.append(" and T4U_DAYINFOS.person_id=? ");
        }
        query.append(" order by T4U_DAYINFOS.daydate, T4U_WORKITEMS.project_id");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        if (personId!=null) {
            q.setParameter(1, personId);
        }
        List list = q.getResultList();
        List result = new Vector<WorkSpecification>();
        WorkSpecification ws;
        for (int i=0; i<list.size(); i++) {
            ws = new WorkSpecification((Vector)list.get(i));
            result.add(ws);
        }
        return(result);
    }

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
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.sql.Date from, java.sql.Date until, boolean showBilled) {
    	return(getWorkSpecificationForProject(projectId, personId, null, from, until, showBilled));
    }

    
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
    public java.util.List<WorkSpecification> getWorkSpecificationForProject(String projectId,java.lang.String personId, java.lang.String taskId, java.sql.Date from, java.sql.Date until, boolean showBilled) {
    	
        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(from, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId);
        // Add main project to the list (if any)
        if (projectId!=null) {
            T4uProjects mainProject = (T4uProjects)em.createNamedQuery("T4uProjects.findById").setParameter("id", projectId).getSingleResult();
            sProjects.add(mainProject);
        }

        // Create query
        StringBuffer query = new StringBuffer(
                "select T4U_WORKITEMS.id, T4U_DAYINFOS.person_id, T4U_WORKITEMS.dayinfo_id, T4U_DAYINFOS.daydate, wBegin," +
                "wEnd, T4U_WORKITEMS.project_id, T4U_PROJECTS.name, task_id, todo_id, wComment, bill_id, billing_run_id, " +
                "multiplier from T4U_WORKITEMS " +
                "left join T4U_DAYINFOS on (T4U_WORKITEMS.dayinfo_id=T4U_DAYINFOS.id) " +
                "left join T4U_PROJECTS on (T4U_WORKITEMS.project_id=T4U_PROJECTS.id) " +
                "left join BILLITEMS on (T4U_WORKITEMS.id=BILLITEMS.workitem_id) " +
                "where T4U_WORKITEMS.dayinfo_id in (");
        for (int i = 0; i < days.size(); i++) {
            query.append("'" + days.get(i).getId() + "'");
            // Append comma as long as this is not the last day info
            if (i < (days.size() - 1)) {
                query.append(",");
            }
        }
        query.append(") ");
        if (projectId!=null) {
            query.append(" and project_id in (");
            for (int i=0; i<sProjects.size(); i++) {
                query.append("'" + sProjects.get(i).getId() + "'");
                // Append comma as long as this is not the last project
                if (i<(sProjects.size()-1)) {
                    query.append(",");
                }
            }
            query.append(")");
        }
        if (personId!=null) {
            query.append(" and T4U_DAYINFOS.person_id=? ");
        }
        if (taskId!=null) {
        	query.append(" and T4U_WORKITEMS.task_id=? ");
        }
        if (!showBilled) {
            query.append(" and bill_id is null ");
        }
        query.append(" order by T4U_DAYINFOS.daydate, T4U_WORKITEMS.project_id");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        int c=1;
        if (personId!=null) {
            q.setParameter(c++, personId);
        }
        if (taskId!=null) {
        	q.setParameter(c++, taskId);
        }
        List list = q.getResultList();
        List result = new Vector<WorkSpecification>();
        WorkSpecification ws;
        for (int i=0; i<list.size(); i++) {
            ws = new WorkSpecification((Object[])list.get(i));
            result.add(ws);
        }
        return(result);
    }

    /**
     * Get unbilled work for all projects
     * 
     * @return
     */
    public List<WorkSummary> getUnbilledWorkSummaryForAllProjects(java.sql.Date since, java.sql.Date until) {

        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(null);

        // Create query
        StringBuffer query = new StringBuffer(
                "select sum(wEnd-wBegin)," +
                "T4U_WORKITEMS.project_id, T4U_PROJECTS.name " +
                "from T4U_WORKITEMS " +
                "left join T4U_PROJECTS on (T4U_WORKITEMS.project_id=T4U_PROJECTS.id) " +
                "LEFT JOIN T4U_DAYINFOS ON (T4U_WORKITEMS.DAYINFO_ID=T4U_DAYINFOS.ID) " +
                "left join BILLITEMS on (T4U_WORKITEMS.id=BILLITEMS.workitem_id) " +
                "where ");
        if (since!=null) {
        	query.append("DAYDATE>=? AND ");
        }
        if (until!=null) {
        	query.append("DAYDATE<=? AND ");
        }
        query.append(" bill_id is null ");
        query.append(" group by T4U_WORKITEMS.project_id, T4U_PROJECTS.name");
        query.append(" order by 1 desc");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        int c=1;
        if (since!=null) {
        	q.setParameter(c++, since);
        }
        if (until!=null) {
        	q.setParameter(c++, until);
        }
        List list = q.getResultList();
        List result = new Vector<WorkSummary>();
        WorkSummary ws;
        Object[] row;
        
        for (int i=0; i<list.size(); i++) {
            ws = new WorkSummary();
            row = (Object[])list.get(i);
            
            if (row[0] instanceof Long)
            	ws.setDuration((Long)row[0]);
            if (row[0] instanceof BigDecimal) 
            	ws.setDuration(((BigDecimal)row[0]).longValue());
            
            ws.setProjectId(row[1].toString());
            ws.setProjectName(row[2].toString());
            result.add(ws);
        }
        return(result);
    	
    }
    
    public List<WorkSummary> getWorkSummaryForProjectNoTaskSpec(String projectId, String personId, java.sql.Date from, java.sql.Date until, boolean showBilled) {

        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(from, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId);
        // Add main project to the list (if any)
        if (projectId!=null) {
            T4uProjects mainProject = (T4uProjects)em.createNamedQuery("T4uProjects.findById").setParameter("id", projectId).getSingleResult();
            sProjects.add(mainProject);
        }

        // Create query
        StringBuffer query = new StringBuffer(
                "select T4U_DAYINFOS.person_id, sum(wEnd-wBegin)," +
                "T4U_WORKITEMS.project_id, T4U_PROJECTS.name " +
                "from T4U_WORKITEMS " +
                "left join T4U_DAYINFOS on (T4U_WORKITEMS.dayinfo_id=T4U_DAYINFOS.id) " +
                "left join T4U_PROJECTS on (T4U_WORKITEMS.project_id=T4U_PROJECTS.id) " +
                "left join BILLITEMS on (T4U_WORKITEMS.id=BILLITEMS.workitem_id) " +
                "where T4U_WORKITEMS.dayinfo_id in (");
        for (int i = 0; i < days.size(); i++) {
            query.append("'" + days.get(i).getId() + "'");
            // Append comma as long as this is not the last day info
            if (i < (days.size() - 1)) {
                query.append(",");
            }
        }
        query.append(") ");
        if (projectId!=null) {
            query.append(" and project_id in (");
            for (int i=0; i<sProjects.size(); i++) {
                query.append("'" + sProjects.get(i).getId() + "'");
                // Append comma as long as this is not the last project
                if (i<(sProjects.size()-1)) {
                    query.append(",");
                }
            }
            query.append(")");
        }
        if (personId!=null) {
            query.append(" and T4U_DAYINFOS.person_id=? ");
        }
        if (!showBilled) {
            query.append(" and bill_id is null ");
        }
        query.append(" group by T4U_DAYINFOS.person_id, T4U_WORKITEMS.project_id, T4U_PROJECTS.name");
        query.append(" order by T4U_WORKITEMS.project_id");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        int c=1;
        if (personId!=null) {
            q.setParameter(c++, personId);
        }
        List list = q.getResultList();
        List result = new Vector<WorkSummary>();
        WorkSummary ws;
        for (int i=0; i<list.size(); i++) {
            ws = new WorkSummary((Object[])list.get(i));
            result.add(ws);
        }
        return(result);
    	
    }
    
    public List<WorkSummary> getWorkSummaryForProject(String projectId, String personId, String taskId, java.sql.Date from, java.sql.Date until, boolean showBilled) {

        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(from, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId);
        // Add main project to the list (if any)
        if (projectId!=null) {
            T4uProjects mainProject = (T4uProjects)em.createNamedQuery("T4uProjects.findById").setParameter("id", projectId).getSingleResult();
            sProjects.add(mainProject);
        }

        // Create query
        StringBuffer query = new StringBuffer(
                "select T4U_DAYINFOS.person_id, sum(wEnd-wBegin)," +
                "T4U_WORKITEMS.project_id, T4U_PROJECTS.name, task_id, todo_id " +
                "from T4U_WORKITEMS " +
                "left join T4U_DAYINFOS on (T4U_WORKITEMS.dayinfo_id=T4U_DAYINFOS.id) " +
                "left join T4U_PROJECTS on (T4U_WORKITEMS.project_id=T4U_PROJECTS.id) " +
                "left join BILLITEMS on (T4U_WORKITEMS.id=BILLITEMS.workitem_id) " +
                "where T4U_WORKITEMS.dayinfo_id in (");
        for (int i = 0; i < days.size(); i++) {
            query.append("'" + days.get(i).getId() + "'");
            // Append comma as long as this is not the last day info
            if (i < (days.size() - 1)) {
                query.append(",");
            }
        }
        query.append(") ");
        if (projectId!=null) {
            query.append(" and project_id in (");
            for (int i=0; i<sProjects.size(); i++) {
                query.append("'" + sProjects.get(i).getId() + "'");
                // Append comma as long as this is not the last project
                if (i<(sProjects.size()-1)) {
                    query.append(",");
                }
            }
            query.append(")");
        }
        if (personId!=null) {
            query.append(" and T4U_DAYINFOS.person_id=? ");
        }
        if (taskId!=null) {
        	query.append(" and T4U_WORKITEMS.task_id=? ");
        }
        if (!showBilled) {
            query.append(" and bill_id is null ");
        }
        query.append(" group by T4U_DAYINFOS.person_id, T4U_WORKITEMS.project_id, T4U_PROJECTS.name, task_id, todo_id");
        query.append(" order by T4U_WORKITEMS.project_id");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        int c=1;
        if (personId!=null) {
            q.setParameter(c++, personId);
        }
        if (taskId!=null) {
        	q.setParameter(c++, taskId);
        }
        List list = q.getResultList();
        List result = new Vector<WorkSummary>();
        WorkSummary ws;
        for (int i=0; i<list.size(); i++) {
            ws = new WorkSummary((Object[])list.get(i));
            result.add(ws);
        }
        return(result);
    	
    	
    }
    
    /**
     * Returns the first worked time for given project within given date interval
     * 
     * @param projectId
     * @param since
     * @param until
     * @param first  If not true, latest time is returned.
     * @param showBilled
     * @return
     */
    public java.sql.Date getLimitTimeForProject(String projectId, String personId, String taskId, java.sql.Date since, java.sql.Date until, boolean first, boolean showBilled) {
        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(since, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId, true);
        // Create query
        StringBuffer query = new StringBuffer(
                "select T4U_WORKITEMS.WBEGIN, T4U_WORKITEMS.WEND, T4U_DAYINFOS.DAYDATE from T4U_WORKITEMS " +
                "LEFT JOIN T4U_DAYINFOS ON (T4U_WORKITEMS.DAYINFO_ID=T4U_DAYINFOS.ID) " +
                "left join BILLITEMS on (T4U_WORKITEMS.id=BILLITEMS.workitem_id) " +
                "where ");

        if (since!=null) {
        	query.append("DAYDATE>=? AND ");
        }
        if (until!=null) {
        	query.append("DAYDATE<=? AND ");
        }
        if (personId!=null) {
        	query.append("PERSON_ID=? AND ");
        }
        if (taskId!=null) {
        	query.append("TASK_ID=? AND ");
        }
        query.append("project_id in (");
        for (int i=0; i<sProjects.size(); i++) {
            query.append("'" + sProjects.get(i).getId() + "'");
            // Append comma as long as this is not the last project
            if (i<(sProjects.size()-1)) {
                query.append(",");
            }
        }
        query.append(") ");
        if (!showBilled) {
        	query.append(" and bill_id is null ");
        }
        query.append("order by T4U_DAYINFOS.DayDate ");
        if (!first)
        	query.append(" DESC");
        if (m_isDerby) {
        	query.append(" FETCH FIRST 1 ROWS ONLY");
        }
        if (m_isMysql) {
        	query.append(" LIMIT 1");
        }
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        int c=1;
        if (since!=null) {
        	q.setParameter(c++, since);
        }
        if (until!=null) {
        	q.setParameter(c++, until);
        }
        if (personId!=null)
        	q.setParameter(c++, personId);

        if (taskId!=null) {
        	q.setParameter(c++, taskId);
        }
        
        List result = q.getResultList();
        java.sql.Date date = null;
        long secs = 0;
        Object[] row;
        int timeIdx = first ? 0 : 1;
        
        if (result.size()>0) {
        	row = (Object[])result.get(0);
        	date = (java.sql.Date)row[2];
        	if (row[timeIdx] instanceof Integer)
        		secs = (Integer)row[timeIdx];
        	if (row[timeIdx] instanceof BigDecimal) {
        		secs = ((BigDecimal)row[timeIdx]).longValue();
        	}
        	if (secs>0 && date!=null) {
        		date = new java.sql.Date(date.getTime() + (secs*1000));
        	}
        }
        
    	return date;
    }
    
    /**
     * @param projectId     Project to get worked time for.
     * @param since         From date
     * @param until         Until date
     * @return  Worked time in seconds for the given project and subprojects.
     */
    public long getWorkedTimeForProject(String projectId, java.sql.Date since, java.sql.Date until) {
        // First determine what dayinfos are included in the period.
        List<T4uDayinfos> days = getDayInfos(since, until);
        // Get subprojects
        List<T4uProjects> sProjects = getSubProjects(projectId);
        // Create query
        StringBuffer query = new StringBuffer(
                "select sum(wEnd-wBegin) from T4U_WORKITEMS " +
                "where ");
        
        if (days.size()>0) {
                query.append("dayinfo_id in (");
	        for (int i = 0; i < days.size(); i++) {
	            query.append("'" + days.get(i).getId() + "'");
	            // Append comma as long as this is not the last day info
	            if (i < (days.size() - 1)) {
	                query.append(",");
	            }
	        }
	        query.append(") and ");
        }
        query.append("project_id in (");
        for (int i=0; i<sProjects.size(); i++) {
            query.append("'" + sProjects.get(i).getId() + "'");
            // Append comma as long as this is not the last project
            if (i<(sProjects.size()-1)) {
                query.append(",");
            }
        }
        query.append(")");
        initEntityManager();
        Query q = em.createNativeQuery(query.toString());
        // Check return type
        Object r = q.getSingleResult();
        if (r instanceof Integer) {
	        Integer s = (Integer)r;
	        return (s.longValue());
        }
        if (r instanceof BigDecimal) {
        	BigDecimal b = (BigDecimal)r;
        	return(b.longValue());
        }
        return -1;
    }
    
    public void startTransaction() throws Exception {
    	if (m_et!=null && m_et.isActive()) {
    		throw new Exception("Trying to run multiple transactions without committing.");
    	}
    	m_et = em.getTransaction();
    	m_et.begin();
    	
    }
    
    public void commitTransaction() {
    	m_et.commit();
    	m_et = null;
    }
    
    public void persist(Object obj) {
    	em.persist(obj);
    }

}
