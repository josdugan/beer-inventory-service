package com.josdugan.beerinventoryservice.services.listeners;

import com.josdugan.beerinventoryservice.services.AllocationService;
import com.josdugan.beerworkscommon.constants.MessageQueues;
import com.josdugan.beerworkscommon.events.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = MessageQueues.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
