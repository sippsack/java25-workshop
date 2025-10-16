package java25update.solutions.exercise11;

import java25update.exercise11.model.Request;

public class App {
  void main() {
    Server server = new Server();
    server.serve(new Request("sven"));
    IO.println();
    server.serve(new Request("noname"));
  }
}
