package sky.pro.course3.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.repository.FacultyRepository;
import sky.pro.course3.homework.service.FacultyService;

import java.util.Optional;

import static java.util.Optional.of;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sky.pro.course3.homework.ConstantsForTests.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createFaculty() throws Exception {
        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(MOCK_FACULTY);

        JSONObject createFacultyRq = new JSONObject();
        createFacultyRq.put("name", MOCK_FACULTY_NAME);
        createFacultyRq.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(createFacultyRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void getFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void deleteFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        MOCK_FACULTY.setName(MOCK_FACULTY_NEW_NAME);

        JSONObject updateFacultyRq = new JSONObject();
        updateFacultyRq.put("id", MOCK_FACULTY.getId());
        updateFacultyRq.put("name", MOCK_FACULTY.getName());
        updateFacultyRq.put("color", MOCK_FACULTY.getColor());

        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(MOCK_FACULTY);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(updateFacultyRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY.getName()))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY.getColor()));
    }
    @Test
    public void getFacultyByNameOrByColor() throws Exception {
        when(facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(anyString(),anyString())).thenReturn(MOCK_FACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter?name=" + MOCK_FACULTY_NAME + "&color=" + MOCK_FACULTY_COLOR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(MOCK_FACULTIES)));
    }
    @Test
    public void getStudentsByFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}