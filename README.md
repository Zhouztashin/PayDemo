##概述
这是一个关于支付宝支付、微信支付的例子，里面高度集成了支付宝支付和微信支付,便于开发者集成。
##集成说明
将支付过程分为两部分：
<br> 1、订单生成
<br> 2、订单支付
<br>代码中与订单对应的类是OrderCreator.java，支付对应的是Pay.java.然后通过继承AliPayOrderCreator与AliPayPay实现了支付宝功能。微信支付亦是如此。
##集成过程(以Alipay为例子）
<br>1、继承AliPayOrderCreator配置Alipay相应信息（App Id,商户ID等）
<br>2、继承AliPayPay,处理支付后的结果
<br>3、调用支付，如下：
<br>
```	
  Pay pay = new MyAlipayPay(this);
  pay.pay(new MyAlipayOrdersCretor("产品名称", "产品详情", "价格", 1));
```
##注意事项
支付宝支付：
<br>1、主要是确定配置信息无误，其他按照支付宝文档来，没问题。
<br>微信支付：
<br>1、确认签名等信息无误,如错误会导致调用不起微信支付
<br>2、如果在微信支付平台申请的应用包名是com.xxx，那么在com.xxx包下，需要建立一个wxapi包，并且放置名称为WXPayEntryActivity的Activity实现类，并且在里面处理支付结果(Demo已有该类)，微信支付通过这个入口给予用户处理结果。
<br>3、其他按照微信文档来，没问题。
