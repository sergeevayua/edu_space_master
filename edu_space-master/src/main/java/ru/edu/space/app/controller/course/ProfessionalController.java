package ru.edu.space.app.controller.course;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.space.app.controller.AbstractGetController;
import ru.edu.space.app.entity.course.Professional;
import ru.edu.space.app.service.EntityService;
import ru.edu.space.app.service.course.ProfessionalService;

/**
 * @author agerus
 */
@RestController
@RequestMapping(ProfessionalController.PATH)
public class ProfessionalController extends AbstractGetController<Long, Professional> {

    public static final String PATH = "/api/professional/";

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @Override
    protected EntityService<Long, Professional> getService() {
        return professionalService;
    }
}
