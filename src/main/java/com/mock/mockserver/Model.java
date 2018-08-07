package com.mock.mockserver;

/**
 * Created by mayank.chaurasia on 02-08-2018.
 */
public class Model {

  private long fromId;
  private long toId;
  private String fromEmail;
  private String toEmail;
  private String fromFullName;
  private String toFullName;

  public Model(long fromId, long toId){
    this.fromId = fromId;
    this.fromEmail = "test_"+fromId+"@gmail.com";
    this.fromFullName = "FTest" + fromId;
    this.toId = toId;
    this.toEmail = "test_"+toId+"@gmail.com";
    this.toFullName = "TTest" + toId;
  }
  public long getFromId() {
    return fromId;
  }

  public void setFromId(long fromId) {
    this.fromId = fromId;
  }

  public long getToId() {
    return toId;
  }

  public void setToId(long toId) {
    this.toId = toId;
  }

  public String getFromEmail() {
    return fromEmail;
  }

  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  public String getToEmail() {
    return toEmail;
  }

  public void setToEmail(String toEmail) {
    this.toEmail = toEmail;
  }

  public String getFromFullName() {
    return fromFullName;
  }

  public void setFromFullName(String fromFullName) {
    this.fromFullName = fromFullName;
  }

  public String getToFullName() {
    return toFullName;
  }

  public void setToFullName(String toFullName) {
    this.toFullName = toFullName;
  }
}
