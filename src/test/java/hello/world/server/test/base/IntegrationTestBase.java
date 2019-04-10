package hello.world.server.test.base;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.mockito.Mockito.mock;

public abstract class IntegrationTestBase {
    protected static EmbeddedServer server;
    protected static HttpClient client;
    public static HashMap<Class, Object> mocks = new HashMap();

    private static boolean serverStarted = false;

    @BeforeEach
    public void setupServer() {
        if(!serverStarted) {
            server = ApplicationContext.run(EmbeddedServer.class);
            mocks.keySet().forEach(mockClass ->
                    server.getApplicationContext().registerSingleton(mockClass, mocks.get(mockClass)));

            client = server
                    .getApplicationContext()
                    .createBean(HttpClient.class, server.getURL());

            serverStarted = true;
        }
    }

    @AfterAll
    public static void stopServer() {
        if(server != null) {
            server.stop();
        }
        if(client != null) {
            client.stop();
        }
        serverStarted = false;
    }

    public static <T> T createMock(Class<T> classToMock) {
        final T mock = mock(classToMock);
        mocks.put(classToMock, mock);
        return mock;
    }
}
