package write.clean.controller.problem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import write.clean.domain.Problem.Problem;
import write.clean.domain.Problem.ProblemRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ProblemControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    ProblemRepository problemRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void delteRepository() {
        this.problemRepository.deleteAll();
    }

    @DisplayName("problem 전부 가져오기")
    @Test
    public void getAllProblem() throws Exception {
        // given
        final String url = "/api/problem";
        final String content = "<script>test</script>";
        problemRepository.save(Problem.builder()
                .problemContent(content)
                .build());

        // when
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].problemContent").value(content));
    }

    @DisplayName("problem id 가져오기")
    @Test
    public void getFindById() throws Exception {
        // given
        final String url = "/api/problem/{id}";
        final String content1 = "<script>test1</script>";
        final String content2 = "test2";

        problemRepository.save(Problem.builder()
                .problemContent(content1)
                .build());

        Problem findProblem = problemRepository.save(Problem.builder()
                .problemContent(content2)
                .build());
        final Long findId = findProblem.getProblem_id();

        // when
        ResultActions resultActions = mockMvc.perform(get(url, findId));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.problemContent").value(content2));
    }
}
