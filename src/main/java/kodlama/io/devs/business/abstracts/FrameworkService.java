package kodlama.io.devs.business.abstracts;

import kodlama.io.devs.business.requests.frameworkRequests.FrameworkRequest;
import kodlama.io.devs.business.responses.frameworkResponses.FrameworkResponse;

import java.util.List;

public interface FrameworkService {
    String createNewFramework(FrameworkRequest request) throws Exception;

    List<FrameworkResponse> getAllFrameworks();

    FrameworkResponse getSingleFramework(int id) throws Exception;

    String updateFramework(FrameworkRequest request, int id) throws Exception;

    String deleteFramework(int id) throws Exception;
}
