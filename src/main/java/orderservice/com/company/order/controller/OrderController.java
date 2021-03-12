package orderservice.com.company.order.controller;


import com.company.dto.OrderRequestDto;
import orderservice.com.company.order.entity.PurchaseOrder;
import orderservice.com.company.order.service.OrderCommandService;
import orderservice.com.company.order.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderCommandService commandService;

    @Autowired
    private OrderQueryService queryService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto requestDTO){
        requestDTO.setOrderId(UUID.randomUUID());
        return this.commandService.createOrder(requestDTO);
    }

    @GetMapping("/all")
    public List<PurchaseOrder> getOrders(){
        return this.queryService.getAll();
    }

}