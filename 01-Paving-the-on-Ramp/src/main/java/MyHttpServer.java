import module java.base;
import module jdk.httpserver;

public class MyHttpServer {
    static void main() {
        startHttpServer();
    }

    static void startHttpServer() {
        HttpServer server = SimpleFileServer.createFileServer(
                new InetSocketAddress(8888), Path.of(".").toAbsolutePath(), SimpleFileServer.OutputLevel.INFO);
        server.start();
        IO.println("Open http://localhost:8888 in your browser ...");
    }
}
