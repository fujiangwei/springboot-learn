# AmqpTemplate,RabbitTemplate
```aidl
Spring AMQP提供了一个发送和接收消息的操作模板类AmqpTemplate。 AmqpTemplate它定义包含了发送和接收消息等的一些基本的操作功能。RabbitTemplate是AmqpTemplate的一个实现。
RabbitTemplate支持消息的确认与返回，为了返回消息，RabbitTemplate 需要设置mandatory 属性为true,并且CachingConnectionFactory 的publisherReturns属性也需要设置为true。返回的消息会根据它注册的RabbitTemplate.ReturnCallback setReturnCallback 回调发送到给客户端，一个RabbitTemplate仅能支持一个ReturnCallback 。
为了确认Confirms消息, CachingConnectionFactory 的publisherConfirms 属性也需要设置为true，确认的消息会根据它注册的RabbitTemplate.ConfirmCallback setConfirmCallback回调发送到给客户端。一个RabbitTemplate也仅能支持一个ConfirmCallback。
```

# 常用API

    //消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    
    //ack返回false，并重新回到队列
    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    
    //拒绝消息
    channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
