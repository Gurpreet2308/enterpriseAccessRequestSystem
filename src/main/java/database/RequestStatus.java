package database;


public class RequestStatus {

  private long reqId;
  private long statusId;
  private java.sql.Timestamp reqStatusStartTime;
  private long reqApproverId;


  public long getReqId() {
    return reqId;
  }

  public void setReqId(long reqId) {
    this.reqId = reqId;
  }


  public long getStatusId() {
    return statusId;
  }

  public void setStatusId(long statusId) {
    this.statusId = statusId;
  }


  public java.sql.Timestamp getReqStatusStartTime() {
    return reqStatusStartTime;
  }

  public void setReqStatusStartTime(java.sql.Timestamp reqStatusStartTime) {
    this.reqStatusStartTime = reqStatusStartTime;
  }


  public long getReqApproverId() {
    return reqApproverId;
  }

  public void setReqApproverId(long reqApproverId) {
    this.reqApproverId = reqApproverId;
  }

}
