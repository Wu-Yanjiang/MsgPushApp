package cn.yjlearn.msgpushapp.entity;


public class MessageConfig {

  private Long id;
  private long userId;
  private String companyId;
  private int agentId;
  private String appSecret;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }


  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }


  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

}
