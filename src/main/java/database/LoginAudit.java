package database;


public class LoginAudit {

  private long auditId;
  private long empId;
  private long roleId;
  private java.sql.Timestamp loginTime;
  private java.sql.Timestamp logoffTime;
  private long tasksExecuted;


  public long getAuditId() {
    return auditId;
  }

  public void setAuditId(long auditId) {
    this.auditId = auditId;
  }


  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public java.sql.Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(java.sql.Timestamp loginTime) {
    this.loginTime = loginTime;
  }


  public java.sql.Timestamp getLogoffTime() {
    return logoffTime;
  }

  public void setLogoffTime(java.sql.Timestamp logoffTime) {
    this.logoffTime = logoffTime;
  }


  public long getTasksExecuted() {
    return tasksExecuted;
  }

  public void setTasksExecuted(long tasksExecuted) {
    this.tasksExecuted = tasksExecuted;
  }

}
