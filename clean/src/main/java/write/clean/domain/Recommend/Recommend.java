package write.clean.domain.Recommend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import write.clean.domain.Answer.Answer;

@Entity
@Table(name = "recommend")
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long recommend_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;
}
