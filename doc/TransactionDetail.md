## 1 根据交易hash获取详细交易信息


method：GET  
url:/transactionDetail/getTransactionDetailByTxHash?txHash={txHash}  
```json

successResponse： 

[
    {
        "txDetailId": 1,
        "txHash": "d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63",
        "adress": "3KnwSrjGmR8U9HGnRqnDGgsVA3NRtCKhdZ",
        "transactionFees": 0.00094027,
        "weight": 4534,
        "outputTotal": 0.11534299,
        "inputTotal": 0.11628326,
        "amount": 1.0967,
        "type": 1,
        "size": 2098
    },
    {
        "txDetailId": 2,
        "txHash": "d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63",
        "adress": "f73c1ce2416af67c0ac64201a8dcad1420adebb4d06672c7bfbb1b7d40bffa7a",
        "transactionFees": 0.00002935,
        "weight": 1132,
        "outputTotal": 0.00494655,
        "inputTotal": 0.0049759,
        "amount": 0.00494655,
        "type": 1,
        "size": 283
    }
]

```

| ResponseField     |     Type |   Description   | 
| :--------------: | :--------:| :------: |
|    txDetailId|   Long |  详细信息id(主键自增) |
|    txHash|   String |  交易信息hash(外键) |
|    adress|   String | 交易地址 |
|    transactionFees  | Double |  交易费用 |
|    outputTotal|   Double | 最高输出|
|    weight|   Double | 交易重量|
|    inputTotal|   Double | 最高输入|
|    amount|   Double | 交易金额|
|    type   byte | 类型|
|    size   Integer | 交易大小|






