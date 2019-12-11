package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class AnnotationMockTest {

    @Mock
    private Map<String, Object> mapMock;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    private void testMock() {
        mapMock.put("string", "value");
    }
}
