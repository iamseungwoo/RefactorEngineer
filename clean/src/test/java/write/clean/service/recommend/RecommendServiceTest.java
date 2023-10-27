package write.clean.service.recommend;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import write.clean.domain.Answer.Answer;
import write.clean.domain.Problem.Problem;
import write.clean.domain.Recommend.Recommend;
import write.clean.domain.Recommend.RecommendRepository;
import write.clean.dtos.recommend.SaveRecommendDto;

@ExtendWith(MockitoExtension.class)
public class RecommendServiceTest {

    @Mock
    RecommendRepository recommendRepository;

    Answer answer;
    Problem problem;

    @BeforeEach
    void answer_생성() {
        this.problem = Problem.builder()
                    .problemContent("c++ hello world")
                    .build();

        this.answer = Answer.builder()
                    .content("cout << hello world;")
                    .problem(this.problem)
                    .build();
    }

    @Test
    void recommend_생성테스트() {
        SaveRecommendDto saveRecommendDto = new SaveRecommendDto("good!", answer);
        when(recommendRepository.save(any(Recommend.class))).thenReturn(saveRecommendDto.toEntity());

        RecommendService recommendService = new RecommendService(recommendRepository);
        assertThat(recommendService.saveRecommend(saveRecommendDto).getRecommendMsg()).isEqualTo(saveRecommendDto.recommendMsg());
    }
}
