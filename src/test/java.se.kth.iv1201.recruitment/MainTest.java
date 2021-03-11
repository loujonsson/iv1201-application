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

    /**
     * Example test for CI
     * @throws Exception
     */
    @Test
    public void exampleTest() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().is(302));
    }


    
}
