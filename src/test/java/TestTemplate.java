import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTemplate {
    
    @BeforeAll
    static void beforeAll() {}

    @BeforeEach void BeforeEach() {}

    @AfterAll
    static void afterAll() {}

    @AfterEach
    static void afterEach() {}

    @Test
    void test() {}
}