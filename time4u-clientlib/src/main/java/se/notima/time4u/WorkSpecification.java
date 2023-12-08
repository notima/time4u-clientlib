/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u;

import se.notima.time4u.entities.*;

/**
 *
 * @author Daniel Tamm
 */
public class WorkSpecification {

    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String          workItemId;
    private String          personId;
    private String          dayInfoId;
    private java.sql.Date   startTime;
    private java.sql.Date   endTime;
    private long            duration;
    private String          projectId;
    private String          projectName;
    private String          taskId;
    private String          todoId;
    private Billitems       billItem;
    private String          comment;


    /** Default constructor */
    public WorkSpecification() {}

    public WorkSpecification(java.util.Vector v) {
    	populateFromVector(v);
    }
    
    public WorkSpecification(Object[] obj) {
    	if (obj==null) return;
    	java.util.Vector v = new java.util.Vector();
    	for (int i=0; i<obj.length; i++) {
    		v.add(obj[i]);
    	}
    	populateFromVector(v);
    }

    
    public void populateFromVector(java.util.Vector v) {
    	
        int c = 0;
        workItemId = (String)v.get(c++);
        personId = (String)v.get(c++);
        dayInfoId = (String)v.get(c++);
        java.sql.Date day = (java.sql.Date)v.get(c++);
        long t0 = day.getTime();
        long ts = (Integer)v.get(c++);
        startTime = new java.sql.Date(t0 + ts * 1000);
        long te = (Integer)v.get(c++);
        endTime = new java.sql.Date(t0 + te * 1000);
        projectId = (String)v.get(c++);
        projectName = (String)v.get(c++);
        taskId = (String)v.get(c++);
        todoId = (String)v.get(c++);
        comment = (String)v.get(c++);
        if (v.size()>c) {
            Integer billId = (Integer)v.get(c++);
            if (billId!=null) {
                billItem = new Billitems();
                billItem.setBillId(billId.intValue());
                billItem.setBillingRunId((Integer)v.get(c++));
                billItem.setMultiplier((Double)v.get(c++));
                billItem.setWorkitemId(workItemId);
            }
        }

    	
    }

    /**
     * @return the workItemId
     */
    public String getWorkItemId() {
        return workItemId;
    }

    /**
     * @param workItemId the workItemId to set
     */
    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
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
     * 
     * @return  Work specification's duration in seconds
     */
    public long getDuration() {
        if (startTime!=null && endTime!=null) {
            return((endTime.getTime() - startTime.getTime())/1000);
        } else {
            return(duration);
        }
    }

    public void setDuration(long secs) {
        duration = secs;
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
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return(workItemId + " : " + personId + " : " + dateFormat.format(startTime) + " : " + dateFormat.format(endTime) + " : "
               + projectId + " : " + projectName + " : " + comment);
    }

    /**
     * @return the dayInfoId
     */
    public String getDayInfoId() {
        return dayInfoId;
    }

    /**
     * @param dayInfoId the dayInfoId to set
     */
    public void setDayInfoId(String dayInfoId) {
        this.dayInfoId = dayInfoId;
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
