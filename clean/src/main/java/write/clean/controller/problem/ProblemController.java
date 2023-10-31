package write.clean.controller.problem;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import write.clean.domain.Problem.Problem;
import write.clean.service.problem.ProblemService;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProblemController {
    
    private final ProblemService problemService;

    @GetMapping("/problem")
    public ResponseEntity<List<Problem>> allProblem() {
        return ResponseEntity.ok(problemService.findAllProblem());
    }

    @GetMapping("/problem/{id}")
    public ResponseEntity<Problem> findProblem(@PathVariable Long id) {
        return ResponseEntity.ok(problemService.findById(id));
    }
}
