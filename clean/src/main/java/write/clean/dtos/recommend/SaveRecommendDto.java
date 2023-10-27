package write.clean.dtos.recommend;

import write.clean.domain.Answer.Answer;
import write.clean.domain.Recommend.Recommend;

/**
 * saveRecommendDto
 */
public record SaveRecommendDto(
    String recommendMsg,
    Answer answer
) {
    public Recommend toEntity() {
        return Recommend.builder()
                .recommendMsg(this.recommendMsg)
                .answer(this.answer)
                .build();
    }
}