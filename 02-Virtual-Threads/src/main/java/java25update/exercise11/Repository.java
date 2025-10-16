package java25update.exercise11;

import java25update.exercise11.model.Data;
import java25update.exercise11.model.User;

import java.util.UUID;

class Repository {

  Data getData(UUID id, User loggedInUser) {
    IO.println("Getting data (logged in user: " + loggedInUser + ")");

    Data data = findById(id);
    if (loggedInUser.admin()) {
      enrichDataWithAdminInfos(data);
    }
    return data;
  }

  private Data findById(UUID id) {
    return new Data(id);
  }

  private void enrichDataWithAdminInfos(Data data) {
    IO.println("+ Enriching data with admin infos");

    data.setAdminInfos("admin infos");
  }
}