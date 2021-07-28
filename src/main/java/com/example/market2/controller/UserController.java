package com.example.market2.controller;
import com.example.market2.entity.PurchaseResponse;
import com.example.market2.entity.User;
import com.example.market2.entity.UserRequest;
import com.example.market2.exception.ServerException;
import com.example.market2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {


    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/home")
    public void home(){
        System.out.println("I am here");
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public User createUser( @RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @GetMapping(value="/buy/{userId}/{productId}",produces = "application/json")
    public ResponseEntity<PurchaseResponse> buyProduct(@PathVariable int userId, @PathVariable Long productId, @RequestParam int qty)
    {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setProductId(productId);
        purchaseResponse.setUserId(userId);
        try{
            userService.buyProduct(userId,productId,qty);
            purchaseResponse.setMessage("Success");
            return ResponseEntity.status(HttpStatus.OK).body(purchaseResponse);
        }
        catch (ServerException snf) {
            log.error("Purchase Failed");
            purchaseResponse.setMessage("Failure");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(purchaseResponse);
        }
    }
}
