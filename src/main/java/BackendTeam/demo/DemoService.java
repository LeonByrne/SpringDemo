package BackendTeam.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Services are typically in between web facing controllers and more technical aspects, such as databases and other api calls
@Service
public class DemoService
{
    // Just an example
    private final ArrayList<PersonDTO> people = new ArrayList<>();


    // In case you are unfamiliar, if one thread calls a synchronized method, other threads must wait until the first thread returns before it can proceed.
    public synchronized void addPerson(PersonDTO person)
    {
        people.add(person);
    }

    public String getPeople()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < people.size(); i++)
        {
            stringBuilder.append(people.get(i)).append("\n");
        }

        return stringBuilder.toString();
    }
}
