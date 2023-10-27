package write.clean.service.recommend;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import write.clean.domain.Recommend.Recommend;
import write.clean.domain.Recommend.RecommendRepository;
import write.clean.dtos.recommend.SaveRecommendDto;

@RequiredArgsConstructor
@Service
public class RecommendService {
    
    private final RecommendRepository recommendRepository;

    public Recommend saveRecommend(SaveRecommendDto saveRecommendDto) {
        Recommend saveRecommend = Recommend.builder()
                                    .recommendMsg(saveRecommendDto.recommendMsg())
                                    .answer(saveRecommendDto.answer())
                                    .build();

        return recommendRepository.save(saveRecommend);
    }
}
