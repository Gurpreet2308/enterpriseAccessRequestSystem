package database;


public class Status {

  private long statusId;
  private String statusName;


  public long getStatusId() {
    return statusId;
  }

  public static long getStatusId(String status){
    if(status!=null && !status.isEmpty()){
      switch (status){
        case "Accepted":
          return -1818509258;
        case "Pending":
          return -1605677271;
        case "Rejected":
          return 1620135295;
      }
    }return 0;
  }

  public static String getStatus(long statusId){
    if(statusId!=0){
      switch ((int)statusId){
        case -1818509258:
          return "Accepted";
        case -1605677271:
          return "Pending";
        case 1620135295:
          return "Rejected";
      }
    }return null;
  }

  public void setStatusId(long statusId) {
    this.statusId = statusId;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

}
