package com.example.bank.service.transaction;

import com.example.bank.dto.OrderReq;
import com.example.bank.dto.card.CardResponse;
import com.example.bank.dto.order.OrderConverter;
import com.example.bank.dto.order.OrderRequest;
import com.example.bank.dto.order.OrderResponse;
import com.example.bank.dto.product.ProductConverter;
import com.example.bank.dto.product.ProductResponse;
import com.example.bank.dto.transaction.TransactionConverter;
import com.example.bank.dto.transaction.TransactionCreationResponse;
import com.example.bank.dto.transaction.TransactionRequest;
import com.example.bank.exception.BalanceAmountIsLowException;
import com.example.bank.exception.CardCodeException;
import com.example.bank.exception.CardYearException;
import com.example.bank.exception.CvvException;
import com.example.bank.model.*;
import com.example.bank.model.enums.OrderStatus;
import com.example.bank.model.enums.ShoppingCartStatus;
import com.example.bank.model.enums.TransactionStatus;
import com.example.bank.repository.order.OrderRepository;
import com.example.bank.repository.product.ProductRepository;
import com.example.bank.repository.transaction.TransactionRepository;
import com.example.bank.service.card.CardService;
import com.example.bank.service.order.OrderService;
import com.example.bank.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final TransactionConverter transactionConverter;
    private final CardService cardService;
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductRepository productRepository;
    private final OrderRepository  orderRepository;

    public TransactionCreationResponse createTransaction(TransactionRequest transactionRequest) {

        Orders oneOrder = orderService.getOrderByUuid(transactionRequest.getOrderUuid());
        ShoppingCart shoppingCart = oneOrder.getShoppingCart();
        List<Product> products = shoppingCart.getProducts();

        List<Card> allCards = cardService.getAllCards();
        Transaction transaction = transactionConverter.convertToTransaction(transactionRequest);
        transaction.setOrders(oneOrder);

        for (Card card:allCards){
            if (transactionRequest.getCardCode().equals(card.getCardCode())
                    && transactionRequest.getCvv().equals(card.getCvv())
                    && transactionRequest.getCardYear().equals(card.getCardYear())){
                if (card.getBalance().compareTo(oneOrder.getAmount()) >= 0) {
                    card.setBalance(card.getBalance().subtract(oneOrder.getAmount()));
                    transaction.setTransactionStatus(TransactionStatus.SUCCESS);
                    oneOrder.setOrderStatus(OrderStatus.SUCCESS);
                    if (oneOrder.getOrderStatus().equals(OrderStatus.SUCCESS)){
                        for (Product product:products){
                            product.setAddTime(null);
                            productRepository.save(product);
                        }
                    }
                    oneOrder.getShoppingCart().setAmount(BigDecimal.valueOf(0));
                    oneOrder.getShoppingCart().setShoppingCartStatus(ShoppingCartStatus.EMPTY);
                    oneOrder.getShoppingCart().getProducts().clear();
                    orderRepository.save(oneOrder);
                    transactionRepository.save(transaction);
                   return transactionConverter.convertToCreatResponse(transaction);
               } else {
                    transaction.setTransactionStatus(TransactionStatus.FAILED);
                    oneOrder.setOrderStatus(OrderStatus.FAILED);
                    transactionRepository.save(transaction);
                    throw new BalanceAmountIsLowException();
                }

            }
            else {

                transaction.setTransactionStatus(TransactionStatus.FAILED);
                oneOrder.setOrderStatus(OrderStatus.FAILED);
                transactionRepository.save(transaction);
                throw new CardCodeException();

            }
        }
        if (oneOrder.getOrderStatus().equals(OrderStatus.SUCCESS)){
            for (Product product:products){
                product.setAddTime(null);
                products.remove(product);
                productRepository.save(product);
            }
        }
        if (transaction.getTransactionStatus().equals(TransactionStatus.FAILED)) {
            for (Card card : allCards) {
                if (transaction.getCardCode().equals(card.getCardCode())) {
                    if (transaction.getCvv().equals(card.getCvv())) {
                        if (!transaction.getCardYear().equals(card.getCardYear())) {
                            throw new CardYearException();
                        }
                    }
                    throw new CvvException();
                }

            }
            throw new CardCodeException();
        } else {

            return transactionConverter.convertToCreatResponse(transaction);
        }
    }

}



