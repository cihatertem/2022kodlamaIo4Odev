package kodlama.io.devs.webApi.controllers;

import kodlama.io.devs.business.abstracts.FrameworkService;
import kodlama.io.devs.business.requests.frameworkRequests.FrameworkRequest;
import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frameworks")
public class FrameworksController {
    private final FrameworkService frameworkService;

    @Autowired
    public FrameworksController(FrameworkService frameworkService) {
        this.frameworkService = frameworkService;
    }

    @GetMapping("")
    public List<FrameworkResponse> getAllFrameworks() {
        return frameworkService.getAllFrameworks();
    }

    @GetMapping("/{id}")
    public FrameworkResponse getSingleFramework(@PathVariable int id) throws Exception {
        return frameworkService.getSingleFramework(id);
    }

    @PostMapping("")
    public String createNewFramework(@RequestBody FrameworkRequest request) throws Exception {
        return frameworkService.createNewFramework(request);
    }

    @PutMapping("/{id}")
    public String updateFramework(@RequestBody FrameworkRequest request, @PathVariable int id) throws Exception {
        return frameworkService.updateFramework(request, id);
    }

    @DeleteMapping("/{id}")
    public String deleteFramework(@PathVariable int id) throws Exception {
        return frameworkService.deleteFramework(id);
    }
}
