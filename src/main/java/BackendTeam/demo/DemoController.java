package BackendTeam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Controllers are where we connect methods to the web. We assign urls to methods inside here.
@RestController
public class DemoController
{
    // Autowired means spring boot will handle this, anything autowired by the same name is shared throughout the application.
    @Autowired
    DemoService demoService;

    /*
        There are a couple types of mappings.
        GET    This is only for retrieving data from the server.
        POST   This is used for providing data to the server for a wide variety of reasons.
        PATCH  This is used for partial updates, eg only changing a persons phone no.
        PUT    This is used to update or replace existing data.
        DELETE This is for deleting data.
     */

    @GetMapping("/hello")  // The mapping tells spring where the function is called from. Eg http://localhost:8080/hello
    public String hello()    // The name can be whatever you want but the type and being public matters.
    {
        return "Hi.";
    }

    // There are two ways to pass variables to the server, one is through path variables
    @GetMapping("/I_am_{name}")  // Whatever is in place of {name} will be given as a variable.
    public String name(@PathVariable String name)   // We need to specify the type.
    {
        return "Nice to meet you " + name;  // We can use the string passed through the path in the function.
    }

    @GetMapping("/age_is_{age}")
    public String age(@PathVariable int age)  // Path variables can be integers, floats, etc. Spring will convert it for you
    {
        return "You're " + age + " years old.";
    }


    /*
        Path variables can only do so much. Request bodies can send much more complicated data types.

        Request bodies allow us to send whole objects, even ones we create ourselves

        Don't worry about how data is sent to this, but here is an example command

        curl -d '{"name":"Leon Byrne", "age": 20}' -H "Content-Type: application/json" -X POST http://localhost:8080/person
    */
    @PostMapping("/person")
    public String person(@RequestBody PersonDTO person)
    {
        // We are using the service here
        demoService.addPerson(person);

        return "Your name is " + person.getName() + " and you're " + person.getAge() + " years old.";
    }

    @GetMapping("/people")
    public String people()
    {
        return demoService.getPeople();
    }

    /*
        Status codes can be returned to indicate the status of the server, whether the data sent is done so correctly,
        whether the user is allowed or meant to access the resource, etc.

        curl -d '2' -H 'Content-Type: application/json' -X POST http://localhost:8080/only_twos
     */
    @PostMapping("/only_twos")
    public HttpStatus twoOnly(@RequestBody Integer num)
    {
        if(num == 2)
        {
            return HttpStatus.OK;
        } else
        {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    /*
        We can send data back in addition to a status.

        We just have to wrap them in a response entity.
     */
    @PostMapping("/sqrt")
    public ResponseEntity<Double> sqrt(@RequestBody Double num)
    {
        if(num < 0.0f)
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else
        {
            return new ResponseEntity<>(Math.sqrt(num), HttpStatus.OK);
        }
    }
}
