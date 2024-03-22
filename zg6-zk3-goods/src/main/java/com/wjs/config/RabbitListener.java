package com.wjs.config;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * description
 *
 * @author wangjunshan
 * @date 2023/11/30 20:40
 */
@Component
@org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "order2104")
public class RabbitListener {

    @RabbitHandler
    public void consumer(Integer id, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        // channel.basicAck(deliveryTag,false);
        channel.basicAck(deliveryTag,false);
    }


}
