package com.example.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqTest {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @RequestMapping("/test/sendMessage")
    public void sendMessage() {
        messageSender.sendMessage(MqConstant.TEST_EXCHANGE, MqConstant.TEST_ROUTING_KEY, "test");
    }

    @RequestMapping("/zf/sendMessage")
    public void sendMessageZf() {
//        String cabinetInfo = "{\"type\":1,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":{\"ueSn\":\"CC123456\",\"lon\":\"120.068554\",\"lat\":\"30.286559\",\"boxNum\":10,\"emptyBoxNum\":1,\"disabledBoxNum\":1,\"temp\":40.5,\"dailyActive\":1,\"todayActive\":1,\"dailyExchangeCount\":1.5,\"todayExchangeCount\":1,\"fullSocBatteryCount\":1,\"chargingBatteryCount\":1,\"yesterdayCharging\":1.1,\"electricityMeter\":1.1,\"exStatus\":0,\"boxList\":[{\"boxSn\":1,\"boxEnableStatus\":1,\"disabledReason\":\"no reason\",\"doorStatus\":0,\"cur\":0.00,\"vol\":53.22,\"batterySoc\":100,\"batterySn\":\"BT123\",\"batteryTemp\":40.5,\"batterySoh\":92}]}}";
        String cabinetInfo = "{\"type\":1,\"manufacturer\":10005,\"time\":\"1989174468000\",\"data\":{\"ueSn\":\"CC123456\",\"lon\":\"120.068554\",\"lat\":\"30.286559\",\"boxNum\":10,\"emptyBoxNum\":1,\"disabledBoxNum\":1,\"temp\":40.5,\"dailyActive\":1,\"todayActive\":1,\"dailyExchangeCount\":1.5,\"todayExchangeCount\":1,\"fullSocBatteryCount\":1,\"chargingBatteryCount\":1,\"yesterdayCharging\":1.1,\"electricityMeter\":1.1,\"exStatus\":0,\"boxList\":[{\"boxSn\":987,\"boxEnableStatus\":1,\"disabledReason\":\"no reason\",\"doorStatus\":0,\"cur\":0.00,\"vol\":53.22,\"batterySoc\":100,\"batterySn\":\"BT123\",\"batteryTemp\":41,\"batterySoh\":92}]}}";
        messageSender.sendMessage(MqConstant.ZF_EXCHANGE, MqConstant.ZF_ROUTING_KEY, cabinetInfo);

//        String exchangeInfo = "{\"type\":2,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":[{\"ueSn\":\"123456\",\"operateTime\":\"1989174468000\",\"userName\":\"ccc\",\"phone\":\"15777777777\",\"status\":1,\"returnBatterySn\":\"BT002\",\"returnBatteryTime\":\"1989174468000\",\"takeBatterySn\":\"BT001\",\"takeBatteryTime\":\"1989174468000\",\"returnBoxSn\":1,\"takeBoxSn\":2}]}";
//        messageSender.sendMessage(MqConstant.ZF_EXCHANGE, MqConstant.ZF_ROUTING_KEY, exchangeInfo);

//        String orderInfo = "{\"type\":3,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":{\"orderTime\":\"1989174468000\",\"userName\":\"ccc\",\"phone\":\"15777777777\",\"chargeType\":0,\"amount\":299,\"productName\":\"1代电池48V\",\"storeName\":\"宝荷站\",\"batterySn\":\"E2472BS17B\",\"province\":\"浙江\",\"city\":\"杭州\",\"thirdChannel\":\"三级渠道-杭州\",\"payment\":\"支付宝\",\"deposit\":0,\"rentAmount\":299,\"totalAmount\":299,\"discountAmount\":0,\"serviceAmount\":0,\"merchantOrderNo\":\"2231109000370835815\",\"parentOrderNo\":\"3231109000370835815\"}}";
//        messageSender.sendMessage(MqConstant.ZF_EXCHANGE, MqConstant.ZF_ROUTING_KEY, orderInfo);

//        String paymentOrderInfo = "{\"type\":4,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":{\"source\":\"叮咚\",\"fundType\":\"收入\",\"orderType\":\"单租电池\",\"billScene\":\"支付租金\",\"payType\":\"普通支付-支付宝\",\"ifWithhold\":0,\"orderTime\":\"1989174468000\",\"endTime\":\"1989174468000\",\"refundTime\":\"1989174468000\",\"payTime\":\"1989174468000\",\"userName\":\"张三\",\"userPhone\":\"13820998237\",\"identifyNo\":\"360403188223994888\",\"packageName\":\"月租套餐299\",\"leaseTime\":30,\"tradeAmount\":299,\"city\":\"杭州\",\"orderNo\":\"1327058387094994944\",\"payInterfaceOutTradeNo\":\"2202207040000374557\"}}";
//        messageSender.sendMessage(MqConstant.ZF_EXCHANGE, MqConstant.ZF_ROUTING_KEY, paymentOrderInfo);

//        String parentOrderInfo = "{\"type\":5,\"manufacturer\":1,\"time\":\"1989174468000\",\"data\":{\"userName\":\"张三\",\"userPhone\":\"13820998237\",\"orderNo\":\"360403188223994888\",\"rentAmountSum\":199,\"depositAmount\":299,\"refundRent\":100,\"refundDeposit\":299,\"leaseType\":3,\"ifFreeDeposit\":0,\"ifOverdue\":0,\"status\":3,\"startTime\":\"1705910521000\",\"endTime\":\"1708588921000\",\"refundTime\":\"1706342521000\",\"ueSn\":\"E2471CC158\",\"lastExchangeTime\":\"1706256121000\"}}";
//        messageSender.sendMessage(MqConstant.ZF_EXCHANGE, MqConstant.ZF_ROUTING_KEY, parentOrderInfo);
    }

    @RequestMapping("/zf/deleteExchange")
    public void deleteExchange() {
        rabbitAdmin.deleteExchange(MqConstant.ZF_EXCHANGE);
    }
}
