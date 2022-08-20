package database;


public class EmployeeDepartment {

  private long empId;
  private long deptId;
  private java.sql.Timestamp deptStartDate;
  private java.sql.Timestamp deptEndDate;
  private String deptName;

  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }

  public long getDeptId() {
    return deptId;
  }

  public void setDeptId(long deptId) {
    this.deptId = deptId;
  }

  public java.sql.Timestamp getDeptStartDate() {
    return deptStartDate;
  }

  public void setDeptStartDate(java.sql.Timestamp deptStartDate) {
    this.deptStartDate = deptStartDate;
  }

  public java.sql.Timestamp getDeptEndDate() {
    return deptEndDate;
  }

  public void setDeptEndDate(java.sql.Timestamp deptEndDate) {
    this.deptEndDate = deptEndDate;
  }

  public String getDeptName() { return deptName; }

  public void setDeptName(String deptName) { this.deptName = deptName; }
}
