{1:F01${senderBank}}{2:O300}{4:
:20:${referenceNumber}
<#if includeNewt>
:22A:NEWT
</#if>
:77H:${currencyPair}
:32B:${amount?string["0.00"]}
:36:${exchangeRate?string["0.0000"]}
:52A:${senderBank}
:58A:${receiverBank}
-}