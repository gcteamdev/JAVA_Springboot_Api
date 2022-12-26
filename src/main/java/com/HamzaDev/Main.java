package com.HamzaDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
  //private final const was created in step 5 after api url
    private final CustomerRepository customerRepository;
    //with hover over above was changed to "Add constructor parameter"

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    //this method was created as Step 5 after creating customer database ((((GET)))
    @GetMapping
    public List<Customer> getCustomer(){
        //return List.of();
        return customerRepository.findAll();
    }

     //adding a method to data by customer (((POST)))
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
     ) {}
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
      Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    //(((DELETE)))
    @DeleteMapping( "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
       //For now, we will avoid business logic like does customer exists? etc
        customerRepository.deleteById(id);
    }

    // (((PUT)))
    record UpdateCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}
    @PutMapping( "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody UpdateCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }
    //above code is a bug as its creating a new customer when updating.It should only replace old info with new = update
    @GetMapping("/greet")
    public GreetResponse greet() {

        GreetResponse response = new GreetResponse(
                "Hamza",
              List.of("JavaScript", "JAVA", "React"),
              new Person ("Alex", 32, 30_000)
      );
         return response;

    }
    record Person(String name, int age, double savings) {

    }
    record GreetResponse<person>(
           String greet,
           List<String> languages,
           Person person
    ) {

   }
}
