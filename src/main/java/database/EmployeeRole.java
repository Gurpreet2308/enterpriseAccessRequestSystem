package database;

import java.util.ArrayList;

public class EmployeeRole {

  private java.sql.Timestamp roleStartDate;
  private java.sql.Timestamp roleEndDate;
  private String empRolePassword;
  private long empId;
  private long roleId;

  private ArrayList<String> roles;
  private String empName;

  public EmployeeRole() { }

  public java.sql.Timestamp getRoleStartDate() {
    return roleStartDate;
  }

  public void setRoleStartDate(java.sql.Timestamp roleStartDate) {
    this.roleStartDate = roleStartDate;
  }

  public java.sql.Timestamp getRoleEndDate() {
    return roleEndDate;
  }

  public void setRoleEndDate(java.sql.Timestamp roleEndDate) {
    this.roleEndDate = roleEndDate;
  }

  public String getEmpRolePassword() {
    return empRolePassword;
  }

  public void setEmpRolePassword(String empRolePassword) {
    this.empRolePassword = empRolePassword;
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

  public ArrayList<String> getRoles() { return roles; }

  public void setRoles(ArrayList<String> roles) { this.roles = roles; }

  public String getEmpName() { return empName; }

  public void setEmpName(String empName) { this.empName = empName; }

}
