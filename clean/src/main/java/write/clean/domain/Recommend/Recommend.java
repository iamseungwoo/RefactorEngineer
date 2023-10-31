package write.clean.domain.Recommend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import write.clean.domain.Answer.Answer;

@Entity
@Table(name = "recommend")
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long recommendId;

    @Column(name = "content")
    private String recommendMsg;

    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @Builder
    public Recommend(String recommendMsg, Answer answer) {
        this.recommendMsg = recommendMsg;
        this.answer = answer;
    }

    public String getRecommendMsg() {
        return this.recommendMsg;
    }
}
