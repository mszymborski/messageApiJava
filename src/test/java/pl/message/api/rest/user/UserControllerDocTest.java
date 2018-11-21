package pl.message.api.rest.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.message.api.rest.user.impl.UserDTO;
import pl.message.api.rest.user.interfaces.UserService;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
//TODO Prepare test for all controller services
public class UserControllerDocTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void userCreateExample() throws Exception {

        UserDTO userModel = new UserDTO("ExampleName","ExampleSurname", "example@email");
        UserDTO userOut = new UserDTO(userModel);
        userOut.setId(1L);
        userOut.setCreated(LocalDate.now());
        userOut.setLastModified(LocalDate.now());

        String userString = this.objectMapper.writeValueAsString(userModel);

        given(userService.createUser(userModel)).willReturn(userOut);

        this.mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                                        .content(userString))
                .andExpect(status().isCreated())
                .andDo(document("create-user-example",
                        requestFields(fieldWithPath("id").description("Created user id"),
                                fieldWithPath("name").description("Created user name"),
                                fieldWithPath("surname").description("Created user surname"),
                                fieldWithPath("email").description("Created user email"),
                                fieldWithPath("created").description("Date of user creation"),
                                fieldWithPath("lastModified").description("Date of user data last modification"))));
    }


    public void getAllUserExample(){

    }

}
