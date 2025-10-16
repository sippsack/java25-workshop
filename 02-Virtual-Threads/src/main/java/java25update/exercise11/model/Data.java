package java25update.exercise11.model;

import java.util.UUID;

public class Data {
  private final UUID id;
  private String adminInfos;

  public Data(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getAdminInfos() {
    return adminInfos;
  }

  public void setAdminInfos(String adminInfos) {
    this.adminInfos = adminInfos;
  }
}
