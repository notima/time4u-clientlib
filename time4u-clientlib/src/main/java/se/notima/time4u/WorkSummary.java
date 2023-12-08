/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u;

import java.math.BigDecimal;
import java.util.List;

import se.notima.time4u.entities.*;

/**
 *
 * @author Daniel Tamm
 */
public class WorkSummary {

    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String          personId;
    private java.sql.Date   startTime;
    private java.sql.Date   endTime;
    private long            duration;
    private String          projectId;
    private String          projectName;
    private String          taskId;
    private String          todoId;
    private Billitems       billItem;
    private List<String>          comments;

    private java.text.NumberFormat nf = new java.text.DecimalFormat("00");

    /** Default constructor */
    public WorkSummary() {}


	public WorkSummary(java.util.Vector v) {
    	populateFromVector(v);
    }
    
    public WorkSummary(Object[] obj) {
    	if (obj==null) return;
    	java.util.Vector v = new java.util.Vector();
    	for (int i=0; i<obj.length; i++) {
    		v.add(obj[i]);
    	}
    	populateFromVector(v);
    }

    
    public void populateFromVector(java.util.Vector v) {
    	
        personId = (String)v.get(0);
        if (v.get(1) instanceof Integer) {
        	duration = (Integer)v.get(1);
        } else if (v.get(1) instanceof BigDecimal) {
        	duration = ((BigDecimal)v.get(1)).longValue();
        } else {
        	duration = -1l;
        }
        projectId = (String)v.get(2);
        projectName = (String)v.get(3);
        if (v.size()>4) {
	        taskId = (String)v.get(4);
	        todoId = (String)v.get(5);
        }
    	
    }

    /**
     * @return the personId
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
    
    /**
     * @return the startTime
     */
    public java.sql.Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public java.sql.Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the todoId
     */
    public String getTodoId() {
        return todoId;
    }

    /**
     * @param todoId the todoId to set
     */
    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    /**
     * @return the comment
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * @param comment the comment to set
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return(personId + " : "
               + projectId + " : " + projectName + " : " + secondsToString(duration));
    }

    private String secondsToString(long secs) {
        long minutes = secs/60;
        long hours = minutes/60;
        minutes = minutes % 60;
        return(hours + "," + nf.format((Math.round((minutes/60.0)*100))));
    }
    
    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Billitems getBillItem() {
        return(billItem);
    }

    public void setBillItem(Billitems b) {
        billItem = b;
    }

}
