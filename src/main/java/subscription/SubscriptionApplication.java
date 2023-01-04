package subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SubscriptionApplication {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public static void main(String[] args){
        SpringApplication.run(SubscriptionApplication.class,args);
    }

    @GetMapping("/showAll")
    @ResponseBody
    public Iterable<Subscribers> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    @PostMapping("/subscribe")
    @ResponseBody
    public String subscribe(@RequestBody Subscribers subscribers){
        subscriberRepository.save(subscribers);
        return "success";
    }

    @PatchMapping("/subscribe/{email}")
    @ResponseBody
    public Subscribers detail(@RequestBody Subscribers subscribers, @PathVariable String email){
        Optional<Subscribers> optional = subscriberRepository.getSubscriberByEmail(email);
        if(optional.isEmpty())
            throw new NoSuchElementException();
        Subscribers subscribers1 = optional.get();
        subscribers1.setC_name(subscribers.getC_name());
        subscribers1.setPhone(subscribers.getPhone());
        subscribers1.setPostcode(subscribers.getPostcode());
        subscribers1.setEmail_address(email);
        subscriberRepository.save(subscribers1);
        subscriberRepository.delete(subscribers);
        return subscribers1;
    }
}
