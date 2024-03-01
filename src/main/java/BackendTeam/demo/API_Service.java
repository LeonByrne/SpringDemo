package BackendTeam.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class API_Service
{
    // Let's pretend that this is a real api and not a pretend one.
    private final static String API_URL = "";

    private final RestTemplate template;

    public API_Service()
    {
        this.template = new RestTemplate();
    }

    /**
     *
     * @return Returns the person retrieved from the api
     */
    public PersonDTO getObject(String name)
    {
        // The url can be modified to include those path variables shown in the controller
        // When we specify the return type the method attempts to convert the returned json string into that type.
        // This can sometimes cause exceptions. Since it can't handle errors such as 404, 403, etc
        PersonDTO person = template.getForObject(API_URL + "/people/" + name, PersonDTO.class);

        return person;
    }

    public PersonDTO getResponse(String name)
    {
        // This is better as this won't throw an exception due to http errors
        // Should an error occur we can also see what error it was
        ResponseEntity<PersonDTO> person = template.getForEntity(API_URL + "/people/" + name, PersonDTO.class);

        // For example we can just log the error
        if(person.getStatusCode() == HttpStatus.NOT_FOUND)
        {
            System.out.println("404 error: " + API_URL + " not found");
        }

        // Will be null if an error occurred
        return person.getBody();
    }


    /**
     * Let's pretend that this api request adds a person to the database and returns the rest of the database
     *
     * @param person
     * @return
     */
    public PersonDTO[] postForEntity(PersonDTO person)
    {
        ResponseEntity<PersonDTO[]> people = template.postForEntity(API_URL + "/add_person", person, PersonDTO[].class);

        if(people.getStatusCode() == HttpStatus.NOT_FOUND)
        {
            System.out.println("404 error: " + API_URL + " not found");
        }

        return people.getBody();
    }
}
