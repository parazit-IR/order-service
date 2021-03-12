package orderservice.com.company.order.config;


import com.company.events.inventory.InventoryStatus;
import com.company.events.order.OrderStatus;
import com.company.events.payment.PaymentStatus;
import orderservice.com.company.order.entity.PurchaseOrder;
import orderservice.com.company.order.repository.PurchaseOrderRepository;
import orderservice.com.company.order.service.OrderStatusPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class OrderStatusUpdateEventHandler {

    @Autowired
    private PurchaseOrderRepository repository;

    @Autowired
    private OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(final UUID id, Consumer<PurchaseOrder> consumer){
        this.repository
                .findById(id)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder){
        if(Objects.isNull(purchaseOrder.getInventoryStatus()) || Objects.isNull(purchaseOrder.getPaymentStatus()))
            return;
        var isComplete = PaymentStatus.RESERVED.equals(purchaseOrder.getPaymentStatus()) && InventoryStatus.RESERVED.equals(purchaseOrder.getInventoryStatus());
        var orderStatus = isComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
        if (!isComplete){
            this.publisher.raiseOrderEvent(purchaseOrder, orderStatus);
        }
    }

}