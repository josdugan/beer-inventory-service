package com.josdugan.beerinventoryservice.services.listeners;

import com.josdugan.beerinventoryservice.services.AllocationService;
import com.josdugan.beerworkscommon.constants.MessageQueues;
import com.josdugan.beerworkscommon.events.AllocateOrderRequest;
import com.josdugan.beerworkscommon.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = MessageQueues.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {
        var builder = AllocateOrderResult.builder();
        builder.beerOrderDto(request.getBeerOrderDto());

        try {
            Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrderDto());

            if (allocationResult) {
                builder.pendingInventory(false);
            } else {
                builder.pendingInventory(true);
            }

            builder.allocationError(false);
        } catch (Exception e) {
            log.error("Allocation failed for order id: " + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(MessageQueues.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
