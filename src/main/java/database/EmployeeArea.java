package database;


public class EmployeeArea {

  private long areaId;
  private long empId;
  private java.sql.Timestamp areaStartDate;
  private java.sql.Timestamp areaEndDate;


  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
    this.areaId = areaId;
  }


  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }


  public java.sql.Timestamp getAreaStartDate() {
    return areaStartDate;
  }

  public void setAreaStartDate(java.sql.Timestamp areaStartDate) {
    this.areaStartDate = areaStartDate;
  }


  public java.sql.Timestamp getAreaEndDate() {
    return areaEndDate;
  }

  public void setAreaEndDate(java.sql.Timestamp areaEndDate) {
    this.areaEndDate = areaEndDate;
  }

}
