package database;


import java.sql.Timestamp;

public class Request {

  private long reqId;
  private long empId;
  private java.sql.Timestamp reqCreatedDate;
  private java.sql.Timestamp reqCompletedDate;
  private long areaRequested;
  private String areaName;
  private String empName;
  private String status;


  public long getReqId() {
    return reqId;
  }

  public void setReqId(long reqId) {
    this.reqId = reqId;
  }

  public long getEmpId() {
    return empId;
  }

  public void setEmpId(long empId) {
    this.empId = empId;
  }

  public java.sql.Timestamp getReqCompletedDate() {
    return reqCompletedDate;
  }

  public void setReqCompletedDate(java.sql.Timestamp reqCompletedDate) { this.reqCompletedDate = reqCompletedDate; }

  public Timestamp getReqCreatedDate() { return reqCreatedDate; }

  public void setReqCreatedDate(Timestamp reqCreatedDate) { this.reqCreatedDate = reqCreatedDate; }

  public long getAreaRequested() {
    return areaRequested;
  }

  public void setAreaRequested(long areaRequested) {
    this.areaRequested = areaRequested;
  }

  public String getAreaName() { return areaName; }

  public void setAreaName(String areaName) { this.areaName = areaName;}

  public String getEmpName() { return empName; }

  public void setEmpName(String empName) { this.empName = empName; }

  public String getStatus() { return status; }

  public void setStatus(String status) { this.status = status; }
}
