package com.example.market2.service;

import com.example.market2.converters.UserRequestToUserConverter;
import com.example.market2.entity.Product;
import com.example.market2.entity.PurchaseResponse;
import com.example.market2.entity.TransactionalLogs;
import com.example.market2.entity.User;
import com.example.market2.entity.UserRequest;
import com.example.market2.exception.ServerException;
import com.example.market2.repository.ProductRepository;
import com.example.market2.repository.TransactionalLogsRepository;
import com.example.market2.repository.UserRepository;
import com.example.market2.response.ResponseCode;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    int generateUid(){
        return (int) Math.floor(Math.random()*(1000)+1);
    }


    private UserRepository userRepository;
    private ProductRepository productRepository;
    private UserRequestToUserConverter userRequestToUserConverter;
    private TransactionalLogsRepository transactionalLogsRepository;
 public  UserService(UserRepository userRepository, UserRequestToUserConverter userRequestToUserConverter,ProductRepository productRepository,TransactionalLogsRepository transactionalLogsRepository) { //, UserRequestToUserConverter userRequestToUserConverter
          this.userRepository = userRepository;
       this.userRequestToUserConverter = userRequestToUserConverter;
       this.productRepository = productRepository;
       this.transactionalLogsRepository = transactionalLogsRepository;
   }

    public User addUser(UserRequest userRequest) {
        User user = userRequestToUserConverter.convert(userRequest);
        int uid = generateUid();
        user.setUid(uid);
        return userRepository.save(user);
    }
    public void buyProduct(int userId, Long productId, int qty) throws ServerException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);
        if(!user.isPresent()||!product.isPresent()){
            throw new ServerException(ResponseCode.NOT_FOUND,"USER/Product not found");
        }

        User currentUser = user.get();
        Product currentProduct = product.get();
        log.info("Product Qty In Db: {}, Qty Required: {}, User's Balance:{}, Product's MRP:{} ",currentProduct.getSku_qty(),qty,currentUser.getBalance(),currentProduct.getMrp());
        this.QtyAndBalanceChecks(currentProduct.getSku_qty(),qty,currentUser.getBalance(),currentProduct.getMrp());
        this.UpdateQtyAndBalance(currentUser,currentProduct,qty,productId,userId);
    }

    private void QtyAndBalanceChecks(int productQty,int qty,double userBalance,double productMrp) throws ServerException {
        if(productQty<qty) {
            throw new ServerException(ResponseCode.QTY_EXCEEDED,"THERE ARE LESS QTY IN INVENTORY");
        }
        if (userBalance < productMrp * qty) {
            throw new ServerException(ResponseCode.INSUFFICIENT_BALANCE,"INSUFFICIENT BALANCE");
        }
    }

    private  void UpdateQtyAndBalance(User currentUser,Product currentProduct,int qty,long productId,int userId){
        currentUser.setBalance(currentUser.getBalance()-qty*currentProduct.getMrp());
        currentProduct.setSku_qty(currentProduct.getSku_qty()-qty);
        TransactionalLogs logs = new TransactionalLogs();
        logs.setProductId(productId);
        logs.setUserId(userId);
        logs.setQty(qty);

        transactionalLogsRepository.save(logs);
        userRepository.save(currentUser);
        productRepository.save(currentProduct);
    }
}
