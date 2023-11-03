package write.clean.domain.Answer;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import write.clean.domain.Problem.Problem;
import write.clean.domain.Recommend.Recommend;
import write.clean.domain.User.User;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Problem problem;

    @OneToMany(mappedBy = "answer")
    private List<Recommend> recommends = new ArrayList<>();

    @ManyToOne
    private User user;

    @Builder
    public Answer(String content, Problem problem) {
        this.content = content;
        this.problem = problem;
        this.problem.addAnswer(this);
    }

    public String getContent() {
        return this.content;
    }
}
