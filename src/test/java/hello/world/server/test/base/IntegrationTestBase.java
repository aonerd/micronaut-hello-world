package hello.world.server.test.base;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

import static org.mockito.Mockito.mock;

public abstract class IntegrationTestBase {
    protected static EmbeddedServer server;
    protected static HttpClient client;

    private static HashMap<Class, Object> mocks = new HashMap();


    @BeforeAll
    public static void startServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
    }

    @BeforeEach
    public void setupClient() {
        server = ApplicationContext.run(EmbeddedServer.class);

        mocks.keySet().forEach(mockClass ->
                server.getApplicationContext()
                        .registerSingleton(mockClass, mocks.get(mockClass)));

        client = server
                .getApplicationContext()
                .createBean(HttpClient.class, server.getURL());

        beforeEach();
    }


    @AfterEach
    public void stopClient() {
        if(client != null) {
            client.stop();
        }
    }

    @AfterAll
    public static void stopServer() {
        if(server != null) {
            server.stop();
        }
    }

    public static <T> T createMock(Class<T> classToMock) {
        final T mock = mock(classToMock);
        mocks.put(classToMock, mock);
        return mock;
    }

    public void beforeEach(){
        //Placeholder to be override if needed
    }
}
