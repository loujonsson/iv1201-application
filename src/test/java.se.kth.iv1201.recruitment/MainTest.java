import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import se.kth.iv1201.recruitment.Main;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes=Main.class)
public class MainTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void exampleTest() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().is(404));
    }

/*    private Main main;

    @BeforeEach
    public void setUp() throws Exception{
        main = new Main();
    }
    @Test
    @DisplayName("Example test for CI/CD")
    public void testForExampleTest(){
        assertEquals(15, main.forExampleTest(5,10), "Regular sum works");
    }

    @Test
    @DisplayName("Example test for CI/CD, negative")
    public void testForExampleTestNeg(){
        assertEquals(-10, main.forExampleTest(-1,-9), "Sum with negative integers");
    }*/
}
