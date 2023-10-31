package write.clean.service.problem;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import write.clean.domain.Problem.Problem;
import write.clean.domain.Problem.ProblemRepository;

@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public List<Problem> findAllProblem() {

        List<Problem> allProblem = problemRepository.findAll();

        return allProblem;
    }

    public Problem findById(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return problem;
    }
}